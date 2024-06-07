package com.just_ai.test_assignment.requests.vkobjects

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class Message(
    @JsonProperty(value = "from_id", required = true) val fromId: Int,
    @JsonProperty(required = true) val text: String
)
