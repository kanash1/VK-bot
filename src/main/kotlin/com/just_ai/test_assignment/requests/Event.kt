package com.just_ai.test_assignment.requests

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.just_ai.test_assignment.mapping.EventDeserializer

@JsonDeserialize(using = EventDeserializer::class)
@JsonIgnoreProperties(ignoreUnknown=true)
data class Event<out VkObject>(
    @JsonProperty(required = true) val type: String,
    @JsonProperty("object") val vkObject: VkObject,
    @JsonProperty(value = "group_id", required = true) val groupId: Int,
    val secret: String
)
