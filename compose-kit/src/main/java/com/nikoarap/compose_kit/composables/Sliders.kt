package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import kotlin.math.roundToInt

/**
 * A composable function that displays a customizable slider by providing a float range and a text label below the slider thumb.
 *
 * @param sliderStartValue                  The minimum float value of the slider.
 * @param sliderEndValue                    The maximum float value of the slider.
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
 * @param labelColor                        The color of the label text below the slider thumb.
 * @param onSliderValueChange               A callback to handle changes in the slider's value.
 *
 * @sample
 * ```
 *     SliderWithLabel(
 *          sliderStartValue = 0f,
 *          sliderEndValue = 100f,
 *          sliderPosition = 5f,
 *          steps = 0,
 *          thumbColor = Color.Blue,
 *          disabledThumbColor = Color.Gray,
 *          activeTrackColor = Color.Green,
 *          inactiveTrackColor = Color.Gray,
 *          disabledActiveTrackColor = Color.LightGray,
 *          disabledInactiveTrackColor = Color.LightGray,
 *          activeTickColor = Color.Red,
 *          inactiveTickColor = Color.LightGray,
 *          disabledActiveTickColor = Color.Gray,
 *          disabledInactiveTickColor = Color.Gray,
 *          labelColor = Color.Black,
 *          onSliderValueChange = { newValue ->
 *                 // Handle the new value, e.g., update a ViewModel
 *          }
 *     )
 * ```
 */
@Composable
fun SliderWithLabel(
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
    labelColor: Color,
    onSliderValueChange: (Float) -> Unit
) {
    val thumbOffset = remember { mutableFloatStateOf(0f) }
    var thumbPosition by remember { mutableFloatStateOf(sliderPosition) }
    val stepsValue = if (steps < ZERO) ZERO else steps
    var sliderWidth by remember { mutableIntStateOf(ZERO) }
    val onPositioned = Modifier.onGloballyPositioned { layoutCoordinates -> sliderWidth = layoutCoordinates.size.width }
    val minLabelWidth =
       if (thumbPosition.roundToInt() < 99)
            (thumbOffset.toString().length / 2).dp
        else if (thumbPosition.roundToInt() in 100..999)
            ((thumbOffset.toString().length / 2) + 4).dp
        else if (thumbPosition.roundToInt() in 1000..9999)
            ((thumbOffset.toString().length / 2) + 6).dp
         else
            ((thumbOffset.toString().length / 2) + 8).dp


    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp) then onPositioned
    ) {
        val offset = getSliderOffset(
            value = thumbOffset.floatValue,
            valueRange = sliderStartValue..sliderEndValue,
            boxWidth = maxWidth,
            labelWidth = minLabelWidth
        )

        Slider(
            value = thumbPosition,
            onValueChange = {
                onSliderValueChange(it)
                thumbPosition = it
                thumbOffset.floatValue = it
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
        SliderLabel(
            label = thumbOffset.floatValue.toInt().toString(), minWidth = minLabelWidth, modifier = Modifier
                .padding(start = offset, top = 32.dp), labelColor = labelColor
        )
    }
}

/**
 * Calculates the offset for placing a label relative to a Slider thumb within a given width.
 *
 * @param value                 The value for the Slider thumb.
 * @param valueRange            The range of valid values for the Slider.
 * @param boxWidth              The available width for positioning the label.
 * @param labelWidth            The width of the label to be positioned.
 * @return                      The offset, in density-independent pixels (dp), for placing the label.
 */
private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {

    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}

/**
 * Calculates the fraction representing the relative position of a value within a specified range.
 *
 * @param a         The start of the range.
 * @param b         The end of the range.
 * @param pos       The value whose position is to be calculated.
 * @return          The fraction representing the relative position of the value within the range, constrained to [0, 1].
 */
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)

/**
 * A composable function that displays a label with the specified text for a slider.
 *
 * @param label         The text to display in the label.
 * @param minWidth      The minimum width for the label.
 * @param modifier      The modifier to apply to the label composable.
 * @param labelColor    The color of the label.
 */
@Composable
private fun SliderLabel(
    label: String,
    minWidth: Dp,
    modifier: Modifier = Modifier,
    labelColor: Color
) {
    Text(
        label,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = labelColor,
        modifier = modifier
            .defaultMinSize(minWidth = minWidth)
    )
}

/**
 * A composable function that displays a customizable slider with a floating-point value and a
 * text label below the slider thumb. The slider's thumb and active track colors dynamically change
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
 *
 * @sample
 * ```
 *     DynamicSliderWithLabel(
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
 *         }
 *     )
 *     ```
 */
@Composable
fun DynamicSliderWithLabel(
    sliderStartValue: Float,
    sliderEndValue: Float,
    sliderPosition: Float,
    thresholdOne: Float,
    thresholdTwo: Float,
    startColor: Color,
    middleColor: Color,
    endColor: Color,
    steps: Int = 0,
    onSliderValueChange: (Float) -> Unit
) {
    val thumbOffset = remember { mutableFloatStateOf(0f) }
    var thumbPosition by remember { mutableFloatStateOf(sliderPosition) }
    val stepsValue = if (steps < ZERO) ZERO else steps
    var sliderWidth by remember { mutableIntStateOf(ZERO) }
    val onPositioned = Modifier.onGloballyPositioned { layoutCoordinates -> sliderWidth = layoutCoordinates.size.width }
    val minLabelWidth =
        if (thumbPosition.roundToInt() < 99)
            (thumbOffset.toString().length / 2).dp
        else if (thumbPosition.roundToInt() in 100..999)
            ((thumbOffset.toString().length / 2) + 4).dp
        else if (thumbPosition.roundToInt() in 1000..9999)
            ((thumbOffset.toString().length / 2) + 6).dp
        else
            ((thumbOffset.toString().length / 2) + 8).dp

    val sliderColorsAfterChange = when {
        thumbPosition < thresholdOne -> {
            startColor
        }
        thumbPosition >= thresholdOne && thumbPosition < thresholdTwo -> {
            middleColor
        }
        else -> {endColor}
    }


    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp) then onPositioned
    ) {
        val offset = getSliderOffset(
            value = thumbOffset.floatValue,
            valueRange = sliderStartValue..sliderEndValue,
            boxWidth = maxWidth,
            labelWidth = minLabelWidth
        )

        Slider(
            value = thumbPosition,
            onValueChange = {
                onSliderValueChange(it)
                thumbPosition = it
                thumbOffset.floatValue = it
            },
            colors = trackAndThumbActiveColors(
                activeSliderColors = sliderColorsAfterChange
            ),
            valueRange = sliderStartValue..sliderEndValue,
            steps = stepsValue
        )
        SliderLabel(
            label = thumbOffset.floatValue.toInt().toString(), minWidth = minLabelWidth, modifier = Modifier
                .padding(start = offset, top = 32.dp), labelColor = sliderColorsAfterChange
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
    activeSliderColors: Color
): SliderColors {
    return SliderDefaults.colors(
        thumbColor = activeSliderColors,
        activeTrackColor = activeSliderColors,
        inactiveTrackColor = Color.LightGray.copy(0.7f),
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