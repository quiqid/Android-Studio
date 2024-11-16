package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var questionTextView: TextView
    private lateinit var answerTextView: TextView
    private lateinit var buttonNext: Button
    private lateinit var buttonCheat: Button

    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0
    private var cheatCount = 0
    private var isAnswered = false
    private var answerText = ""

    private val questions = listOf(
        Pair("Все медузы являются ядовитыми?", false),
        Pair("Птицы являются потомками динозавров?", true),
        Pair("Вода кипит при температуре 100 градусов Цельсия на любой высоте?", false),
        Pair("Слон — это самое крупное сухопутное животное на планете?", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}