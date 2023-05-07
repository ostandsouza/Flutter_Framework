@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun byTooltip(input: String): FlutterElement {
  return FlutterElement(mapOf(
          "finderType" to "ByTooltipMessage",
          "text" to input
  ))
}
