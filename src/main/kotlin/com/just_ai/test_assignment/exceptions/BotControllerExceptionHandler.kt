package com.just_ai.test_assignment.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BotControllerExceptionHandler {
    @ExceptionHandler(AppException.EventDeserialization::class)
    private fun handleBadRequestException(e: Exception): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }
}