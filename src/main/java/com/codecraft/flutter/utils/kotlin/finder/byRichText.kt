@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun byRichText(input: String): FlutterElement {
    return FlutterElement(mapOf(
            "finderType" to "RichTextFinder",
            "text" to input
    ))
}