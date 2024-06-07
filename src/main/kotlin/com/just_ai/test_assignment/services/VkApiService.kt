package com.just_ai.test_assignment.services

import com.just_ai.test_assignment.controllers.BotController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class VkApiService(val restTemplate: RestTemplate) {
    private val log: Logger = LoggerFactory.getLogger(BotController::class.java)

    fun messageSend(params: MultiValueMap<String, String>) {
        val relativeUrl = "/messages.send"
        val headers = HttpHeaders().apply { accept = listOf(MediaType.MULTIPART_FORM_DATA) }
        val entity = HttpEntity(params, headers)
        val response = restTemplate.postForEntity(relativeUrl, entity, String::class.java)
        log.info("Response from $relativeUrl: ${response.body}")
    }
}