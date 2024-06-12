package com.example.calculadora_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora_kotlin.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var formula: String = ""
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set button click listeners
        binding.clear.setOnClickListener { clear() }
        binding.plus.setOnClickListener { appendOperator("+") }
        binding.menos.setOnClickListener { appendOperator("-") }
        binding.multiplica.setOnClickListener { appendOperator("*") }
        binding.divide.setOnClickListener { appendOperator("/") }
        binding.igual.setOnClickListener { calculateResult() }

        binding.um.setOnClickListener { appendNumber("1") }
        binding.dois.setOnClickListener { appendNumber("2") }
        binding.tres.setOnClickListener { appendNumber("3") }
        binding.quatro.setOnClickListener { appendNumber("4") }
        binding.cinco.setOnClickListener { appendNumber("5") }
        binding.seis.setOnClickListener { appendNumber("6") }
        binding.sete.setOnClickListener { appendNumber("7") }
        binding.oito.setOnClickListener { appendNumber("8") }
        binding.nove.setOnClickListener { appendNumber("9") }
        binding.zero.setOnClickListener { appendNumber("0") }
        binding.ponto.setOnClickListener { appendNumber(".") }
    }

    private fun clear() {
        formula = ""
        result = 0.0
        updateUI()
    }

    private fun appendNumber(number: String) {
        formula += number
        updateUI()
    }

    private fun appendOperator(operator: String) {
        if (formula.isNotEmpty() && !isOperator(formula.last())) {
            formula += operator
        }
        updateUI()
    }

    private fun calculateResult() {
        try {
            result = evaluateFormula(formula)
            formula = result.toString()
        } catch (e: Exception) {
            formula = "Error"
        }
        updateUI()
    }

    private fun updateUI() {
        binding.tvFormula.text = formula
        binding.tvResult.text = result.toString()
    }

    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/'
    }

    private fun evaluateFormula(formula: String): Double {
        val expression = ExpressionBuilder(formula).build()
        return expression.evaluate()
    }
}
