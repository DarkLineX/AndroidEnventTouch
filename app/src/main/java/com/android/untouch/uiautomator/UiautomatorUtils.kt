package com.android.untouch.uiautomator

import com.android.untouch.utils.Adb

object UiautomatorUtils {
    fun dumpXml(){
        Adb.execRootCmd("uiautomator dump /sdcard/ui.xml")
    }
}