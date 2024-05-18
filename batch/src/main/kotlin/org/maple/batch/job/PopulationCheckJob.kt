package org.maple.batch.job

import org.maple.batch.config.BasicJobParamIncrementer
import org.maple.config.logger
import org.maple.domain.rank.RankRepository
import org.springframework.batch.core.*
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDateTime


@Configuration
@ComponentScan(basePackages = ["org.maple"])
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
class PopulationCheckJob(
    private val jobRepository: JobRepository,
    private val rankRepository: RankRepository,
    private val transactionManager: PlatformTransactionManager,
    private val basicJobParamIncrementer: BasicJobParamIncrementer,
) {
    companion object {
        const val JOB_NAME = "PopulationCheckJob"
        const val STEP_NAME = "StepOf$JOB_NAME"
        const val CHUNK_AND_PAGE_SIZE = 1000
    }


    @Bean(JOB_NAME)
    fun job(printHelloWorld: Step): Job {
        rankRepository.findAllByDateIs(LocalDateTime.now())
        return JobBuilder(JOB_NAME, jobRepository)
            .start(printHelloWorld)
            .incrementer(basicJobParamIncrementer)
            .build()
    }

    @Bean
    @JobScope
    fun printHelloWorld(): Step{
        return StepBuilder(STEP_NAME, jobRepository)
            .tasklet(myTasklet(), transactionManager)
            .build()

    }

    @Bean
    fun myTasklet(): MethodInvokingTaskletAdapter {
        val adapter = MethodInvokingTaskletAdapter()
        adapter.setTargetObject(this)
        adapter.setTargetMethod("printHelloWorldMessage")
        return adapter
    }

    fun printHelloWorldMessage() {
        logger().info("Hello World!")
    }
}