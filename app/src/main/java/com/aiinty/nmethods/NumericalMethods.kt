package com.aiinty.nmethods

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class NumericalMethods {

    data class NumericalMethodsResult(val value: BigDecimal, val output: String)

    companion object {

        fun sumNumbers(arr: MutableList<BigDecimal>) : NumericalMethodsResult {
            var result: BigDecimal = BigDecimal.ZERO
            var textOut = ""

            var minDecimalDigitsCount: Int? = null
            for (number in arr) {
                val digitsCount = countDecimalDigits(number)
                if (minDecimalDigitsCount == null || digitsCount < minDecimalDigitsCount) {
                    minDecimalDigitsCount = digitsCount
                }
            }

            arr.forEachIndexed { i, _ ->
                arr[i] = arr[i].setScale(minDecimalDigitsCount!! + 1, RoundingMode.HALF_EVEN)
                textOut += "${arr[i].stripTrailingZeros()}"
                if (i != arr.lastIndex) {
                    textOut += " + "
                }
            }

            arr.forEach { n ->
                result += n
            }

            textOut += " = ${result}"

            return NumericalMethodsResult(
                result.setScale(minDecimalDigitsCount!!, RoundingMode.HALF_EVEN),
                textOut
            )
        }

        fun subNumbers(arr: MutableList<BigDecimal>) : NumericalMethodsResult {
            var result: BigDecimal = arr[0].multiply(BigDecimal(2))
            var textOut = ""

            var minDecimalDigitsCount: Int? = null
            for (number in arr) {
                val digitsCount = countDecimalDigits(number)
                if (minDecimalDigitsCount == null || digitsCount < minDecimalDigitsCount) {
                    minDecimalDigitsCount = digitsCount
                }
            }

            arr.forEachIndexed { i, _ ->
                arr[i] = arr[i].setScale(minDecimalDigitsCount!! + 1, RoundingMode.HALF_EVEN)
                textOut += "${arr[i].stripTrailingZeros()}"
                if (i != arr.lastIndex) {
                    textOut += " - "
                }
            }

            arr.forEach { n -> result -= n }

            textOut += " = ${result}"

            return NumericalMethodsResult(
                result.setScale(minDecimalDigitsCount!!, RoundingMode.HALF_EVEN),
                textOut
            )
        }

        fun mulNumbers(arr: MutableList<BigDecimal>) : NumericalMethodsResult {
            var result: BigDecimal = BigDecimal.ZERO
            var textOut = ""

            var minSignificantDigitsCount: Int? = null
            for (number in arr) {
                val digitsCount = countSignificantDigits(number)
                if (minSignificantDigitsCount == null || digitsCount < minSignificantDigitsCount) {
                    minSignificantDigitsCount = digitsCount
                }
            }

            arr.forEachIndexed { i, n ->
                arr[i] = arr[i].round(MathContext(minSignificantDigitsCount!! + 1, RoundingMode.HALF_EVEN))
                textOut += "${arr[i].stripTrailingZeros()}"
                if (i != arr.lastIndex) {
                    textOut += " * "
                }
            }

            arr.forEach { n ->
                if (result == BigDecimal.ZERO) result = n
                else result = result.multiply(n, MathContext.DECIMAL64)
            }

            textOut += " = ${result}"

            return NumericalMethodsResult(
                roundToNSignificantDigits(result, minSignificantDigitsCount!!),
                textOut
            )
        }

        fun divNumbers(arr: MutableList<BigDecimal>) : NumericalMethodsResult {
            var result: BigDecimal = BigDecimal.ZERO
            var textOut = ""

            var minSignificantDigitsCount: Int? = null
            for (number in arr) {
                val digitsCount = countSignificantDigits(number)
                if (minSignificantDigitsCount == null || digitsCount < minSignificantDigitsCount) {
                    minSignificantDigitsCount = digitsCount
                }
            }

            arr.forEachIndexed { i, n ->
                arr[i] = arr[i].round(MathContext(minSignificantDigitsCount!! + 1, RoundingMode.HALF_EVEN))
                textOut += "${arr[i].stripTrailingZeros()}"
                if (i != arr.lastIndex) {
                    textOut += " / "
                }
            }

            arr.forEach { n ->
                if (result == BigDecimal.ZERO) result = n
                else result = result.divide(n, MathContext.DECIMAL64)
            }

            textOut += " = ${result}"

            return NumericalMethodsResult(
                roundToNSignificantDigits(result, minSignificantDigitsCount!!),
                textOut
            )
        }

        fun countDecimalDigits(value: BigDecimal) : Int {
            val number: String = value.abs()
                .toPlainString()
                .reversed()
            var result = 0

            if (!number.contains(Regex("[,.]"))) {
                return result
            }

            for (digit in number) {
                if (!digit.isDigit()) {
                    break
                }
                result++
            }

            return result
        }

        fun countIntegerDigits(value: BigDecimal) : Int {
            val number: String = value.abs()
                .toPlainString()
            var result = 0

            for (digit in number) {
                if (!digit.isDigit()) {
                    break
                }
                result++
            }

            return result
        }

        fun countDigits(value: BigDecimal) : Int {
            return countDecimalDigits(value) + countIntegerDigits(value)
        }

        fun countSignificantDigits(value: BigDecimal): Int {
            val number: String = value.abs().toPlainString()
            var result: Int = number.length

            if (number.contains(Regex("[,.]"))) {
                result--
            }

            for (digit in number) {
                if (!digit.isDigit()) {
                    continue
                }
                if (digit != '0') {
                    break
                }
                result--
            }

            return result
        }

        fun countExactDigits(value: BigDecimal, a: BigDecimal): Int {
            val number: String = value.abs()
                .toPlainString()
                .reversed()
            var result: Int = countSignificantDigits(value)
            var separatorIdx: Int = number.indexOf('.')

            if (separatorIdx == -1) {
                separatorIdx = if (number.indexOf(',') != -1) number.indexOf(',') else throw IllegalArgumentException()
            }

            for (digit in number) {
                if (result == 0 || a.toDouble() <= 0.5 * Math.pow(10.toDouble(), (-separatorIdx--).toDouble())) {
                    break
                }
                result--
            }

            return result
        }

        fun roundToNSignificantDigits(value: BigDecimal, digitsCount: Int) : BigDecimal {
            return value.round(MathContext(digitsCount, RoundingMode.HALF_EVEN))
        }
    }
}