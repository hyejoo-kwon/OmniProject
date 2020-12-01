package com.example.omniproject

import android.content.Context
import android.content.Intent
import android.util.Log
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.UserState


object CommonAction {
    fun openMain(context: Context) {
        openActivityOnTop(context, MainActivity::class.java)
    }

    fun openAuthMain(context: Context) {
        openActivityOnTop(context, AuthMainActivity::class.java)
    }

    fun openSplash(context: Context) {
        openActivityOnTop(context, SplashActivity::class.java)
    }

    fun openActivityOnTop(
        context: Context,
        targetClass: Class<*>?
    ) {
        val intent = Intent(context, targetClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun checkSession(context: Context, moveToMain: Boolean) {
        // Add code here
        AWSMobileClient.getInstance().addUserStateListener { userStateDetails ->
            when (userStateDetails.userState) {
                UserState.SIGNED_IN -> {
                    Log.i("checkSession", "user signed in")
                    if (moveToMain) openMain(context)
                }
                else -> {
                    Log.i("checkSession", "unsupported")
                    openSplash(context)
                }
            }
        }
    }
}