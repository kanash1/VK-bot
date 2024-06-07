package com.just_ai.test_assignment.controllers

import com.just_ai.test_assignment.requests.Event
import com.just_ai.test_assignment.services.BotService
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(private val botService: BotService) {
    val log: Logger = getLogger(BotController::class.java)

    @PostMapping
    fun acceptEvent(@RequestBody event: Event<*>): ResponseEntity<*> {
        log.info("Event from VK: $event")
        return ResponseEntity.ok(botService.handleEvent(event))
    }
}