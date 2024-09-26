package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

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
    }
}
