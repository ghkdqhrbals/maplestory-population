package org.maple.batch.config

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
//class HelloWorldJobConfig: DefaultBatchConfiguration(){
//
//    companion object{
//        const val JOB_NAME = "MyJob"
//        const val STEP_NAME = "StepOf$JOB_NAME"
//        const val CHUNK_AND_PAGE_SIZE = 1000
//    }
//
//    @Bean(JOB_NAME)
//    fun myJob(
//        jobRepository: JobRepository,
//        myStep: Step
//    ): Job {
//        return JobBuilder(JOB_NAME, jobRepository)
//            .preventRestart()
//            .incrementer(BasicJobParamIncrementer())
//            .start(myStep)
//            .build()
//    }
//
//    @Bean
//    fun myStep(jobRepository: JobRepository): Step {
//        return StepBuilder(STEP_NAME, jobRepository!!)
//            .tasklet(
//                { _: StepContribution?, _: ChunkContext? ->
//                    println("Hello, world!")
//                    RepeatStatus.FINISHED
//                },
//                transactionManager!!,
//            )
//            .build()
//
//    }
//
//}

