package com.just_ai.test_assignment.mapping

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.just_ai.test_assignment.exceptions.AppException
import com.just_ai.test_assignment.requests.Event
import com.just_ai.test_assignment.requests.vkobjects.MessageNew
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EventDeserializer: StdDeserializer<Event<*>>(Event::class.java) {
    private val log: Logger = LoggerFactory.getLogger(EventDeserializer::class.java)

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Event<*> {
        val node = p.readValueAsTree<JsonNode>()
        val type = node.checkAndGet("type") { it, key -> it.hasNonNull(key) }.textValue()
        val groupId = node.checkAndGet("group_id") { it, key -> it.hasNonNull(key) && it[key].isInt }.asInt()
        val secret = if (node.has("secret")) node.get("secret").textValue() else ""
        val vkObject = if (type.compareTo("confirmation") != 0) castVkObject(node, type) else ""
        return Event(type, vkObject, groupId, secret)
    }

    private fun castVkObject(node: JsonNode, type: String) = node.checkAndGet("object") { it, key ->
        it.hasNonNull(key)
    }.let {
        when (type) {
            "message_new" -> jacksonObjectMapper().readerFor(MessageNew::class.java).readValue<MessageNew>(it)
            else -> it.textValue()
        }
    }

    private fun JsonNode.checkAndGet(key: String, check: (JsonNode, String) -> Boolean): JsonNode {
        if (check(this, key))
            return this[key]
        else {
            this@EventDeserializer.log.info("Problem with field $key")
            throw AppException.EventDeserialization("Problem with field $key")
        }
    }
}