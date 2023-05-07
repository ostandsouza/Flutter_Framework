@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun text(input: String): FlutterElement {
  return FlutterElement(mapOf(
          "finderType" to "ByText",
          "text" to input
  ))
}
