@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.util.*

val json = Json(JsonConfiguration.Stable)
val base64encoder = Base64.getUrlEncoder().withoutPadding()
val base64decoder = Base64.getUrlDecoder()

@OptIn(ImplicitReflectionSerializer::class)
fun serialize(o: Map<String, *>): String {
  val jsonStringified = json.stringify(jsonObjectFrom(o))
  val base64Encoded = base64encoder.encodeToString(jsonStringified.toByteArray())
  return base64Encoded
}

@OptIn(ImplicitReflectionSerializer::class)
fun jsonObjectFrom(o: Map<String, *>): Map<String, JsonElement> {
  return o.map {
    val value = it.value
    val jsonO = when (value) {
      is String -> JsonLiteral(value)
      is Number -> JsonLiteral(value)
      is Boolean -> JsonLiteral(value)
      is Map<*, *> -> JsonLiteral(json.stringify(jsonObjectFrom(value as Map<String, *>)))
      is JsonElement -> value
      else -> JsonNull
    }
    Pair(it.key, jsonO)
  }.toMap()
}

fun deserialize(base64Encoded: String): Map<String, *> {
  val base64Decoded = String(base64decoder.decode(base64Encoded))
  val jsonObject = json.parseJson(base64Decoded) as JsonObject
  return jsonObject.toMap()
}