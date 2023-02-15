package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<Button>(R.id.buttonDatePicker)
        btn.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view : View ){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)
       val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month+1}/$year"
            var selectdate = findViewById<TextView>(R.id.tvSelectedDate)
            selectdate.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDatetoMinutes = currentDate!!.time/60000

            val differenceInMinutes = currentDatetoMinutes - selectedDateInMinutes
            var updateDate = findViewById<TextView>(R.id.tvSelectedDateinMinutes)
            updateDate.setText(differenceInMinutes.toString())

        }   ,year
            ,month
            ,dayOfMonth
        )
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}