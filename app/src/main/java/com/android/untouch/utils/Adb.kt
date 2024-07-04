package com.android.untouch.utils

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException


object Adb {
    private const val TAG = "AdbTAG"

    fun execRootCmd(cmd: String): String? {
        var result: String? = ""
        var dos: DataOutputStream? = null
        var dis: DataInputStream? = null
        try {
            val p = Runtime.getRuntime().exec("su") // 经过Root处理的android系统即有su命令
            dos = DataOutputStream(p.outputStream)
            dis = DataInputStream(p.inputStream)
            //L.i(TAG, cmd)
            dos.writeBytes("$cmd\n")
            dos.flush()
            dos.writeBytes("exit\n")
            dos.flush()
            var line: String? = null
            while (dis.readLine().also { line = it } != null) {
                result += line
            }
            p.waitFor()
        } catch (e: Exception) {
            //e.printStackTrace()
            L.e("adb exp ${e.message}")
        } finally {
            if (dos != null) {
                try {
                    dos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (dis != null) {
                try {
                    dis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return result
    }

    //app 相关操作
    fun stopApp(pName:String){
        execRootCmd("am force-stop $pName")
    }

    fun stopSelf(){
        stopApp("com.android.coinsbot")
    }

    fun lunchApp(pName:String){
        execRootCmd("am start -n $pName")
    }

    fun getTopActivity():String?{
        return execRootCmd("dumpsys window windows | grep mFocusedActivity")
    }

    //getevent -t 需要持续读取


}