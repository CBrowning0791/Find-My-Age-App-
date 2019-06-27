package com.example.findmyage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dateBox.addTextChangedListener(MaskWatcher("##/##/####"))
        dateBox.setText("12/31/1900")
        var dateList = mutableListOf<LocalDate>()
        var date : LocalDate

        val date_click = findViewById(R.id.dateBox) as TextView
        date_click.setOnClickListener{
            dateBox.setText("")
        }

        val button_click = findViewById(R.id.enterButton) as Button

        button_click.setOnClickListener{
            dateList = this.validateDate(dateBox.text.toString())
            if(dateList.count() == 1)
            {
                date = dateList.removeAt(0)
                resultText.setText(calculateAge(date))
            }
        }


    }

    fun validateDate(inputDate: String): MutableList<LocalDate>{
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val resultText = findViewById(R.id.resultText) as TextView
        val list = mutableListOf<LocalDate>()
        try{
            val formattedDate = LocalDate.parse(inputDate, formatter)
            list.add(formattedDate)
        }
        catch (e: java.time.format.DateTimeParseException)
        {
            resultText.setText("Invalid Date. Enter Date In dd/MM/yyyy Format")
            resultText.setTextColor(Color.parseColor("#FF0000"))
        }
        return list
    }

    fun calculateAge(inputDate: LocalDate): String{
        val today = LocalDateTime.now().toLocalDate()
        val age = inputDate.until(today)
        return "You are ${age.years} years, ${age.months} months, and ${age.days} days old!"
    }

}



