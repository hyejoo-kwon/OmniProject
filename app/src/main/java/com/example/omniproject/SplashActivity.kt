package com.example.omniproject

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        context = this
        _initCognito()
    }

    private fun _initCognito() {
        // Add code here
    }

    companion object {
        private val TAG = SplashActivity::class.java.simpleName
    }
}