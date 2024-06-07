package com.just_ai.test_assignment.services

import com.just_ai.test_assignment.controllers.BotController
import com.just_ai.test_assignment.props.VkApiProperties
import com.just_ai.test_assignment.requests.Event
import com.just_ai.test_assignment.requests.vkobjects.MessageNew
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import kotlin.random.Random

@Service
class BotService(
    private val vkApiProperties: VkApiProperties,
    private val vkApiRepository: VkApiService
) {
    companion object {
        private const val MAX_MESSAGE_LENGTH = 4096
        private const val MESSAGE_PREFIX = "Вы сказали: "
    }
    private val log: Logger = LoggerFactory.getLogger(BotController::class.java)

    @Suppress("UNCHECKED_CAST")
    fun handleEvent(event: Event<*>): String {
        if (vkApiProperties.secret.isNotEmpty() && vkApiProperties.secret.compareTo(event.secret) != 0) {
            log.info("Wrong secret: ${event.secret}")
            return "ok"
        }

        return when (event.type) {
            "message_new" -> handleMessageNew(event as Event<MessageNew>)
            "confirmation" -> vkApiProperties.confirmationKey
            else -> "ok"
        }
    }

    fun handleMessageNew(event: Event<MessageNew>): String {
        if (event.vkObject.message.text.isNotEmpty()) {
            val params = LinkedMultiValueMap<String, String>().apply {
                add("user_id", event.vkObject.message.fromId.toString())
                add("message", MESSAGE_PREFIX)
                add("random_id", Random.nextInt().toString())
                add("access_token", vkApiProperties.accessToken)
                add("v", vkApiProperties.version)
            }

            if ((MESSAGE_PREFIX + event.vkObject.message.text).length > MAX_MESSAGE_LENGTH) {
                vkApiRepository.messageSend(params)
                params["message"]!![0] = event.vkObject.message.text
                params["random_id"]!![0] = Random.nextInt().toString()
                vkApiRepository.messageSend(params)
            } else {
                params["message"]!![0] += event.vkObject.message.text
                vkApiRepository.messageSend(params)
            }
        }

        return "ok"
    }
}