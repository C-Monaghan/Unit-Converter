package com.example.unitconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unitconverter.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { (calculateConversion()) }
    }

    // Function to calculate the conversion once the user clicks calculate
    private fun calculateConversion() {
        val stringInTextField = binding.measuredUnitEditText.text.toString()
        val measure = stringInTextField.toDoubleOrNull()
        var convertedAmount: Double

        // If user leaves text entry field blank
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

        // Adds the necessary units to the selected conversion
        val unit: String = when(binding.unitOptions.checkedRadioButtonId) {
            R.id.celsius -> "°F"
            R.id.fahrenheit -> "°C"
            R.id.millilitres -> "fl oz"
            R.id.fluid_ounces -> "ml"
            R.id.grams -> "cups"
            else -> "grams"
        }

        // Rounds conversion up if option is selected
        if (binding.roundSwitch.isChecked) {
            convertedAmount = convertedAmount.roundToInt().toDouble()
        }

        displayConversion(convertedAmount, unit)
    }

    // Function to display the converted output
    @SuppressLint("SetTextI18n")
    private fun displayConversion(convertedAmount: Double, unit: String) {
        val convertedAmountDecimal = String.format("%.2f" , convertedAmount)
        binding.result.text = "Converted Amount: $convertedAmountDecimal $unit"
    }
}