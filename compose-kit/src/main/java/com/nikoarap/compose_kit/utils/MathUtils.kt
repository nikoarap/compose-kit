package com.nikoarap.compose_kit.utils

import com.nikoarap.compose_kit.utils.Constants.Companion.FULL_ROTATION
import com.nikoarap.compose_kit.utils.Constants.Companion.NO_ROTATION

class MathUtils {

    companion object {

        /**
         * Calculates the angle of a full circle, based on the int values given.
         *
         * @param num     numerator int value
         * @param denom   denominator int value
         * @return angle in float from 0f - 360f
         *
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