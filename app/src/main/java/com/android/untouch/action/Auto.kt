package com.android.untouch.action

import com.android.untouch.action.Recorder.ACTION_CLICK_1
import com.android.untouch.action.Recorder.inToOut
import com.android.untouch.utils.Adb

object Auto {
    fun click(){
         val cmd = inToOut(ACTION_CLICK_1).trimIndent()
        Adb.execRootCmd(cmd)
    }

}