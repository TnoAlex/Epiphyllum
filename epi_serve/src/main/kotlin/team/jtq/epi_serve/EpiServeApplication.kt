package team.jtq.epi_serve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import team.jtq.epi_serve.config.AppResourceConfig

@SpringBootApplication
@EnableConfigurationProperties(AppResourceConfig::class)
class EpiServeApplication

fun main(args: Array<String>) {
    runApplication<EpiServeApplication>(*args)
}
