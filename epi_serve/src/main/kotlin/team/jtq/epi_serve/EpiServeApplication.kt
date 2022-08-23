package team.jtq.epi_serve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EpiServeApplication

fun main(args: Array<String>) {
    runApplication<EpiServeApplication>(*args)
}
