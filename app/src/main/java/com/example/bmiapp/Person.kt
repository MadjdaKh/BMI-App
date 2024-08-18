package com.example.bmiapp

data class Person(val weight: Double, val height: Double, val gender: Gender) {
    fun calculateBMI(): Double {
        return weight / ((height / 100) * (height / 100))
    }

    fun getBMICategory(): BMICategory {
        val bmi = calculateBMI()
        return when {
            bmi < 18.5 -> BMICategory.UNDERWEIGHT
            bmi < 24.9 -> BMICategory.NORMAL
            else -> BMICategory.OVERWEIGHT
        }
    }
}

enum class Gender {
    MALE, FEMALE
}

enum class BMICategory {
    UNDERWEIGHT, NORMAL, OVERWEIGHT
}
