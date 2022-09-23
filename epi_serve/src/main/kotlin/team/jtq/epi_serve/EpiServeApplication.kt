package team.jtq.epi_serve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import team.jtq.epi_serve.config.AppResourceConfig

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(AppResourceConfig::class)
class EpiServeApplication

fun main(args: Array<String>) {
    runApplication<EpiServeApplication>(*args)
}
