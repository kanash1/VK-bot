package com.just_ai.test_assignment

import com.just_ai.test_assignment.props.VkApiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory


@SpringBootApplication
@EnableConfigurationProperties(VkApiProperties::class)
class BotApplication {
	@Bean
	fun vkRestTemplate() = RestTemplate().apply {
		uriTemplateHandler = DefaultUriBuilderFactory("https://api.vk.com/method")
	}
}

fun main(args: Array<String>) {
	runApplication<BotApplication>(*args)
}
