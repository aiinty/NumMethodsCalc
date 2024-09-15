package com.aiinty.nmethods.numericalmethods

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

private const val TAG = "ApproximateNumber"

class ApproximateNumbers {

    data class ApproximateNumbersResult(val value: BigDecimal, val output: String)

    companion object {

        fun sum(list: MutableList<BigDecimal>) : ApproximateNumbersResult {
            var result: BigDecimal = BigDecimal.ZERO
            var textOut = ""

            var minDecimalDigitsCount: Int? = null
            for (number in list) {
                val digitsCount = countDecimalDigits(number)
                if (minDecimalDigitsCount == null || digitsCount < minDecimalDigitsCount) {
                    minDecimalDigitsCount = digitsCount
                }
            }

            list.forEachIndexed { i, _ ->
                list[i] = list[i].setScale(minDecimalDigitsCount!! + 1, RoundingMode.HALF_EVEN)
                textOut += "${list[i].stripTrailingZeros()}"
                if (i != list.lastIndex) {
                    textOut += " + "
                }
            }

            list.forEach { n ->
                result += n
            }

            textOut += " = ${result}"

            return ApproximateNumbersResult(
                result.setScale(minDecimalDigitsCount!!, RoundingMode.HALF_EVEN),
                textOut
            )
        }

        fun sub(list: MutableList<BigDecimal>) : ApproximateNumbersResult {
            var result: BigDecimal = list[0].multiply(BigDecimal(2))
            var textOut = ""

            var minDecimalDigitsCount: Int? = null
            for (number in list) {
                val digitsCount = countDecimalDigits(number)
                if (minDecimalDigitsCount == null || digitsCount < minDecimalDigitsCount) {
                    minDecimalDigitsCount = digitsCount
                }
            }

            list.forEachIndexed { i, _ ->
                list[i] = list[i].setScale(minDecimalDigitsCount!! + 1, RoundingMode.HALF_EVEN)
                textOut += "${list[i].stripTrailingZeros()}"
                if (i != list.lastIndex) {
                    textOut += " - "
                }
            }

            list.forEach { n -> result -= n }

            textOut += " = ${result}"

            return ApproximateNumbersResult(
                result.setScale(minDecimalDigitsCount!!, RoundingMode.HALF_EVEN),
                textOut
            )
        }

        fun mul(list: MutableList<BigDecimal>) : ApproximateNumbersResult {
            var result: BigDecimal = BigDecimal.ZERO
            var textOut = ""

            var minSignificantDigitsCount: Int? = null
            for (number in list) {
                val digitsCount = countSignificantDigits(number)
                if (minSignificantDigitsCount == null || digitsCount < minSignificantDigitsCount) {
                    minSignificantDigitsCount = digitsCount
                }
            }

            list.forEachIndexed { i, _ ->
                list[i] = list[i].round(MathContext(minSignificantDigitsCount!! + 1, RoundingMode.HALF_EVEN))
                textOut += "${list[i].stripTrailingZeros()}"
                if (i != list.lastIndex) {
                    textOut += " * "
                }
            }

            list.forEach { n ->
                if (result == BigDecimal.ZERO) result = n
                else result = result.multiply(n, MathContext.DECIMAL64)
            }

            textOut += " = ${result}"

            return ApproximateNumbersResult(
                roundToNSignificantDigits(result, minSignificantDigitsCount!!),
                textOut
            )
        }

        fun div(list: MutableList<BigDecimal>) : ApproximateNumbersResult {
            var result: BigDecimal = BigDecimal.ZERO
            var textOut = ""

            var minSignificantDigitsCount: Int? = null
            for (number in list) {
                val digitsCount = countSignificantDigits(number)
                if (minSignificantDigitsCount == null || digitsCount < minSignificantDigitsCount) {
                    minSignificantDigitsCount = digitsCount
                }
            }

            list.forEachIndexed { i, _ ->
                list[i] = list[i].round(MathContext(minSignificantDigitsCount!! + 1, RoundingMode.HALF_EVEN))
                textOut += "${list[i].stripTrailingZeros()}"
                if (i != list.lastIndex) {
                    textOut += " / "
                }
            }

            list.forEach { n ->
                if (result == BigDecimal.ZERO) result = n
                else result = result.divide(n, MathContext.DECIMAL64)
            }

            textOut += " = ${result}"

            return ApproximateNumbersResult(
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

        private fun countDigits(value: BigDecimal) : Int {
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

        fun countExactDigits(value: BigDecimal, deltaA: BigDecimal): Int {
            val number: String = value.abs()
                .toPlainString()
                .reversed()
            var result: Int = countSignificantDigits(value)
            var separatorIdx: Int = number.indexOf('.')

            if (separatorIdx == -1) {
                separatorIdx = if (number.indexOf(',') != -1) number.indexOf(',') else throw IllegalArgumentException()
            }

            for (digit in number) {
                if (result == 0 || deltaA.toDouble() <= 0.5 * Math.pow(10.toDouble(), (-separatorIdx--).toDouble())) {
                    break
                }
                result--
            }

            return result
        }

        private fun roundToNSignificantDigits(value: BigDecimal, digitsCount: Int) : BigDecimal {
            return value.round(MathContext(digitsCount, RoundingMode.HALF_EVEN))
        }
    }
}