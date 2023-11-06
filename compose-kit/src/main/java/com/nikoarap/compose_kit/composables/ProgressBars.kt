package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.nikoarap.compose_kit.styles.DP_12
import com.nikoarap.compose_kit.utils.Constants.Companion.FULL_ROTATION
import com.nikoarap.compose_kit.utils.Constants.Companion.HUNDRED
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
 *     modifier = Modifier.align(Alignment.Center),
 *     progressBarSizeDp = 100.dp,
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
    progressBarSizeDp: Dp,
    progressBarStrokeWidth: Float,
    backgroundColor: Color,
    primaryGradient: Color,
    secondaryGradient: Color,
    progressValue: Int,
    totalValue: Int
) {
    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.size(progressBarSizeDp)
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
 *     modifier = Modifier.align(Alignment.End),
 *     progressBarSizeDp = 100.dp,
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
    progressBarSizeDp: Dp,
    progressBarStrokeWidth: Float,
    backgroundColor: Color,
    primaryGradient: Color,
    secondaryGradient: Color,
    progressValue: Int,
    totalValue: Int,
    progressBarTextColor: Color
) {
    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.size(progressBarSizeDp)
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
            typography = MaterialTheme.typography.titleMedium,
            maxLines = ONE,
            textColor = progressBarTextColor,
            softWrap = true
        )
    }
}

/**
 * Composable function to display a linear determinate progress bar that updates based on the current
 * number of operations relative to the total number of operations.
 *
 * @param modifier                  The modifier for the column around the progress bar.
 * @param currentOperationsCount    The current number of completed operations.
 * @param totalOperations           The total number of operations to complete.
 *
 * Usage:
 * ```
 * LinearDeterminateProgressBar(
 *          modifier = Modifier.fillMaxWidth().padding(DP_8.dp),
 *          currentOperationsCount = 3,
 *          totalOperations = 10
 * )
 * ```
 */
@Composable
fun LinearDeterminateProgressBar(
    modifier: Modifier,
    currentOperationsCount: Int,
    totalOperations: Int
) {
    val percentageProgress = (currentOperationsCount.toFloat() / totalOperations.toFloat()) * HUNDRED
    val currentProgress = remember { mutableFloatStateOf(percentageProgress) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(DP_12),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentOperationsCount < totalOperations) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = currentProgress.floatValue / HUNDRED
            )
        }
    }
    DisposableEffect(currentOperationsCount, totalOperations) {
        currentProgress.floatValue = percentageProgress
        onDispose { }
    }
}

/**
 * Composable function to display a linear determinate progress bar that updates based on the current
 * number of operations relative to the total number of operations.
 *
 * @param modifier                  The modifier for the column around the progress bar.
 * @param currentOperationsCount    The current number of completed operations.
 * @param totalOperations           The total number of operations to complete.
 * @param isLoading                 Flag to indicate whether to show the progress bar.
 *
 * Usage:
 * ```
 * // Display the progress bar for a set of operations
 * LinearDeterminateProgressBar(
 *      modifier = Modifier.fillMaxWidth().padding(DP_8.dp),
 *      currentOperationsCount = 2,
 *      totalOperations = 5,
 *      isLoading = true
 * )
 *
 * // Hide the progress bar (isLoading set to false)
 * LinearDeterminateProgressBar(
 *      modifier = Modifier.fillMaxWidth().padding(DP_8.dp),
 *      currentOperationsCount = 0,
 *      totalOperations = 10,
 *      isLoading = false
*   )
 * ```
 */
@Composable
fun LinearDeterminateProgressBar(
    modifier: Modifier,
    currentOperationsCount: Int,
    totalOperations: Int,
    isLoading: Boolean
) {
    var loading by remember { mutableStateOf(isLoading) }
    val percentageProgress = (currentOperationsCount.toFloat() / totalOperations.toFloat()) * HUNDRED
    val currentProgress = remember { mutableFloatStateOf(percentageProgress) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(DP_12),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(loading) {
            if (loading) {
                currentProgress.floatValue = percentageProgress
            }
            if (currentOperationsCount == totalOperations) {
                loading = false
            }
        }

        if (loading && currentOperationsCount < totalOperations) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = currentProgress.floatValue / HUNDRED
            )
        }
    }
}