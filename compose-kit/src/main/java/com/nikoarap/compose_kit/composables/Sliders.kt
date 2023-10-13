package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import kotlin.math.roundToInt


/**
 * A composable function that displays a customizable slider with a floating point value and an optional
 * text label above or below the slider thumb.
 *
 * @param sliderStartValue                  The minimum value of the slider.
 * @param sliderEndValue                    The maximum value of the slider.
 * @param sliderPosition               The initial position of the slider thumb.
 * @param thumbColor                        The color of the slider thumb.
 * @param disabledThumbColor                The color of the slider thumb when it's disabled.
 * @param activeTrackColor                  The color of the active track (to the left of the thumb).
 * @param inactiveTrackColor                The color of the inactive track (to the right of the thumb).
 * @param disabledActiveTrackColor          The color of the active track when the slider is disabled.
 * @param disabledInactiveTrackColor        The color of the inactive track when the slider is disabled.
 * @param activeTickColor                   The color of the ticks on the active track.
 * @param inactiveTickColor                 The color of the ticks on the inactive track.
 * @param disabledActiveTickColor           The color of the ticks on the active track when the slider is disabled.
 * @param disabledInactiveTickColor         The color of the ticks on the inactive track when the slider is disabled.
 * @param onSliderValueChange               A callback to handle changes in the slider's value.
 * @param textAboveThumb                    Set to true to display the text label above the slider thumb; false to display it below.
 *
 *
 * @sample FloatSliderWithText(
 *              sliderStartValue = 0.0f,
 *              sliderEndValue = 100.0f,
 *              sliderPositionFloat = 50.0f,
 *              thumbColor = Color.Blue,
 *              disabledThumbColor = Color.Gray,
 *              activeTrackColor = Color.Green,
 *              inactiveTrackColor = Color.Gray,
 *              disabledActiveTrackColor = Color.LightGray,
 *              disabledInactiveTrackColor = Color.LightGray,
 *              activeTickColor = Color.Red,
 *              inactiveTickColor = Color.LightGray,
 *              disabledActiveTickColor = Color.Gray,
 *              disabledInactiveTickColor = Color.Gray,
 *              onSliderValueChange = { newValue ->
 *                  // Handle the new value, e.g., update a ViewModel
 *              },
 *              textAboveThumb = true
 *     )
 * }
 */
@Composable
fun CustomSliderWithText(
    sliderStartValue: Float,
    sliderEndValue: Float,
    sliderPosition: Float,
    thumbColor: Color,
    disabledThumbColor: Color,
    activeTrackColor: Color,
    inactiveTrackColor: Color,
    disabledActiveTrackColor: Color,
    disabledInactiveTrackColor: Color,
    activeTickColor: Color,
    inactiveTickColor: Color,
    disabledActiveTickColor: Color,
    disabledInactiveTickColor: Color,
    onSliderValueChange: (Float) -> Unit,
    textAboveThumb: Boolean
) {
    val yOffsetText = if (textAboveThumb) -32 else 32
    var thumbPosition by remember { mutableFloatStateOf(sliderPosition) }

    // Measure the slider's layout to get its width
    var sliderWidth by remember { mutableIntStateOf(ZERO) }
    val onPositioned = Modifier.onGloballyPositioned { layoutCoordinates ->
        sliderWidth = layoutCoordinates.size.width
    }

    Box(
        modifier = Modifier.fillMaxWidth() then onPositioned
    ) {
        Slider(
            value = thumbPosition,
            onValueChange = {
                onSliderValueChange(it)
                thumbPosition = it
            },
            colors = CustomSliderColors(
                thumbColor = thumbColor,
                disabledThumbColor = disabledThumbColor,
                activeTrackColor = activeTrackColor,
                inactiveTrackColor = inactiveTrackColor,
                disabledActiveTrackColor = disabledActiveTrackColor,
                disabledInactiveTrackColor = disabledInactiveTrackColor,
                activeTickColor = activeTickColor,
                inactiveTickColor = inactiveTickColor,
                disabledActiveTickColor = disabledActiveTickColor,
                disabledInactiveTickColor = disabledInactiveTickColor
            ),
            valueRange = sliderStartValue..sliderEndValue
        )
        Text(
            text = thumbPosition.toString(),
            modifier = Modifier
                .offset {
                    IntOffset((thumbPosition * sliderWidth).toInt(), yOffsetText)
                }
        )
    }
}

