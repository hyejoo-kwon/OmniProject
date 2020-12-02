package com.example.omniproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.mobile.client.AWSMobileClient


class AuthMainActivity : AppCompatActivity() {
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_main)
        context = this
        Log.d("hello", "AuthMainActivity 객체 생성")
        CommonAction.checkSession(this, true)
    }

    private fun _openFacebookLogin() {
        // Add code here
    }

    override fun onResume() {
        // Add code here
        super.onResume()

        val activityIntent = intent
        println(activityIntent.data)
        if (activityIntent.data != null && "omniproject" == activityIntent.data!!.scheme) {
            if (AWSMobileClient.getInstance().handleAuthResponse(activityIntent)) CommonAction.checkSession(this, true)
        }
    }

    fun openLogin(view: View?) {
        val intent = Intent(context, LoginActivity::class.java)
        Log.d("hello", "openLogin실행")
        startActivity(intent)
    }

    fun openRegistration(view: View?) {
        val intent = Intent(context, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun openFacebookLogin(view: View?) {
        _openFacebookLogin()
    }

    companion object {
        private val TAG = AuthMainActivity::class.java.simpleName
    }
}