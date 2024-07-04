package com.android.untouch.action

import com.blankj.utilcode.util.StringUtils

object Recorder {

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

    //0039 新坐标 id 每次递增加1
    //014a  BTN_TOUCH 触摸屏按下事件 1
    //0145  BTN_TOOL_FINGER 检测到手指触摸 1
    //0035 X坐标
    //0036 Y坐标
    //0034 触摸压力值
    //014a 触摸屏松开事件 0
    //0145 手指触摸结束 0
    //0003 0030 00000060: ABS_MT_ORIENTATION 事件，表示触摸点的 X 方向速度是 96。
    //0003 0031 00000050: ABS_MT_TOUCH_MAJOR 事件，表示触摸点的 Y 方向速度是 80。

    const val ACTION_CLICK_1 = """
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
         """
    const val ACTION_CLICK_2 = """
        /dev/input/event4: 0003 0039 00000748
        /dev/input/event4: 0001 014a 00000001
        /dev/input/event4: 0001 0145 00000001
        /dev/input/event4: 0003 0035 000003de
        /dev/input/event4: 0003 0036 000005dd
        /dev/input/event4: 0003 0031 00000050
        /dev/input/event4: 0000 0000 00000000
        /dev/input/event4: 0003 0039 ffffffff
        /dev/input/event4: 0001 014a 00000000
        /dev/input/event4: 0001 0145 00000000
        /dev/input/event4: 0000 0000 00000000
        """
    const val ACTION_CLICK_3 = """
        /dev/input/event4: 0003 0039 00000014
        /dev/input/event4: 0001 014a 00000001
        /dev/input/event4: 0001 0145 00000001
        /dev/input/event4: 0003 0035 000002b4
        /dev/input/event4: 0003 0036 00000917
        /dev/input/event4: 0003 0031 00000030
        /dev/input/event4: 0003 0034 0000004b
        /dev/input/event4: 0000 0000 00000000
        /dev/input/event4: 0003 0039 ffffffff
        /dev/input/event4: 0001 014a 00000000
        /dev/input/event4: 0001 0145 00000000
        /dev/input/event4: 0000 0000 00000000
    """


    const val ACTION_CLICK_4 = """
        /dev/input/event4: 0003 0039 00000019
        /dev/input/event4: 0001 014a 00000001
        /dev/input/event4: 0001 0145 00000001
        /dev/input/event4: 0003 0035 00000193
        /dev/input/event4: 0003 0036 00000b64
        /dev/input/event4: 0003 0030 00000040
        /dev/input/event4: 0003 0034 00000057
        /dev/input/event4: 0000 0000 00000000
        /dev/input/event4: 0003 0039 ffffffff
        /dev/input/event4: 0001 014a 00000000
        /dev/input/event4: 0001 0145 00000000
        /dev/input/event4: 0000 0000 00000000
    """


    const val ACTION_LONG_CLICK = """
        /dev/input/event4: 0003 0039 0000001c
        /dev/input/event4: 0001 014a 00000001
        /dev/input/event4: 0001 0145 00000001
        /dev/input/event4: 0003 0035 00000508
        /dev/input/event4: 0003 0036 00000b73
        /dev/input/event4: 0003 0030 00000060
        /dev/input/event4: 0003 0031 00000050
        /dev/input/event4: 0003 0034 0000002d
        /dev/input/event4: 0000 0000 00000000
        
        //暂停
        
        /dev/input/event4: 0003 0039 ffffffff
        /dev/input/event4: 0001 014a 00000000
        /dev/input/event4: 0001 0145 00000000
        /dev/input/event4: 0000 0000 00000000
    """

    fun click(){

    }


    fun inToOut(inStr: String):String{
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