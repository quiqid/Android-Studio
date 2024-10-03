package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputChar = findViewById<EditText>(R.id.inputChar)
        val checkButton = findViewById<Button>(R.id.checkButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        checkButton.setOnClickListener {
            val input = inputChar.text.toString()

            if (input.length != 1) {
                Toast.makeText(this, "Пожалуйста, введите один символ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val char = input[0]

            // Проверка, является ли символ латинской прописной буквой
            if (char in 'A'..'Z') {
                when (char) {
                    'L', 'M', 'K', 'D' -> resultTextView.text = "Это согласные буквы"
                    else -> resultTextView.text = "Возможно, это гласные буквы"
                }
            } else {
                Toast.makeText(this, "Символ не является латинской прописной буквой", Toast.LENGTH_SHORT).show()
                resultTextView.text = ""
            }
        }

        // Связываем элементы интерфейса
        val thresholdInput = findViewById<EditText>(R.id.thresholdInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        // Обработчик нажатия кнопки
        calculateButton.setOnClickListener {
            // Получаем введённое пороговое значение
            val thresholdString = thresholdInput.text.toString()
            if (thresholdString.isNotEmpty()) {
                val threshold = thresholdString.toDouble()

                // Вызываем функцию для вычисления суммы
                val result = calculateFractionSum(threshold)

                // Выводим результат
                resultText.text = "Сумма: ${result.sum}\n" +
                        "Последнее слагаемое: ${result.lastTerm}\n" +
                        "Количество итераций: ${result.iterations}"
            } else {
                resultText.text = "Пожалуйста, введите порог."
            }
        }
    }

    // Функция для вычисления факториала
    private fun factorial(n: Int): Long {
        return if (n == 0 || n == 1) 1 else n * factorial(n - 1)
    }

    // Структура для хранения результатов вычисления
    data class CalculationResult(val sum: Double, val lastTerm: Double, val iterations: Int)

    // Функция для вычисления суммы дробей
    private fun calculateFractionSum(threshold: Double): CalculationResult {
        var sum = 0.0
        var n = 1 // Начальное нечетное число
        var term: Double
        var iterations = 0

        do {
            // Вычисляем текущее слагаемое
            term = 1.0 / factorial(n)
            sum += term
            iterations++
            n += 2 // Переход к следующему нечетному числу
        } while (abs(term) > threshold) // Продолжаем пока слагаемое больше порога

        return CalculationResult(sum, term, iterations)
    }
}
