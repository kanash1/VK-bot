package com.just_ai.test_assignment.controlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.just_ai.test_assignment.requests.Event
import com.just_ai.test_assignment.requests.vkobjects.Message
import com.just_ai.test_assignment.requests.vkobjects.MessageNew
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class BotControllerTests @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    @Test
    fun `should return ok`() {
        val event = Event("message_new", MessageNew(Message(0, "test")), 0, "")

        val performPost = mockMvc.post("/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(event)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { string("ok") }
            }
    }

    @Test
    fun `should return BAD REQUEST`() {
        val event = Event("message_new", Message(0, "test"), 0, "aboba")

        val performPost = mockMvc.post("/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(event)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }
}