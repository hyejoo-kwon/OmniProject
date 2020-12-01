package com.example.socialandroidapp

import android.Manifest
import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView? = null

    //private PostAdapter mAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CommonAction.checkSession(this, false)
        checkPermission()
        val settingBtn = findViewById<Button>(R.id.setting)
        val writeBtn = findViewById<Button>(R.id.writing)
        settingBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent)
        }
        writeBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                Log.e(TAG, "permission error - ")
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                    MY_PERMISSION_CAMERA
                )
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.INTERNET),
                1
            )
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSION_CAMERA -> {
                var i = 0
                while (i < grantResults.size) {
                    if (grantResults[i] < 0) {
                        return
                    }
                    i++
                }
            }
        }
    }

    companion object {
        private const val MY_PERMISSION_CAMERA = 1111
        private const val TAG = "tko-main"
    }
}