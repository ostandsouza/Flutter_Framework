@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun byValueKey(input: String): FlutterElement {
  return FlutterElement(mapOf(
          "finderType" to "ByValueKey",
          "keyValueType" to "String",
          "keyValueString" to input
  ))
}

fun byValueKey(input: Int): FlutterElement {
  return FlutterElement(mapOf(
          "finderType" to "ByValueKey",
          "keyValueType" to "int",
          "keyValueString" to input
  ))
}