package com.aiinty.nmethods.screens.approxnumbers

import androidx.lifecycle.ViewModel
import com.aiinty.nmethods.numericalmethods.ApproximateNumbers
import com.aiinty.nmethods.numericalmethods.ApproximateNumbers.ApproximateNumbersResult
import java.math.BigDecimal

private const val TAG = "ApproximateNumbersViewModel"

class ApproximateNumbersViewModel () : ViewModel() {

    var numbers = mutableListOf<BigDecimal>()

    init {
        numbers = mutableListOf()
    }

    fun addNumber(value: BigDecimal) {
        numbers.add(value)
    }

    fun deleteNumber(idx: Int) {
        numbers.removeAt(idx)
    }

    fun sumNumbers() : ApproximateNumbersResult {
        return ApproximateNumbers.sum(numbers.toMutableList())
    }

    fun subNumbers() : ApproximateNumbersResult {
        return ApproximateNumbers.sub(numbers.toMutableList())
    }

    fun mulNumbers() : ApproximateNumbersResult {
        return ApproximateNumbers.mul(numbers.toMutableList())
    }

    fun divNumbers() : ApproximateNumbersResult {
        return ApproximateNumbers.div(numbers.toMutableList())
    }

    fun getSignificantDigits(idx: Int) : Int {
        return ApproximateNumbers.countSignificantDigits(numbers[idx])
    }

    fun getExactDigits(idx: Int, deltaA: BigDecimal) : Int {
        return ApproximateNumbers.countExactDigits(numbers[idx], deltaA)
    }

}