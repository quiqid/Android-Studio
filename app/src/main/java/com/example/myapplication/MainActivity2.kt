package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Build


class MainActivity2 : AppCompatActivity() {

    private lateinit var androidInfo: TextView
    private lateinit var textViewAnswer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        androidInfo = findViewById(R.id.textViewApi)
        textViewAnswer = findViewById(R.id.textViewCheat)

        val questionIndex = intent.getIntExtra("questionIndex", 0)
        val correctAnswer = intent.getBooleanExtra("correctAnswer", false)

        textViewAnswer.text = "Правильный ответ: $correctAnswer"

        val androidVersion = getAndroidVersion()
        androidInfo.text = androidVersion

    }

    private fun getAndroidVersion(): String {
        return "Android Version: ${Build.VERSION.RELEASE}, API Level: ${Build.VERSION.SDK_INT}"
    }
}