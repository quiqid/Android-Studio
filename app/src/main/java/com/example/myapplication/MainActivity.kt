package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        Pair("Живут ли пингвины в тропических лесах?", false),
        Pair("Является ли вода химическим соединением?", true),
        Pair("Является ли Луна планетой?", false),
        Pair("Гепард — это самое крупное быстрое млекопитающее на планете?", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTrue = findViewById(R.id.buttonTrue)
        buttonFalse = findViewById(R.id.buttonFalse)
        questionTextView = findViewById(R.id.textViewQuestion)
        answerTextView = findViewById(R.id.textViewAnswer)
        buttonNext = findViewById(R.id.buttonNext)
        buttonCheat = findViewById(R.id.buttonCheat)

        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex", 0)
            correctAnswersCount = savedInstanceState.getInt("correctAnswersCount", 0)
            cheatCount = savedInstanceState.getInt("cheatCount", 0)
            isAnswered = savedInstanceState.getBoolean("isAnswered", false)
            answerText = savedInstanceState.getString("answerText", "")
        }

        updateQuestion()

        buttonTrue.setOnClickListener { checkAnswer(true) }
        buttonFalse.setOnClickListener { checkAnswer(false) }
        buttonNext.setOnClickListener { nextQuestion() }
        buttonCheat.setOnClickListener { useCheat() }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (isAnswered) return

        val correctAnswer = questions[currentQuestionIndex].second

        if (userAnswer == correctAnswer) {
            answerTextView.text = "Правильный ответ"
            correctAnswersCount++
            answerText = "Правильный ответ"
        } else {
            answerTextView.text = "Неправильный ответ"
            answerText = "Неправильный ответ"
        }

        isAnswered = true

        buttonTrue.visibility = View.GONE
        buttonFalse.visibility = View.GONE
        buttonCheat.visibility = View.GONE
        buttonNext.visibility = View.VISIBLE
    }

    private fun updateQuestion() {
        if (currentQuestionIndex < questions.size) {
            questionTextView.text = questions[currentQuestionIndex].first

            if (isAnswered) {
                answerTextView.text = answerText
                buttonNext.visibility = View.VISIBLE
                buttonTrue.visibility = View.GONE
                buttonFalse.visibility = View.GONE
                buttonCheat.visibility = View.GONE
            } else {
                answerTextView.text = ""
                buttonTrue.visibility = View.VISIBLE
                buttonFalse.visibility = View.VISIBLE
                buttonCheat.visibility = View.VISIBLE
                buttonNext.visibility = View.GONE
            }
        } else {
            showFinalScore()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentQuestionIndex", currentQuestionIndex)
        outState.putInt("correctAnswersCount", correctAnswersCount)
        outState.putInt("cheatCount", cheatCount)
        outState.putBoolean("isAnswered", isAnswered)
        outState.putString("answerText", answerText)
    }

    private fun nextQuestion() {
        if (currentQuestionIndex >= questions.size) {
            currentQuestionIndex = 0
            correctAnswersCount = 0
            cheatCount = 0
            isAnswered = false
            answerTextView.text = ""
            buttonNext.text = "Next"
            questionTextView.visibility = View.VISIBLE
        } else {
            currentQuestionIndex++
            isAnswered = false
        }

        updateQuestion()
    }

    private fun showFinalScore() {
        questionTextView.visibility = View.GONE
        answerTextView.text = "Количество правильных ответов: $correctAnswersCount"

        buttonTrue.visibility = View.GONE
        buttonFalse.visibility = View.GONE
        buttonCheat.visibility = View.GONE

        buttonNext.text = "Restart"
        buttonNext.visibility = View.VISIBLE
    }

    private fun useCheat() {
        if (cheatCount < 3) {
            val next = Intent(this, MainActivity2::class.java)
            next.putExtra("questionIndex", currentQuestionIndex)
            next.putExtra("correctAnswer", questions[currentQuestionIndex].second)
            startActivity(next)
            cheatCount++
        } else {
            Toast.makeText(this, "Вы исчерпали лимит подсказок!", Toast.LENGTH_SHORT).show()
        }
    }
}