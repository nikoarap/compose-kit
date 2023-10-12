package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
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
import com.nikoarap.compose_kit.utils.Constants.Companion.TEN_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.TWENTY_FLOAT
import com.nikoarap.compose_kit.utils.MathUtils

/**
 * Composable function to display a circular progress bar with customizable attributes.
 *
 * @param modifier                  The modifier for the CircularProgressBar composable.
 * @param progressBarSizeDp         The size of the circular progress bar in density-independent pixels (dp).
 * @param progressBarStrokeWidth    The width of the progress bar's stroke in pixels.
 * @param backgroundColor           The background color of the progress bar.
 * @param primaryGradient           The primary color of the gradient for the progress indicator.
 * @param secondaryGradient         The secondary color of the gradient for the progress indicator.
 * @param progressValue             The current progress value.
 * @param totalValue                The total value, representing the 100% progress.
 *
 * This Composable function creates a circular progress bar with customizable attributes. You can specify the [modifier],
 * [progressBarSizeDp], [progressBarStrokeWidth], [backgroundColor], [primaryGradient], [secondaryGradient], [progressValue],
 * and [totalValue]. The progress is indicated by a gradient arc within the circular progress bar.
 *
 * Example usage:
 * ```
 * CircularProgressBar(
 *     modifier = Modifier.size(100.dp),
 *     progressBarSizeDp = 200,
 *     progressBarStrokeWidth = 8f,
 *     backgroundColor = Color.Gray,
 *     primaryGradient = Color.Green,
 *     secondaryGradient = Color(0xFF33FF33),
 *     progressValue = 50,
 *     totalValue = 100
 * )
 * ```
 */
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
                    center = Offset(TEN_FLOAT, TWENTY_FLOAT)
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
 * Composable function to display a circular progress bar with customizable attributes and accompanying text at the center of it.
 *
 * @param modifier                  The modifier for the CircularProgressBarWithText composable.
 * @param progressBarSizeDp         The size of the circular progress bar in density-independent pixels (dp).
 * @param progressBarStrokeWidth    The width of the progress bar's stroke in pixels.
 * @param backgroundColor           The background color of the progress bar.
 * @param primaryGradient           The primary color of the gradient for the progress indicator.
 * @param secondaryGradient         The secondary color of the gradient for the progress indicator.
 * @param progressValue             The current progress value.
 * @param totalValue                The total value, representing the 100% progress.
 * @param progressBarTextColor      The color of the progress value and total value text.
 *
 * This Composable function creates a circular progress bar with customizable attributes. You can specify the [modifier],
 * [progressBarSizeDp], [progressBarStrokeWidth], [backgroundColor], [primaryGradient], [secondaryGradient], [progressValue],
 * [totalValue], and [progressBarTextColor]. The progress is indicated by a gradient arc within
 * the circular progress bar, and the current progress value and total value are displayed as text.
 *
 * Example usage:
 * ```
 * CircularProgressBarWithText(
 *     modifier = Modifier.size(100.dp),
 *     progressBarSizeDp = 200,
 *     progressBarStrokeWidth = 8f,
 *     backgroundColor = Color.Gray,
 *     primaryGradient = Color.Green,
 *     secondaryGradient = Color(0xFF33FF33),
 *     progressValue = 50,
 *     totalValue = 100,
 *     progressBarTextColor = Color.Black
 * )
 * ```
 */
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
                    center = Offset(TEN_FLOAT, TWENTY_FLOAT)
                ),
                startAngle = PROGRESS_START_ANGLE,
                sweepAngle = MathUtils.calculateAngle(progressValue, totalValue),
                useCenter = false,
                style = Stroke(width = progressBarStrokeWidth, cap = StrokeCap.Round)
            )
        }
        CustomizedText(
            modifier = Modifier.align(Alignment.Center),
            textValue = "$progressValue / $totalValue",
            typography = MaterialTheme.typography.button,
            maxLines = ONE,
            textColor = progressBarTextColor,
            softWrap = true
        )
    }
}