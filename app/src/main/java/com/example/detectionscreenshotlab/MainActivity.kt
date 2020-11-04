package com.example.detectionscreenshotlab

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), ScreenshotDetectionDelegate.ScreenshotDetectionListener {

    companion object {
        const val TAG = "MainActivity"
    }

    private val screenshotDetectionDelegate = ScreenshotDetectionDelegate(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val window = this.window
//        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }

    override fun onStart() {
        super.onStart()
        screenshotDetectionDelegate.startScreenshotDetection()
    }

    override fun onStop() {
        super.onStop()
        screenshotDetectionDelegate.stopScreenshotDetection()
    }

    override fun onScreenCaptured(path: String?) {
        findViewById<TextView>(R.id.tvScreenshotPath).text = path ?: ""
        path?.let {
            Log.d(TAG, path)
        }
    }

    override fun onScreenCapturedWithDeniedPermission() {
        Log.d(TAG, "onScreenCapturedWithDeniedPermission")
    }
}
