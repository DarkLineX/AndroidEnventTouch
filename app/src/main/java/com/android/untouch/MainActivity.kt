package com.android.untouch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.untouch.action.Auto
import com.android.untouch.databinding.ActivityMainBinding
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var t0: View? = null
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFloat()

        Handler(Looper.getMainLooper()).postDelayed({
            Auto.touch()
        },5000)

    }

    private fun showFloat() {
        EasyFloat.with(this)
            .setShowPattern(ShowPattern.ALL_TIME)
            .setSidePattern(SidePattern.RESULT_RIGHT)
            .setDragViewId(R.id.root_view)
            .setLayout(R.layout.float_view) {
                t0 = it.findViewById(R.id.float_v0)
                rootView = it.findViewById(R.id.root_view)
                t0!!.setOnClickListener{

                }
            }.show()
    }
}