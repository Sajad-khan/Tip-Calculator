package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val initialTip = 0.00
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayTip(initialTip)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val inputNumber = binding.inputValueEditText.text.toString().toDoubleOrNull()
        if(inputNumber==null){
            displayTip(initialTip)
            return
        }

        var tip = when (binding.serviceOption.checkedRadioButtonId) {
            R.id.great_option -> 0.18 * inputNumber
            R.id.good_option -> 0.15 * inputNumber
            else -> 0.12 * inputNumber
        }
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text =getString(R.string.tip_amount, formattedTip)
    }
}