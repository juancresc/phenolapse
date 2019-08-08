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

class CamActivity : HiddenCameraActivity() {
    private val REQ_CODE_CAMERA_PERMISSION = 1253

    private var mCameraConfig: CameraConfig? = null

    private lateinit var imageView: ImageView
    private var photoFile: File? = null
    private val CAPTURE_IMAGE_REQUEST = 1
    private lateinit var mCurrentPhotoPath: String
    private val IMAGE_DIRECTORY_NAME = "VLEMONN"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_last)

        val btn_click_me = findViewById(R.id.btn_agregar_alarma) as Button

        btn_click_me.setOnClickListener {
            val newFragment = TimePickerFragment()
            newFragment.show(fragmentManager, "TimePicker")


        }


        val btn_captura = findViewById(R.id.btn_captura) as Button



        var mCameraConfig = CameraConfig()
            .getBuilder(this)
            .setCameraFacing(CameraFacing.REAR_FACING_CAMERA)
            .setCameraResolution(CameraResolution.HIGH_RESOLUTION)
            .setImageFormat(CameraImageFormat.FORMAT_JPEG)
            .setCameraFocus(CameraFocus.AUTO)
            .build()


        //Check for the camera permission for the runtime
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            //Start camera preview
            startCamera(mCameraConfig)
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                REQ_CODE_CAMERA_PERMISSION
            )
        }

        //Take a picture
        btn_captura.setOnClickListener(View.OnClickListener {
            //Take picture using the camera without preview.
            Toast.makeText(this, "hola charola", Toast.LENGTH_LONG).show()
            takePicture()
        })

    }
    override fun onImageCapture(imageFile: File) {

        // Convert file to bitmap.
        // Do something.
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath, options)

        //Display the image to the image view
        (findViewById(R.id.image_last) as ImageView).setImageBitmap(bitmap)
    }

    override fun onCameraError(@CameraError.CameraErrorCodes errorCode: Int) {
        when (errorCode) {
            CameraError.ERROR_CAMERA_OPEN_FAILED ->
                //Camera open failed. Probably because another application
                //is using the camera
                Toast.makeText(this, R.string.error_cannot_open, Toast.LENGTH_LONG).show()
            CameraError.ERROR_IMAGE_WRITE_FAILED ->
                //Image write failed. Please check if you have provided WRITE_EXTERNAL_STORAGE permission
                Toast.makeText(this, R.string.error_cannot_write, Toast.LENGTH_LONG).show()
            CameraError.ERROR_CAMERA_PERMISSION_NOT_AVAILABLE ->
                //camera permission is not available
                //Ask for the camera permission before initializing it.
                Toast.makeText(this, R.string.error_cannot_get_permission, Toast.LENGTH_LONG).show()
            CameraError.ERROR_DOES_NOT_HAVE_OVERDRAW_PERMISSION ->
                //Display information dialog to the user with steps to grant "Draw over other app"
                //permission for the app.
                HiddenCameraUtils.openDrawOverPermissionSetting(this)
            CameraError.ERROR_DOES_NOT_HAVE_FRONT_CAMERA -> Toast.makeText(
                this,
                R.string.error_not_having_camera,
                Toast.LENGTH_LONG
            ).show()
        }
    }


}
