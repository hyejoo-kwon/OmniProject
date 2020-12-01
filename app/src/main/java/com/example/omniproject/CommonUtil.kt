package com.example.omniproject

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object CommonUtil{
    fun makeToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}