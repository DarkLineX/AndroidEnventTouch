package com.android.untouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.android.untouch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.setOnClickListener {

        }

        Handler(Looper.getMainLooper()).postDelayed({
            stringFromJNI()
        },5000)

    }

    /**
     * A native method that is implemented by the 'untouch' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI()

    companion object {
        // Used to load the 'untouch' library on application startup.
        init {
            System.loadLibrary("untouch")
        }
    }
}