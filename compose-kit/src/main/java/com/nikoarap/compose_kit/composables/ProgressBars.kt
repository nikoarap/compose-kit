package com.nikoarap.compose_kit.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.FONT_STYLE_NORMAL
import com.nikoarap.compose_kit.utils.Constants.Companion.FONT_WEIGHT_BOLD
import com.nikoarap.compose_kit.utils.Constants.Companion.FULL_ROTATION
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.Constants.Companion.PROGRESS_START_ANGLE
import com.nikoarap.compose_kit.utils.MathUtils

/**
 * A gradient circular progress bar composable.
 * Progress is calculated based on the progressValue and totalValue integer values given.
 * Progress bar can be styled with the primaryGradient & secondaryGradient colors to make a gradient.
 *
 * @param modifier                  Modifier to apply attributes to
 * @param progressBarSizeDp         size of the progress bar in dp
 * @param progressBarStrokeWidth    float value of progress bar stroke width
 * @param backgroundColor           background color of the progress bar (here you will match the background color of the surface that you are composing this progress bar)
 * @param primaryGradient           primary color to make the gradient
 * @param secondaryGradient         secondary color to make the gradient
 * @param progressValue             value to be applied for the progressed state of the bar
 * @param totalValue                total value to be applied to the progress bar
 *
 */
@SuppressLint("ComposableNaming")
@Composable
fun CircularProgressBar(
    modifier: Modifier,
    progressBarSizeDp: Int,
    progressBarStrokeWidth: Float,
    backgroundColor: Color,
    primaryGradient: Color,
    secondaryGradient: Color,
    progressValue: Int,
    totalValue: Int
) {
    Box(
        modifier = Modifier.size(progressBarSizeDp.dp)
    ) {
        Canvas(
            modifier = modifier
        ) {
            drawArc(
                color = backgroundColor,
                startAngle = PROGRESS_START_ANGLE,
                sweepAngle = FULL_ROTATION.toFloat(),
                useCenter = false,
                style = Stroke(width = progressBarStrokeWidth, cap = StrokeCap.Round)
            )
            drawArc(
                brush = Brush.radialGradient(
                    listOf(primaryGradient, secondaryGradient),
                    center = Offset(10.0f, 20.0f)
                ),
                startAngle = PROGRESS_START_ANGLE,
                sweepAngle = MathUtils.calculateAngle(progressValue, totalValue),
                useCenter = false,
                style = Stroke(width = progressBarStrokeWidth, cap = StrokeCap.Round)
            )
        }
    }
}

/**
 * A gradient circular progress bar composable with a text aligned at its' center.
 * Progress is calculated based on the progressValue and totalValue integer values given.
 * Progress bar can be styled with the primaryGradient & secondaryGradient colors to make a gradient. Text size and color can be styled as well.
 *
 * @param modifier                  Modifier to apply attributes to
 * @param progressBarSizeDp         size of the progress bar in dp
 * @param progressBarStrokeWidth    float value of progress bar stroke width
 * @param backgroundColor           background color of the progress bar (here you will match the background color of the surface that you are composing this progress bar)
 * @param primaryGradient           primary color to make the gradient
 * @param secondaryGradient         secondary color to make the gradient
 * @param progressValue             value to be applied for the progressed state of the bar
 * @param totalValue                total value to be applied to the progress bar
 * @param progressBarTextSizeSp     progress bar text size in sp
 * @param progressBarTextColor      progress bar text color
 *
 */
@SuppressLint("ComposableNaming")
@Composable
fun CircularProgressBarWithText(
    modifier: Modifier,
    progressBarSizeDp: Int,
    progressBarStrokeWidth: Float,
    backgroundColor: Color,
    primaryGradient: Color,
    secondaryGradient: Color,
    progressValue: Int,
    totalValue: Int,
    progressBarTextSizeSp: Int,
    progressBarTextColor: Color
) {
    Box(
        modifier = Modifier.size(progressBarSizeDp.dp)
    ) {
        Canvas(
            modifier = modifier
        ) {
            drawArc(
                color = backgroundColor,
                startAngle = PROGRESS_START_ANGLE,
                sweepAngle = FULL_ROTATION.toFloat(),
                useCenter = false,
                style = Stroke(width = progressBarStrokeWidth, cap = StrokeCap.Round)
            )
            drawArc(
                brush = Brush.radialGradient(
                    listOf(primaryGradient, secondaryGradient),
                    center = Offset(10.0f, 20.0f)
                ),
                startAngle = PROGRESS_START_ANGLE,
                sweepAngle = MathUtils.calculateAngle(progressValue, totalValue),
                useCenter = false,
                style = Stroke(width = progressBarStrokeWidth, cap = StrokeCap.Round)
            )
        }
        SimpleText(
            modifier = Modifier.align(Alignment.Center),
            textValue = "$progressValue / $totalValue",
            textSizeSp = progressBarTextSizeSp,
            fontWeight = FONT_WEIGHT_BOLD,
            fontStyle = FONT_STYLE_NORMAL,
            fontFamily = FontFamily.SansSerif,
            maxLines = ONE,
            textColor = progressBarTextColor,
            softWrap = true
        )
    }
}