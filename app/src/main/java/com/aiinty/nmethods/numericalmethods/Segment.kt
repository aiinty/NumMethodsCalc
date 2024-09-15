package com.aiinty.nmethods.numericalmethods

import java.math.BigDecimal
import java.math.RoundingMode

private const val TAG = "Segment"

class Segment(
    private val lowerLimit: BigDecimal,
    private val upperLimit: BigDecimal
) {

    fun getApproximateValue(): BigDecimal {
        return lowerLimit + getBoundary()
    }

    fun getBoundary(): BigDecimal {
        return (upperLimit - lowerLimit) / BigDecimal("2")
    }

    operator fun plus(other: Segment): Segment {
        return Segment(
            this.lowerLimit + other.lowerLimit,
            this.upperLimit + other.upperLimit
        )
    }

    operator fun minus(other: Segment): Segment {
        return Segment(
            this.lowerLimit - other.upperLimit,
            this.upperLimit - other.lowerLimit
        )
    }

    operator fun times(other: Segment): Segment {
        if (
            lowerLimit < BigDecimal.ZERO ||
            upperLimit < BigDecimal.ZERO ||
            other.lowerLimit < BigDecimal.ZERO ||
            other.upperLimit < BigDecimal.ZERO
            ) {
            throw IllegalArgumentException()
        }
        return Segment(
            (this.lowerLimit * other.lowerLimit).setScale(2, RoundingMode.DOWN),
            (this.upperLimit * other.upperLimit).setScale(2, RoundingMode.UP)
        )
    }

    operator fun div(other: Segment): Segment {
        if (
            lowerLimit < BigDecimal.ZERO ||
            upperLimit < BigDecimal.ZERO ||
            other.lowerLimit < BigDecimal.ZERO ||
            other.upperLimit < BigDecimal.ZERO
        ) {
            throw IllegalArgumentException()
        }
        return Segment(
            this.lowerLimit.divide(other.upperLimit, RoundingMode.DOWN),
            this.upperLimit.divide(other.lowerLimit, RoundingMode.UP)
        )
    }

    fun pow(n: Int): Segment {
        return Segment(
            this.lowerLimit.pow(n).setScale(2, RoundingMode.DOWN),
            this.upperLimit.pow(n).setScale(2, RoundingMode.UP)
        )
    }

    override operator fun equals(other: Any?): Boolean {
        return other is Segment
                && this.lowerLimit == other.lowerLimit
                && this.upperLimit == other.upperLimit
    }

    override fun toString(): String {
        return "${super.toString()}, lowerLimit = ${this.lowerLimit}, upperLimit = ${this.upperLimit}"
    }

    override fun hashCode(): Int {
        var result = lowerLimit.hashCode()
        result = 31 * result + upperLimit.hashCode()
        return result
    }
}