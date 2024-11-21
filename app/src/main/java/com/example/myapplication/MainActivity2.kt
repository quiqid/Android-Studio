package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val paymentTextView: TextView = findViewById(R.id.paymentTextView)
        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 0)

        paymentTextView.text = "Оплатить $totalPrice рублей."
    }
}
