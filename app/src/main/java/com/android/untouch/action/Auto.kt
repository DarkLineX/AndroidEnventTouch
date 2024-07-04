package com.android.untouch.action

import com.android.untouch.action.Recorder.ACTION_CLICK
import com.android.untouch.utils.Adb
import com.blankj.utilcode.util.StringUtils

object Auto {
    fun click(){
         val cmd = inToOut(ACTION_CLICK).trimIndent()
        Adb.execRootCmd(cmd)
    }
    private fun inToOut(inStr: String):String{
       val strings = inStr.replace(":","").split("\n")
        val sb = StringBuilder()
        for(s in strings){
            if(!StringUtils.isEmpty(s)){
                sb.append(lineToHex(s.trimEnd().trimStart()))
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    private fun lineToHex(inStr: String):String{
        val sb = StringBuilder()
        val listString =  inStr.split(" ")
        if(listString.size==4){
            sb.append("sendevent ")
            sb.append(listString[0])
            sb.append(" ")
            sb.append(listString[1].toLong(16))
            sb.append(" ")
            sb.append(listString[2].toLong(16))
            sb.append(" ")
            sb.append(listString[3].toLong(16))
        }
        return sb.toString()
    }
}