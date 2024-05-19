package org.maple.batch.job

import org.maple.batch.config.BasicJobParamIncrementer
import org.maple.batch.job.population.MapleExternalAPIService
import org.maple.batch.job.population.PaginatedItemReader
import org.maple.batch.job.population.RankResponse
import org.maple.batch.job.population.RankResponseWrapper
import org.maple.config.logger
import org.maple.domain.rank.RankRepository
import org.springframework.batch.core.*
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.listener.StepExecutionListenerSupport
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDateTime
import java.time.OffsetDateTime


@Configuration
@ComponentScan(basePackages = ["org.maple"])
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
class PopulationCheckJob(
    private val jobRepository: JobRepository,
    private val rankRepository: RankRepository,
    private val transactionManager: PlatformTransactionManager,
    private val basicJobParamIncrementer: BasicJobParamIncrementer,
    private val mapleExternalAPIService: MapleExternalAPIService,
) {
    companion object {
        const val JOB_NAME = "PopulationCheckJob"
        const val STEP_NAME = "StepOf$JOB_NAME"
        const val CHUNK_AND_PAGE_SIZE = 10
    }

    private var currentPage = 1


    @Bean(JOB_NAME)
    fun job(printHelloWorld: Step): Job {
        rankRepository.findAllByDateIs(LocalDateTime.now())
        return JobBuilder(JOB_NAME, jobRepository)
            .start(printHelloWorld)
            .listener(stepExecutionListener())
            .incrementer(basicJobParamIncrementer)
            .build()
    }

    @Bean
    @JobScope
    fun printHelloWorld(): Step{
        return StepBuilder(STEP_NAME, jobRepository)
            .chunk<RankResponseWrapper,RankResponseWrapper>(1, transactionManager)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build()

    }

    fun stepExecutionListener(): StepExecutionListener {
        logger().info("currentPage: ${currentPage} 스텝 종료")
        return object : StepExecutionListenerSupport() {
            override fun afterStep(stepExecution: StepExecution): ExitStatus? {
                currentPage += 10
                return super.afterStep(stepExecution)
            }
        }
    }

    @Bean
    fun itemReader(): ItemReader<RankResponseWrapper?> {
        return PaginatedItemReader(mapleExternalAPIService, OffsetDateTime.now(), CHUNK_AND_PAGE_SIZE)
    }

    fun itemProcessor(): ItemProcessor<RankResponseWrapper, RankResponseWrapper> {
        return ItemProcessor { item ->
            // Process the item here if needed
            item
        }
    }

    fun itemWriter(): ItemWriter<RankResponseWrapper> {
        return ItemWriter { items ->
            // Write the items here, e.g., save to database
            items.forEach { item -> println("Writing item: $item") }
        }
    }

//    @Bean
//    fun myTasklet(): MethodInvokingTaskletAdapter {
//        val adapter = MethodInvokingTaskletAdapter()
//        adapter.setTargetObject(this)
//        adapter.setTargetMethod("printHelloWorldMessage")
//        return adapter
//    }

    fun printHelloWorldMessage() {
        val fetchPage = mapleExternalAPIService.fetchPage(OffsetDateTime.now(), 1)
        logger().info("time: ${OffsetDateTime.now()} fetchPage: $fetchPage")
    }

}