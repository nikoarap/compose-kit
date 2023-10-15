package com.nikoarap.compose_kit.composables

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_32
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_48
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.EIGHTY
import com.nikoarap.compose_kit.utils.Constants.Companion.ELEVEN_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.NINETY_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.TWO_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO_FLOAT

/**
 * A composable that displays an animated pie chart with segmented sections.
 * The chart segments for the pie chart can be easily created by leveraging the [PieChartSegment] object.
 * A [PieChartSegment] contains data about the label, color, as well as text for the details of each segment.
 * This way the data for each item is bundled up, making this composable a lot more flexible and reusable.
 *
 *
 * @param segmentItems              A list of [PieChartSegment] objects representing the segments of the pie chart.
 * @param chartOuterRadius          The outer radius of the pie chart.
 * @param chartBarWidth             The width of the chart bars.
 * @param animDuration              The duration of the chart animation.
 */
@Composable
fun DetailedPieChart(
    segmentItems: List<PieChartSegment>,
    chartOuterRadius: Int,
    chartBarWidth: Int,
    animDuration: Int,
) {
    val totalSum: Int = segmentItems.sumOf { it.value }
    var animationPlayed by remember { mutableStateOf(false) }
    var lastValue = ZERO_FLOAT

    //diameter value of the Pie
    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) chartOuterRadius * TWO_FLOAT else ZERO_FLOAT,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = ZERO,
            easing = LinearOutSlowInEasing
        ), label = EMPTY
    )

    // if you want to stabilize the Pie Chart you can use value -90f
    // 90f is used to complete 1/4 of the rotation
    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) NINETY_FLOAT * ELEVEN_FLOAT else ZERO_FLOAT,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = ZERO,
            easing = LinearOutSlowInEasing
        ), label = EMPTY
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(chartOuterRadius.dp * TWO_FLOAT)
                    .rotate(animateRotation)
            ) {
                for (segmentItem in segmentItems) {
                    val sweepAngle = segmentItem.getSweepAngleForSegment(totalSum)
                    drawArc(
                        color = segmentItem.color,
                        startAngle = lastValue,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toFloat(), cap = StrokeCap.Butt)
                    )
                    lastValue += sweepAngle
                }
            }
        }
        DetailsPieChart(segmentItems)
    }
}

/**
 * A composable that displays a list of items within a detailed pie chart, each represented by a
 * [PieChartSegment].
 *
 * @param segmentItems A list of [PieChartSegment] objects representing the segments of the pie chart.
 */
@Composable
private fun DetailsPieChart(segmentItems: List<PieChartSegment>) {
    Column(
        modifier = Modifier
            .padding(top = EIGHTY.dp)
            .fillMaxWidth()
    ) {
        for (segmentItem in segmentItems) {
            DetailsPieChartItem(segmentItem)
        }
    }
}

/**
 * A composable that displays a single item within a detailed pie chart, including a colored box,
 * label, and value.
 *
 * @param segmentItem A [PieChartSegment] object representing the segment's data.
 */
@Composable
private fun DetailsPieChartItem(segmentItem: PieChartSegment) {
    Surface(
        modifier = Modifier
            .padding(vertical = DP_8.dp, horizontal = DP_32.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(color = segmentItem.color, shape = RoundedCornerShape(DP_8.dp))
                    .size(DP_48.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = DP_16.dp),
                    text = segmentItem.label,
                    style = segmentItem.upperTextTypography,
                    color = segmentItem.upperTextColor
                )
                Text(
                    modifier = Modifier.padding(start = DP_16.dp),
                    text = segmentItem.value.toString(),
                    style = segmentItem.lowerTextTypography,
                    color = segmentItem.lowerTextColor
                )
            }
        }
    }
}