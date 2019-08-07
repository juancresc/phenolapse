package com.example.phenolapse

import com.example.phenolapse.R
import android.app.Dialog
import android.app.DialogFragment
import android.app.Fragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.Calendar


/**
 * A simple [Fragment] subclass.
 */
class TimePickerFragment:DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState:Bundle?):Dialog {
        super.onCreate(savedInstanceState?: Bundle())

        //Use the current time as the default values for the time picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        //Create and return a new instance of TimePickerDialog
        return TimePickerDialog(
            activity, this, hour, minute,
            DateFormat.is24HourFormat(activity))
    }
        override fun onTimeSet(view:TimePicker, hourOfDay:Int, minute:Int) {
            var hora =  "Hour : " + hourOfDay.toString() + "\nMinute : " + minute.toString()

            val linearLayout = activity.findViewById<LinearLayout>(R.id.layout_alarms)

            // Create Button Dynamically
            val btnShow = Button(activity)
            btnShow.setText(hora )
            btnShow.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            //btnShow.setOnClickListener { Toast.makeText(activity, R.string.welcome_message, Toast.LENGTH_LONG).show() }
            // Add Button to LinearLayout
            linearLayout?.addView(btnShow)

    }



}