@file:JvmName("_FinderRawMethods")
@file:JvmMultifileClass
package com.codecraft.flutter.utils.kotlin.finder

fun byImagePath(input: String): FlutterElement {
    return FlutterElement(mapOf(
            "finderType" to "ImageFinder",
            "image" to input
    ))
}