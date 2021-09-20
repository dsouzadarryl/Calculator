package com.test.navigate.ui.viewModels

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private var counter: Int = 0

    fun add(
        number1: Int,
        number2: Int
    ): Int {
        printOperationStats()
        return number1 + number2
    }

    fun subtract(
        number1: Int,
        number2: Int
    ): Int {
        printOperationStats()
        return number1 - number2
    }

    private fun printOperationStats() {
        counter += 1
        println("operation count $counter")
    }
}