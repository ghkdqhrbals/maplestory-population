package org.maple.config.datasource

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
@EnableAutoConfiguration(
    exclude = [
        DataSourceAutoConfiguration::class,
    ],
)
class MaplefusionDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "maplefusion.master")
    fun masterHikariConfig(): HikariConfig = HikariConfig()

    @Bean
    @Primary
    fun masterDataSource(): DataSource {
        return HikariDataSource(masterHikariConfig())
    }

}