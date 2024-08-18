package com.example.bmiapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etWeight: EditText = findViewById(R.id.etWeight)
        val etHeight: EditText = findViewById(R.id.etHeight)
        val rgGender: RadioGroup = findViewById(R.id.rgGender)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)
        val tvResult: TextView = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val weight = etWeight.text.toString().toDoubleOrNull()
            val height = etHeight.text.toString().toDoubleOrNull()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId == R.id.rbMale) Gender.MALE else Gender.FEMALE

            if (weight != null && height != null) {
                val person = Person(weight, height, gender)
                val bmi = person.calculateBMI()
                val category = person.getBMICategory()

                val resultText = "Your BMI is %.2f and you are %s".format(bmi, category.name.lowercase())
                tvResult.text = resultText

                when (category) {
                    BMICategory.UNDERWEIGHT, BMICategory.OVERWEIGHT -> tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                    BMICategory.NORMAL -> tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
                }
            } else {
                tvResult.text = "Please enter valid weight and height"
                tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            }
        }
    }
}
