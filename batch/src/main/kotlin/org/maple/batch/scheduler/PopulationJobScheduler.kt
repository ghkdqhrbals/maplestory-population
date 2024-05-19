package org.maple.batch.scheduler

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class PopulationJobScheduler(
    private val jobLauncher: JobLauncher,

    @Qualifier("PopulationCheckJob")
    private val populationCheckJob: Job
) {
    @Scheduled(cron = "*/10 * * * * *")
    fun runHelloWorldJob() {
        val currentTime = JobParametersBuilder().addLong("time", System.currentTimeMillis())
        val jobExecution = jobLauncher.run(populationCheckJob, currentTime.toJobParameters())
        println("Job Execution Status: ${jobExecution.status}")
    }
}