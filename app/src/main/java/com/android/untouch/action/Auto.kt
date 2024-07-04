package com.android.untouch.action

import com.android.untouch.utils.Adb
import com.android.untouch.utils.L
import com.blankj.utilcode.util.StringUtils
import java.lang.Long

object Auto {

    val EV_SYN: Int = 0 //(0x00) - 同步事件
    val EV_KEY : Int = 1 //EV_KEY (0x01) - 键盘事件
    val EV_REL : Int = 2 //EV_REL (0x02) - 相对轴事件
    val EV_ABS : Int = 3 //EV_ABS (0x03) - 绝对轴事件
    val EV_MSC : Int = 4 //EV_MSC (0x04) - 杂项事件
    val EV_SW : Int = 5 //EV_SW (0x05) - 开关事件
    val EV_LED : Int = 17 //EV_LED (0x11) - LED事件
    val EV_SND : Int = 18 //EV_SND (0x12) - 声音事件
    val EV_REP : Int = 10 //EV_REP (0x14) - 重复事件
    val EV_FF : Int = 21 //EV_FF (0x15) - 力反馈事件
    val EV_PWR : Int = 22 //EV_PWR (0x16) - 电源事件


    fun touch(){
         val cmd = input2Out("""
        /dev/input/event4: 0003 0039 000006d4   
        /dev/input/event4: 0001 014a 00000001
        /dev/input/event4: 0001 0145 00000001
        /dev/input/event4: 0003 0035 0000026c
        /dev/input/event4: 0003 0036 000006aa
        /dev/input/event4: 0003 0034 ffffffaa
        /dev/input/event4: 0000 0000 00000000
        /dev/input/event4: 0003 0039 ffffffff
        /dev/input/event4: 0001 014a 00000000
        /dev/input/event4: 0001 0145 00000000
        /dev/input/event4: 0000 0000 00000000
         """).trimIndent()
        L.e("cmd$cmd")
        Adb.execRootCmd(cmd)
    }

    private fun input2Out(inStr: String):String{
       val strings = inStr.replace(":","").split("\n")
        val sb = StringBuilder()
        for(s in strings){
            if(!StringUtils.isEmpty(s)){
                sb.append(line2(s.trimEnd().trimStart()))
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    private fun line2(inStr: String):String{
        val sb = StringBuilder()
        val strings2 =  inStr.split(" ")
        if(strings2.size==4){
            sb.append("sendevent ")
            sb.append(strings2[0])
            sb.append(" ")
            sb.append(strings2[1].toLong(16))
            sb.append(" ")
            sb.append(strings2[2].toLong(16))
            sb.append(" ")
            sb.append(strings2[3].toLong(16))
        }
        return sb.toString()
    }
}