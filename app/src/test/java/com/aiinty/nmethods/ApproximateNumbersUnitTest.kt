package com.aiinty.nmethods

import org.junit.Test
import org.junit.Assert.*
import com.aiinty.nmethods.numericalmethods.ApproximateNumbers
import java.math.BigDecimal

class ApproximateNumbersUnitTest {

    @Test
    fun addition_isCorrect() {
        val result = ApproximateNumbers.sum(
            mutableListOf(
                BigDecimal("2.345"),
                BigDecimal("0.06034"),
                BigDecimal("24.06557"),
            ))

        assertEquals(BigDecimal("26.471"), result.value)
    }

    @Test
    fun subtraction_isCorrect() {
        val result = ApproximateNumbers.sub(
            mutableListOf(
                BigDecimal("2.345"),
                BigDecimal("0.06034"),
                BigDecimal("24.06557"),
            ))
        assertEquals(BigDecimal("-21.781"), result.value)
    }

    @Test
    fun multiple_isCorrect() {
        val result = ApproximateNumbers.mul(
            mutableListOf(
                BigDecimal("2.345"),
                BigDecimal("0.06034"),
                BigDecimal("24.06557"),
            ))
        assertEquals(BigDecimal("3.405"), result.value)
    }

    @Test
    fun division_isCorrect() {
        val result = ApproximateNumbers.div(
            mutableListOf(
                BigDecimal("2.345"),
                BigDecimal("0.06034"),
                BigDecimal("24.06557"),
            ))
        assertEquals(BigDecimal("1.615"), result.value)
    }
}