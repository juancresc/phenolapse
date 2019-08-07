package com.example.phenolapse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_click_me = findViewById(R.id.btn_agregar_alarma) as Button

        btn_click_me.setOnClickListener {
            val newFragment = TimePickerFragment()
            newFragment.show(fragmentManager, "TimePicker")


        }

    }
}
