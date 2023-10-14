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
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * A composable function that displays a customizable slider with a floating point value and an optional
 * text label above or below the slider thumb. If a steps are provided
 *
 * @param sliderStartValue                  The minimum value of the slider.
 * @param sliderEndValue                    The maximum value of the slider.
 * @param sliderPosition                    The initial position of the slider thumb.
 * @param steps                             If greater than 0, specifies the amounts of discrete values, evenly distributed between across the whole value range. If 0, slider will behave as a continuous slider and allow to choose any value from the range specified. Must not be negative.
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
 * @param textOffsetDp                      Set a positive integer value for the offset of the text with regards to the slider thumb.
 *
 * @sample {
 *     CustomSliderWithText(
 *         sliderStartValue = 0.0f,
 *         sliderEndValue = 100.0f,
 *         sliderPosition = 50.0f,
 *         steps = 0,
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
 *         textAboveThumb = true,
 *         textOffsetDp = 32
 *     )
 * }
 */
@Composable
fun CustomSliderWithText(
    sliderStartValue: Float,
    sliderEndValue: Float,
    sliderPosition: Float,
    steps: Int = 0,
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
    textAboveThumb: Boolean,
    textOffsetDp: Int
) {
    var yOffsetText = if (textOffsetDp < ZERO) abs(textOffsetDp) else textOffsetDp
    if (textAboveThumb) { yOffsetText = -textOffsetDp }
    var thumbPosition by remember { mutableFloatStateOf(sliderPosition) }
    val stepsValue = if (steps < ZERO) ZERO else steps

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
            colors = customSliderColors(
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
            valueRange = sliderStartValue..sliderEndValue,
            steps = stepsValue
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
 * @param steps                             If greater than 0, specifies the amounts of discrete values, evenly distributed between across the whole value range. If 0, slider will behave as a continuous slider and allow to choose any value from the range specified. Must not be negative.
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
 * @param textOffsetDp                      Set a positive integer value for the offset of the text with regards to the slider thumb.
 *
 * @sample {
 *     CustomSliderWithText(
 *         sliderStartValue = 0,
 *         sliderEndValue = 100,
 *         sliderPosition = 50,
 *         steps = 5,
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
 *         textAboveThumb = true,
 *         textOffsetDp = -32
 *     )
 * }
 */
@Composable
fun CustomSliderWithText(
    sliderStartValue: Int,
    sliderEndValue: Int,
    sliderPosition: Int,
    steps: Int = 0,
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
    textAboveThumb: Boolean,
    textOffsetDp: Int
) {
    var yOffsetText = if (textOffsetDp < ZERO) abs(textOffsetDp) else textOffsetDp
    if (textAboveThumb) { yOffsetText = -textOffsetDp }
    var thumbPosition by remember { mutableIntStateOf(sliderPosition) }
    val stepsValue = if (steps < ZERO) ZERO else steps

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
            colors = customSliderColors(
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
            valueRange = sliderStartValue.toFloat()..sliderEndValue.toFloat(),
            steps = stepsValue
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
 * A composable function that displays a customizable slider with an integer value and an optional
 * text label above or below the slider thumb. The slider's thumb and active track colors dynamically change
 * based on specified thresholds.
 *
 * @param sliderStartValue          The minimum integer value of the slider.
 * @param sliderEndValue            The maximum integer value of the slider.
 * @param sliderPosition            The initial position of the slider thumb.
 * @param thresholdOne              The first threshold value for changing the slider colors.
 * @param thresholdTwo              The second threshold value for changing the slider colors.
 * @param startColor                The color to use when the thumb is below thresholdOne.
 * @param middleColor               The color to use when the thumb is between thresholdOne (inclusive) and thresholdTwo (exclusive).
 * @param endColor                  The color to use when the thumb is equal to or above thresholdTwo.
 * @param steps                     The number of discrete steps in the slider. Use 0 for a continuous slider.
 * @param onSliderValueChange       A callback to handle changes in the slider's value.
 * @param textAboveThumb            Set to true to display the text label above the slider thumb; false to display it below.
 * @param textOffsetDp              The offset in density-independent pixels (dp) for the text label above or below the thumb.
 *
 * @sample
 *     CustomSliderWithText(
 *         sliderStartValue = 0,
 *         sliderEndValue = 100,
 *         sliderPosition = 50,
 *         thresholdOne = 30,
 *         thresholdTwo = 70,
 *         startColor = Color.Gray,
 *         middleColor = Color.Yellow,
 *         endColor = Color.Green,
 *         steps = 0,
 *         onSliderValueChange = { newValue ->
 *             // Handle the new value, e.g., update a ViewModel
 *         },
 *         textAboveThumb = true,
 *         textOffsetDp = 16
 *     )
 */
@Composable
fun CustomSliderWithText(
    sliderStartValue: Int,
    sliderEndValue: Int,
    sliderPosition: Int,
    thresholdOne: Int,
    thresholdTwo: Int,
    startColor: Color,
    middleColor: Color,
    endColor: Color,
    steps: Int = 0,
    onSliderValueChange: (Float) -> Unit,
    textAboveThumb: Boolean,
    textOffsetDp: Int
) {
    var yOffsetText = if (textOffsetDp < ZERO) abs(textOffsetDp) else textOffsetDp
    if (textAboveThumb) { yOffsetText = -textOffsetDp }
    var thumbPosition by remember { mutableIntStateOf(sliderPosition) }
    val stepsValue = if (steps < ZERO) ZERO else steps

    // Measure the slider's layout to get its width
    var sliderWidth by remember { mutableIntStateOf(ZERO) }
    val onPositioned = Modifier.onGloballyPositioned { layoutCoordinates ->
        sliderWidth = layoutCoordinates.size.width
    }

    val sliderColorsAfterChange = when {
        thumbPosition < thresholdOne -> {
            startColor
        }
        thumbPosition in thresholdOne until thresholdTwo -> {
            middleColor
        }
        else -> {endColor}
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
            colors = trackAndThumbActiveColors(
                thumbColor = sliderColorsAfterChange,
                activeTrackColor = sliderColorsAfterChange
            ),
            valueRange = sliderStartValue.toFloat()..sliderEndValue.toFloat(),
            steps = stepsValue
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
 * A composable function that displays a customizable slider with a floating-point value and an optional
 * text label above or below the slider thumb. The slider's thumb and active track colors dynamically change
 * based on specified thresholds.
 *
 * @param sliderStartValue          The minimum float value of the slider.
 * @param sliderEndValue            The maximum float value of the slider.
 * @param sliderPosition            The initial position of the slider thumb.
 * @param thresholdOne              The first threshold value for changing the slider colors.
 * @param thresholdTwo              The second threshold value for changing the slider colors.
 * @param startColor                The color to use when the thumb is below thresholdOne.
 * @param middleColor               The color to use when the thumb is between thresholdOne and thresholdTwo.
 * @param endColor                  The color to use when the thumb is equal to or above thresholdTwo.
 * @param steps                     The number of discrete steps in the slider. Use 0 for a continuous slider.
 * @param onSliderValueChange       A callback to handle changes in the slider's value.
 * @param textAboveThumb            Set to true to display the text label above the slider thumb; false to display it below.
 * @param textOffsetDp              The offset in density-independent pixels (dp) for the text label above or below the thumb.
 *
 * @sample
 *     CustomSliderWithText(
 *         sliderStartValue = 0f,
 *         sliderEndValue = 100f,
 *         sliderPosition = 50f,
 *         thresholdOne = 30f,
 *         thresholdTwo = 70f,
 *         startColor = Color.Gray,
 *         middleColor = Color.Yellow,
 *         endColor = Color.Green,
 *         steps = 0,
 *         onSliderValueChange = { newValue ->
 *             // Handle the new value, e.g., update a ViewModel
 *         },
 *         textAboveThumb = true,
 *         textOffsetDp = 16
 *     )
 */
@Composable
fun CustomSliderWithText(
    sliderStartValue: Float,
    sliderEndValue: Float,
    sliderPosition: Float,
    thresholdOne: Float,
    thresholdTwo: Float,
    startColor: Color,
    middleColor: Color,
    endColor: Color,
    steps: Int = 0,
    onSliderValueChange: (Float) -> Unit,
    textAboveThumb: Boolean,
    textOffsetDp: Int
) {
    var yOffsetText = if (textOffsetDp < ZERO) abs(textOffsetDp) else textOffsetDp
    if (textAboveThumb) { yOffsetText = -textOffsetDp }
    var thumbPosition by remember { mutableFloatStateOf(sliderPosition) }
    val stepsValue = if (steps < ZERO) ZERO else steps

    // Measure the slider's layout to get its width
    var sliderWidth by remember { mutableIntStateOf(ZERO) }
    val onPositioned = Modifier.onGloballyPositioned { layoutCoordinates ->
        sliderWidth = layoutCoordinates.size.width
    }

    val sliderColorsAfterChange = when {
        thumbPosition < thresholdOne -> {
            startColor
        }
        thumbPosition >= thresholdOne && thumbPosition < thresholdTwo -> {
            middleColor
        }
        else -> {endColor}
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
            colors = trackAndThumbActiveColors(
                thumbColor = sliderColorsAfterChange,
                activeTrackColor = sliderColorsAfterChange
            ),
            valueRange = sliderStartValue..sliderEndValue,
            steps = stepsValue
        )
        Text(
            text = thumbPosition.toString(),
            modifier = Modifier
                .offset {
                    IntOffset(((thumbPosition * sliderWidth)).toInt(), yOffsetText)
                }
        )
    }
}

/**
 * A composable function that creates custom colors for a Slider's track and thumb.
 *
 * @return A [SliderColors] object with the specified custom color settings.
 */
@Composable
private fun trackAndThumbActiveColors(
    thumbColor: Color,
    activeTrackColor: Color
): SliderColors {
    return SliderDefaults.colors(
        thumbColor = thumbColor,
        activeTrackColor = activeTrackColor
    )
}

/**
 * A composable function that creates a set of custom colors for a Slider.
 *
 * @return A [SliderColors] object with the specified custom color settings.
 */
@Composable
private fun customSliderColors(
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