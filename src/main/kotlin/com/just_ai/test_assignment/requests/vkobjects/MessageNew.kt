package com.just_ai.test_assignment.requests.vkobjects

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class MessageNew(
    @JsonProperty(required = true) val message: Message
)