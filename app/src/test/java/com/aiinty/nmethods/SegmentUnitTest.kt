package com.aiinty.nmethods

import org.junit.Test
import org.junit.Assert.*
import com.aiinty.nmethods.numericalmethods.Segment
import java.math.BigDecimal

class SegmentUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(
            Segment(BigDecimal("10.16"), BigDecimal("10.24")),
            Segment(BigDecimal("2.55"), BigDecimal("2.58")) + Segment(BigDecimal("7.61"), BigDecimal("7.66"))
        )
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals(
            Segment(BigDecimal("5.03"), BigDecimal("5.11")),
            Segment(BigDecimal("7.61"), BigDecimal("7.66")) - Segment(BigDecimal("2.55"), BigDecimal("2.58"))
        )
    }

    @Test
    fun multiple_isCorrect() {
        assertEquals(
            Segment(BigDecimal("9.24"), BigDecimal("9.43")),
            Segment(BigDecimal("1.11"), BigDecimal("1.13")) * Segment(BigDecimal("8.33"), BigDecimal("8.34"))
        )
    }

    @Test
    fun division_isCorrect() {
        assertEquals(
            Segment(BigDecimal("0.58"), BigDecimal("0.62")),
            Segment(BigDecimal("6.04"), BigDecimal("6.24")) / Segment(BigDecimal("10.16"), BigDecimal("10.24"))
        )
    }

    @Test
    fun exponentiation_isCorrect() {
        assertEquals(
            Segment(BigDecimal("6.50"), BigDecimal("6.66")),
            Segment(BigDecimal("2.55"), BigDecimal("2.58")).pow(2)
        )
    }
}