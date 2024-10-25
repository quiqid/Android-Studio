package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим кнопку по ID и устанавливаем слушатель нажатий
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Создаем Intent для перехода на SecondActivity
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // LR1

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

        // LR1
        // LR2

        // Связываем элементы интерфейса
        val thresholdInput = findViewById<EditText>(R.id.thresholdInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val result2Text = findViewById<TextView>(R.id.resultText2)

        // Обработчик нажатия кнопки
        calculateButton.setOnClickListener {
            // Получаем введённое пороговое значение
            val thresholdString = thresholdInput.text.toString()
            if (thresholdString.isNotEmpty()) {
                val threshold = thresholdString.toDouble()

                // Вызываем функцию для вычисления суммы
                val result = calculateFractionSum(threshold)

                // Выводим результат
                result2Text.text = "Сумма: ${result.sum}\n" +
                        "Последнее слагаемое: ${result.lastTerm}\n" +
                        "Количество итераций: ${result.iterations}"
            } else {
                result2Text.text = "Пожалуйста, введите порог."
            }
        }

        // LR2
        // LR3

        // Связываем элементы интерфейса с кодом
        val inputString = findViewById<EditText>(R.id.inputString)
        val inputOldChar = findViewById<EditText>(R.id.inputOldChar)
        val inputNewChar = findViewById<EditText>(R.id.inputNewChar)
        val replaceButton = findViewById<Button>(R.id.replaceButton)
        val result3Text = findViewById<TextView>(R.id.resultText)

        // Обработчик нажатия кнопки
        replaceButton.setOnClickListener {
            // Получаем значения, введённые пользователем
            val str = inputString.text.toString()
            val oldChar = inputOldChar.text.toString()
            val newChar = inputNewChar.text.toString()

            // Проверяем, что старый символ и новый символ введены корректно
            if (oldChar.length == 1 && newChar.length == 1) {
                // Выполняем замену символа
                val result = str.replace(oldChar[0], newChar[0])

                // Выводим результат
                result3Text.text = "Результат: $result"
            } else {
                result3Text.text = "Пожалуйста, введите один символ для замены и новый символ."
            }
        }

        // LR3
    }

    // Функция для вычисления факториала LR2
    private fun factorial(n: Int): Long {
        return if (n == 0 || n == 1) 1 else n * factorial(n - 1)
    }

    // Структура для хранения результатов вычисления LR2
    data class CalculationResult(val sum: Double, val lastTerm: Double, val iterations: Int)

    // Функция для вычисления суммы дробей LR2
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
