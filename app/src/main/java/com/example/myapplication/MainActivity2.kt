package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    data class Question(val text: String, val answer: Boolean)

    // Список вопросов
    private val questionBank = listOf(
        Question("The Earth is flat", false),
        Question("The Pacific Ocean is the largest ocean on Earth", true),
        Question("The Moon is closer to the Sun than the Earth", false)
    )
    private var currentIndex = 0
    private var score = 0
    private var isAnswered = false

    // Ключ для сохранения состояния
    private val KEY_INDEX = "index"
    private val KEY_SCORE = "score"
    private val KEY_ANSWERED = "answered"

    // UI элементы
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Восстановление состояния
        currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        score = savedInstanceState?.getInt(KEY_SCORE, 0) ?: 0
        isAnswered = savedInstanceState?.getBoolean(KEY_ANSWERED, false) ?: false

        // Инициализация UI элементов
        questionTextView = findViewById(R.id.question_text_view)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
    }
}