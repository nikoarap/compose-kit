package com.nikoarap.compose_kit.utils

import com.nikoarap.compose_kit.utils.Constants.Companion.FULL_ROTATION
import com.nikoarap.compose_kit.utils.Constants.Companion.NO_ROTATION
import kotlin.math.abs

class MathUtils {

    companion object {

        /**
         * Calculates the angle in degrees based on a numerator and denominator.
         *
         * This function computes the angle in degrees based on a numerator and denominator, following these rules:
         * - If [num] is equal to [denom], it returns a full rotation angle (360 degrees).
         * - If [denom] is zero, it returns a zero-degree angle (no rotation).
         * - Otherwise, it calculates the angle as the result of dividing [num] by [denom] and multiplying
         *   by the full rotation angle (360 degrees).
         *
         * @param num           The numerator used in the angle calculation.
         * @param denom         The denominator used in the angle calculation.
         *
         * @return The calculated angle in degrees.
         *
         * @throws ArithmeticException if [denom] is zero (division by zero).
         */
        fun calculateAngle(num: Int, denom: Int): Float {
            return when {
                num == denom -> {
                    FULL_ROTATION.toFloat()
                }
                denom == 0 -> {
                    NO_ROTATION.toFloat()
                }
                else -> ((num * FULL_ROTATION) / denom).toFloat()
            }
        }
    }

}