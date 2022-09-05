package team.jtq.epi_serve.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset


@Configuration
class RestTemplateConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        val requestFactory = SimpleClientHttpRequestFactory()
        requestFactory.setConnectTimeout(60*1000)
        val restTemplate = RestTemplate(requestFactory)
        restTemplate.messageConverters.add(1, StringHttpMessageConverter(Charset.forName("UTF-8")))
        return restTemplate
    }
}