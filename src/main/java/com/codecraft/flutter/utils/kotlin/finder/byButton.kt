@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun byButton(): FlutterElement {
  return FlutterElement(mapOf(
          "finderType" to "FloatingActionButtonFinder"
  ))
}
