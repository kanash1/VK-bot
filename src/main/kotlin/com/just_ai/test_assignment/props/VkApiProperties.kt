package com.just_ai.test_assignment.props

import jakarta.validation.constraints.NotEmpty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties(prefix = "vk-api")
data class VkApiProperties(
    @field:NotEmpty val accessToken: String,
    @field:NotEmpty val confirmationKey: String,
    val secret: String = "",
    val version: String = "5.199"
)