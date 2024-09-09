package com.aiinty.nmethods.screens.operations

import androidx.lifecycle.ViewModel
import com.aiinty.nmethods.NumericalMethods
import com.aiinty.nmethods.NumericalMethods.NumericalMethodsResult
import java.math.BigDecimal

private const val TAG = "NumbersViewModel"

class NumbersListViewModel () : ViewModel() {

    var numbers = mutableListOf<BigDecimal>()

    init {
        numbers = mutableListOf()
    }

    fun addNumber(value: BigDecimal) {
        numbers.add(value)
    }

    fun sumNumbers() : NumericalMethodsResult {
        return NumericalMethods.sumNumbers(numbers.toMutableList())
    }

    fun subNumbers() : NumericalMethodsResult {
        return NumericalMethods.subNumbers(numbers.toMutableList())
    }

    fun mulNumbers() : NumericalMethodsResult {
        return NumericalMethods.mulNumbers(numbers.toMutableList())
    }

    fun divNumbers() : NumericalMethodsResult {
        return NumericalMethods.divNumbers(numbers.toMutableList())
    }

    fun deleteNumber(idx: Int) {
        numbers.removeAt(idx)
    }
}