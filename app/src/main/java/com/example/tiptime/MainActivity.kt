package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    // calculate the tip from the cost in the EditText and the percent in the radio button
    private fun calculateTip() {
        // getting the text for cost of service from the EditText and converting to string
        val stringInTextField = binding.costOfService.text.toString()
        // convert text to double or Null, returns null if no input or empty in EditText Field
        val cost = stringInTextField.toDoubleOrNull()

        // set tipResult TextView to empty string and return if cost is null
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        // find the checked radio button id
        // get the percentage tip from the selected radio button
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        // calculate the tip, using var instead of val because it may be rounded off depending on the switch
        var tip = tipPercent * cost
        // check switch is on/off ,if switch is checked then round off the tip
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        // formatting the tip to the currency
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // setting the TextView to the formatted tip
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}