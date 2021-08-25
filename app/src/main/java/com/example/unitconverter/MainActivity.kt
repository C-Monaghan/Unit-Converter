package com.example.unitconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { (calculateConversion()) }
    }

    private fun calculateConversion() {
        val stringInTextField = binding.measuredUnit.text.toString()
        val measure = stringInTextField.toDoubleOrNull()
        var convertedAmount: Double

        if (measure == null) {
            displayConversion(0.0,"")
            return
        } else {
            convertedAmount = when (binding.unitOptions.checkedRadioButtonId) {
                R.id.celsius -> (measure * 1.80) + 32.0
                R.id.fahrenheit -> (measure - 32.0) / 1.80
                R.id.millilitres -> measure / 29.57
                R.id.fluid_ounces -> measure * 29.57
                R.id.grams -> measure / 201.6
                else -> measure * 201.6
            }
        }

        val unit: String = when(binding.unitOptions.checkedRadioButtonId) {
            R.id.celsius -> "°F"
            R.id.fahrenheit -> "°C"
            R.id.millilitres -> "fl oz"
            R.id.fluid_ounces -> "ml"
            R.id.grams -> "cups"
            else -> "grams"
        }

        if (binding.roundUpSwitch.isChecked) {
            convertedAmount = kotlin.math.ceil(convertedAmount)
        }

        displayConversion(convertedAmount, unit)
    }

    @SuppressLint("SetTextI18n")
    private fun displayConversion(convertedAmount: Double, unit: String) {
        binding.result.text = "Converted Amount: $convertedAmount $unit"
    }
}