package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formatRadioGroup: RadioGroup = findViewById(R.id.formatRadioGroup)
        val quantityEditText: EditText = findViewById(R.id.quantityEditText)
        val okButton: Button = findViewById(R.id.okButton)

        okButton.setOnClickListener {
            val selectedFormatId = formatRadioGroup.checkedRadioButtonId
            if (selectedFormatId == -1) {
                Toast.makeText(this, "Выберите формат фотографии", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantityText = quantityEditText.text.toString()
            if (quantityText.isEmpty()) {
                Toast.makeText(this, "Введите количество фотографий", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = quantityText.toInt()
            val pricePerPhoto = when (selectedFormatId) {
                R.id.format_9x12 -> 100
                R.id.format_10x15 -> 150
                R.id.format_18x24 -> 300
                else -> 0
            }

            val totalPrice = pricePerPhoto * quantity

            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("TOTAL_PRICE", totalPrice)
            startActivity(intent)
        }
    }
}
