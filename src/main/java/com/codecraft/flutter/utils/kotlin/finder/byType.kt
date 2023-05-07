@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun byType(input: String): FlutterElement {
  return FlutterElement(mapOf(
          "finderType" to "ByType",
          "type" to input
  ))
}
