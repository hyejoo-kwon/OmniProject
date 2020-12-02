package com.example.omniproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserState
import com.amazonaws.mobile.client.UserStateDetails


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
        Log.d("hello", "initCognito실행")
        if (AWSMobileClient.getInstance().configuration == null) {
            // Initialize user
            AWSMobileClient.getInstance().initialize(
                applicationContext,
                object : Callback<UserStateDetails> {
                    override fun onResult(userStateDetails: UserStateDetails) {
                        when (userStateDetails.userState) {
                            UserState.SIGNED_IN -> {
                                Log.e("TAG", "처음들어왔을때")// Open Main Activity
                                CommonAction.openMain(context!!)
                            }
                            UserState.SIGNED_OUT -> {
                                Log.d("hello", "initialize 로그아웃 상태")
                                CommonAction.openAuthMain(context!!)
                            }
                            else -> AWSMobileClient.getInstance().signOut()
                        }
                    }

                    override fun onError(e: Exception) {
                        Log.e("TAG", e.toString())
                    }
                })
        } else if (AWSMobileClient.getInstance().isSignedIn) {
            // Logined user
            Log.d("hello", "moblieclient null이고 로그인")
            CommonAction.openMain(context!!)
        } else {
            // Logouted user
            Log.d("hello", "moblieclient null이고 로그아")
            CommonAction.openAuthMain(context!!)
        }
    }

    companion object {
        private val TAG = SplashActivity::class.java.simpleName
    }
}