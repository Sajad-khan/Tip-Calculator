package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    // instance of ActivityMainBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Click Lister to button
        binding.calculateButton.setOnClickListener {calculateTip()}
    }
    // calculates tip according to the options you choose
    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull() ?: return
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage

        if(binding.roundUpSwitch.isChecked){
            tip = ceil(tip)
        }
        displayTip(tip)
    }
    // displays tip
    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}