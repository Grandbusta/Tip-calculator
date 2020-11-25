package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcBtn.setOnClickListener{calculateTip()}
    }
    private fun calculateTip(){
        val cost=binding.costOfService.text.toString().toDoubleOrNull()
        if(cost==null){
            binding.tipResult.text=""
            return
        }
        val tipPercent=when(binding.tipOptions.checkedRadioButtonId){
            R.id.amazing->0.20
            R.id.good->0.15
            else->0.10
        }
        var tip=tipPercent*cost
        val roundUp=binding.tipSwitch.isChecked
        if(roundUp){
            tip=kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount, formattedTip)
    }
}