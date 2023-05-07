package com.codecraft.flutter.utils.kotlin

import com.codecraft.flutter.utils.kotlin.finder.FlutterElement
import org.openqa.selenium.remote.FileDetector
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.regex.Pattern
import com.codecraft.flutter.utils.kotlin.finder.ancestor as _ancestor
import com.codecraft.flutter.utils.kotlin.finder.byButton as _byButton
import com.codecraft.flutter.utils.kotlin.finder.byImagePath as _byImagePath
import com.codecraft.flutter.utils.kotlin.finder.byRichText as _byRichText
import com.codecraft.flutter.utils.kotlin.finder.bySemanticsLabel as _bySemanticsLabel
import com.codecraft.flutter.utils.kotlin.finder.byTooltip as _byTooltip
import com.codecraft.flutter.utils.kotlin.finder.byType as _byType
import com.codecraft.flutter.utils.kotlin.finder.byValueKey as _byValueKey
import com.codecraft.flutter.utils.kotlin.finder.descendant as _descendant
import com.codecraft.flutter.utils.kotlin.finder.pageBack as _pageBack
import com.codecraft.flutter.utils.kotlin.finder.text as _text

public class FlutterFinder(driver: RemoteWebDriver) {
  private val driver = driver
  private val fileDetector = FileDetector({ _ -> null })
  fun ancestor(of: FlutterElement, matching: FlutterElement, matchRoot: Boolean = false, firstMatchOnly: Boolean = false): FlutterElement {
    val f = _ancestor(of, matching, matchRoot, firstMatchOnly)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun bySemanticsLabel(label: String): FlutterElement {
    val f = _bySemanticsLabel(label)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun bySemanticsLabel(label: Pattern): FlutterElement {
    val f = _bySemanticsLabel(label)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byTooltip(input: String): FlutterElement {
    val f = _byTooltip(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byType(input: String): FlutterElement {
    val f = _byType(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byValueKey(input: String): FlutterElement {
    val f = _byValueKey(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byValueKey(input: Int): FlutterElement {
    val f = _byValueKey(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun descendant(of: FlutterElement, matching: FlutterElement, matchRoot: Boolean = false, firstMatchOnly: Boolean = false): FlutterElement {
    val f = _descendant(of, matching, matchRoot, firstMatchOnly)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun pageBack(): FlutterElement {
    val f = _pageBack()
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun text(input: String): FlutterElement {
    val f = _text(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byButton(): FlutterElement {
    val f = _byButton()
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byRichText(input: String): FlutterElement {
    val f = _byRichText(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
  fun byImagePath(input: String): FlutterElement {
    val f = _byImagePath(input)
    f.setParent(driver)
    f.setFileDetector(fileDetector)
    return f
  }
}