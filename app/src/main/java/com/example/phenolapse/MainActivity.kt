package com.example.phenolapse

import java.util.*
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat


import com.androidhiddencamera.CameraConfig
import com.androidhiddencamera.CameraError
import com.androidhiddencamera.HiddenCameraActivity
import com.androidhiddencamera.HiddenCameraUtils
import com.androidhiddencamera.config.CameraFacing
import com.androidhiddencamera.config.CameraFocus
import com.androidhiddencamera.config.CameraImageFormat
import com.androidhiddencamera.config.CameraResolution
import com.androidhiddencamera.config.CameraRotation

class MainActivity : AppCompatActivity() {
    private val REQ_CODE_CAMERA_PERMISSION = 1253

    private var mCameraConfig: CameraConfig? = null

    private lateinit var imageView: ImageView
    private var photoFile: File? = null
    private val CAPTURE_IMAGE_REQUEST = 1
    private lateinit var mCurrentPhotoPath: String
    private val IMAGE_DIRECTORY_NAME = "VLEMONN"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val intent = Intent(this,CamActivity::class.java)
        startActivity(intent)


    }
}
