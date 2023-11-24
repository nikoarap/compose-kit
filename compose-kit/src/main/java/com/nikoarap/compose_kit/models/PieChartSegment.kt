package com.nikoarap.compose_kit.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.nikoarap.compose_kit.utils.Constants
import com.nikoarap.compose_kit.utils.Constants.Companion.FULL_ROTATION

/**
 * A data class representing a segment of a pie chart.
 *
 * @param label                     The label or name of the pie chart segment.
 * @param value                     The numerical value or size of the segment.
 * @param color                     The color to be used for the segment in the pie chart.
 * @param upperTextTypography       The text style for the upper text associated with the segment.
 * @param lowerTextTypography       The text style for the lower text associated with the segment.
 * @param upperTextColor            The color of the upper text.
 * @param lowerTextColor            The color of the lower text.
 */
data class PieChartSegment(
    val label: String = Constants.EMPTY,
    val value: Int = 0,
    val color: Color = Color.Blue,
    val upperTextTypography: TextStyle = TextStyle.Default,
    val lowerTextTypography: TextStyle = TextStyle.Default,
    val upperTextColor: Color = Color.Black,
    val lowerTextColor: Color = Color.LightGray
) {

    /**
     * Calculates the sweep angle in degrees for a pie chart segment based on its value and the total
     * sum of values in the pie chart.
     *
     * @param totalChartSum     The total sum of values for all segments in the pie chart.
     * @return The sweep angle in degrees for the segment.
     */
    fun getSweepAngleForSegment(totalChartSum: Int): Float {
        return FULL_ROTATION * value / totalChartSum.toFloat()
    }
}