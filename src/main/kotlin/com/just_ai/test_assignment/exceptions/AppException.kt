package com.just_ai.test_assignment.exceptions

import java.lang.Exception

sealed class AppException(message: String? = null, cause: Throwable? = null): Exception(message, cause) {
    class EventDeserialization(message: String? = null, cause: Throwable? = null): AppException(message, cause)
}