/**
 * A composable function that displays a customizable slider with an integer value and an optional
 * text label above or below the slider thumb.
 *
 * @param sliderStartValue                  The minimum integer value of the slider.
 * @param sliderEndValue                    The maximum integer value of the slider.
 * @param sliderPosition                    The initial position of the slider thumb.
 * @param thumbColor                        The color of the slider thumb.
 * @param disabledThumbColor                The color of the slider thumb when it's disabled.
 * @param activeTrackColor                  The color of the active track (to the left of the thumb).
 * @param inactiveTrackColor                The color of the inactive track (to the right of the thumb).
 * @param disabledActiveTrackColor          The color of the active track when the slider is disabled.
 * @param disabledInactiveTrackColor        The color of the inactive track when the slider is disabled.
 * @param activeTickColor                   The color of the ticks on the active track.
 * @param inactiveTickColor                 The color of the ticks on the inactive track.
 * @param disabledActiveTickColor           The color of the ticks on the active track when the slider is disabled.
 * @param disabledInactiveTickColor         The color of the ticks on the inactive track when the slider is disabled.
 * @param onSliderValueChange               A callback to handle changes in the slider's value.
 * @param textAboveThumb                    Set to true to display the text label above the slider thumb; false to display it below.
 *
 * @sample {
 *     IntSliderWithText(
 *         sliderStartValue = 0,
 *         sliderEndValue = 100,
 *         sliderPosition = 50,
 *         thumbColor = Color.Blue,
 *         disabledThumbColor = Color.Gray,
 *         activeTrackColor = Color.Green,
 *         inactiveTrackColor = Color.Gray,
 *         disabledActiveTrackColor = Color.LightGray,
 *         disabledInactiveTrackColor = Color.LightGray,
 *         activeTickColor = Color.Red,
 *         inactiveTickColor = Color.LightGray,
 *         disabledActiveTickColor = Color.Gray,
 *         disabledInactiveTickColor = Color.Gray,
 *         onSliderValueChange = { newValue ->
 *             // Handle the new value, e.g., update a ViewModel
 *         },
 *         textAboveThumb = true
 *     )
 * }
 */
@Composable
fun CustomSliderWithText(
    sliderStartValue: Int,
    sliderEndValue: Int,
    sliderPosition: Int,
    thumbColor: Color,
    disabledThumbColor: Color,
    activeTrackColor: Color,
    inactiveTrackColor: Color,
    disabledActiveTrackColor: Color,
    disabledInactiveTrackColor: Color,
    activeTickColor: Color,
    inactiveTickColor: Color,
    disabledActiveTickColor: Color,
    disabledInactiveTickColor: Color,
    onSliderValueChange: (Float) -> Unit,
    textAboveThumb: Boolean
) {
    val yOffsetText = if (textAboveThumb) -32 else 32
    var thumbPosition by remember { mutableIntStateOf(sliderPosition) }

    // Measure the slider's layout to get its width
    var sliderWidth by remember { mutableIntStateOf(ZERO) }
    val onPositioned = Modifier.onGloballyPositioned { layoutCoordinates ->
        sliderWidth = layoutCoordinates.size.width
    }

    Box(
        modifier = Modifier.fillMaxWidth() then onPositioned
    ) {
        Slider(
            value = thumbPosition.toFloat(),
            onValueChange = {
                onSliderValueChange(it)
                thumbPosition = it.roundToInt()
            },
            colors = CustomSliderColors(
                thumbColor = thumbColor,
                disabledThumbColor = disabledThumbColor,
                activeTrackColor = activeTrackColor,
                inactiveTrackColor = inactiveTrackColor,
                disabledActiveTrackColor = disabledActiveTrackColor,
                disabledInactiveTrackColor = disabledInactiveTrackColor,
                activeTickColor = activeTickColor,
                inactiveTickColor = inactiveTickColor,
                disabledActiveTickColor = disabledActiveTickColor,
                disabledInactiveTickColor = disabledInactiveTickColor
            ),
            valueRange = sliderStartValue.toFloat()..sliderEndValue.toFloat()
        )
        Text(
            text = thumbPosition.toString(),
            modifier = Modifier
                .offset {
                    IntOffset((thumbPosition * sliderWidth), yOffsetText)
                }
        )
    }
}

/**
 * A composable function that creates a set of custom colors for a Slider.
 *
 * @return A [SliderColors] object with the specified custom color settings.
 */
@Composable
private fun CustomSliderColors(
    thumbColor: Color,
    disabledThumbColor: Color,
    activeTrackColor: Color,
    inactiveTrackColor: Color,
    disabledActiveTrackColor: Color,
    disabledInactiveTrackColor: Color,
    activeTickColor: Color,
    inactiveTickColor: Color,
    disabledActiveTickColor: Color,
    disabledInactiveTickColor: Color
): SliderColors {
    return SliderDefaults.colors(
        thumbColor = thumbColor,
        disabledThumbColor = disabledThumbColor,
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        activeTickColor = activeTickColor,
        inactiveTickColor = inactiveTickColor,
        disabledActiveTickColor = disabledActiveTickColor,
        disabledInactiveTickColor = disabledInactiveTickColor
    )
}