package com.android.untouch.utils

import android.util.Log


object L {
    private const val Tag = "UnTouchTAG"

    fun e(log:String){
        Log.e(Tag,log)
    }

    fun e(tag:String,log:String){
        Log.e("$Tag $tag",log)
    }

    fun e(tag:String,log:String,th: Throwable){
        Log.e("$Tag $tag", "$log $th")
    }

    fun i(log:String){
        Log.i(Tag,log)
    }

    fun i(tag:String,log:String){
        Log.i("$Tag $tag",log)
    }

    fun d(log:String){
        Log.d(Tag,log)
    }

    fun d(tag:String,log:String){
        Log.d("$Tag $tag",log)
    }

    fun w(log:String){
        Log.w(Tag,log)
    }

    fun w(tag:String,log:String){
        Log.w("$Tag $tag",log)
    }
}