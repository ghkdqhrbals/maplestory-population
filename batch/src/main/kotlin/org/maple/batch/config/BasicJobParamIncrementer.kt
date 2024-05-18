package org.maple.batch.config

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersIncrementer
import org.springframework.context.annotation.Configuration

@Configuration
class BasicJobParamIncrementer : JobParametersIncrementer {
    override fun getNext(parameters: JobParameters?): JobParameters {
        val builder = JobParametersBuilder(parameters ?: JobParameters())
            .addLong("currentTimeMillis", System.currentTimeMillis(), true)
        return builder.toJobParameters()
    }
}
