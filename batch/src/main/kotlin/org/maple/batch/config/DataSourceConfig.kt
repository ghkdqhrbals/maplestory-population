package org.maple.batch.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.batch.BatchDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.support.JdbcTransactionManager
import javax.sql.DataSource


@Configuration
@EnableAutoConfiguration(
    exclude = [
        DataSourceAutoConfiguration::class,
    ],
)
class DataSourceConfig {

    @Bean(name = ["batchTransactionManager"])
    fun batchTransactionManager(dataSource: DataSource?): JdbcTransactionManager {
        return JdbcTransactionManager(dataSource!!)
    }

    @Bean
    @ConfigurationProperties(prefix = "maplefusion.batch")
    fun batchHikariConfig(): HikariConfig = HikariConfig()


    @BatchDataSource
    @Bean(name = ["dataSource", "batchDataSource"])
    fun masterDataSource(): DataSource {
        return HikariDataSource(batchHikariConfig())
    }

}
