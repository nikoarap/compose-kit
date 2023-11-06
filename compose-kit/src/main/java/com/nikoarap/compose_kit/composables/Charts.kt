package com.nikoarap.compose_kit.composables

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.compose_kit.styles.DP_16
import com.nikoarap.compose_kit.styles.DP_24
import com.nikoarap.compose_kit.styles.DP_32
import com.nikoarap.compose_kit.styles.DP_48
import com.nikoarap.compose_kit.styles.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.ELEVEN_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.NINETY_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.TWO_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO_FLOAT

/**
 * A composable function for displaying a detailed pie chart with animated segments.
 * The chart segments for the pie chart can be easily created by leveraging the [PieChartSegment] object.
 * A [PieChartSegment] contains data about the label, color, as well as text for the details of each segment.
 * This way the data for each item is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param segmentItems                  A list of [PieChartSegment] objects, each representing a segment of the pie chart.
 * @param backgroundColor               The background color of the pie chart card.
 * @param cardPaddingDp                 Padding for the card containing the pie chart.
 * @param cardElevationDp               Elevation for the card.
 * @param chartOuterRadius              The outer radius of the pie chart.
 * @param chartBarWidth                 The width of the chart segments.
 * @param animDurationMs                Duration in milliseconds for the animation.
 *
 * Example usage:
 * ```
 * val segmentItems = listOf(
 *             PieChartSegment(label = "Water", value = 70, color = Color(0xFF6495ED), upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
 *             PieChartSegment(label = "Malt", value = 8, color = Color.Yellow, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
 *             PieChartSegment(label = "Hops", value = 4, color = Color.Green, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
 *             PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray))
 *
 * DetailedPieChart(
 *     segmentItems = segmentItems,
 *     backgroundColor = Color.White,
 *     cardPaddingDp = 8.dp,
 *     cardElevationDp = 8.dp,
 *     chartOuterRadius = 80,
 *     chartBarWidth = 80,
 *     animDurationMs = 1600
 * )
 * ```
 *
 * @see PieChartSegment
 * @see Card
 * @see Canvas
 */
@Composable
fun DetailedPieChart(
    segmentItems: List<PieChartSegment>,
    backgroundColor: Color,
    cardPaddingDp: Dp,
    cardElevationDp: Dp,
    chartOuterRadius: Int,
    chartBarWidth: Int,
    animDurationMs: Int,
) {
    val totalSum: Int = segmentItems.sumOf { it.value }
    var animationPlayed by remember { mutableStateOf(false) }
    var lastValue = ZERO_FLOAT

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) chartOuterRadius * TWO_FLOAT else ZERO_FLOAT,
        animationSpec = tween(
            durationMillis = animDurationMs,
            delayMillis = ZERO,
            easing = LinearOutSlowInEasing
        ), label = EMPTY
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) NINETY_FLOAT * ELEVEN_FLOAT else ZERO_FLOAT,
        animationSpec = tween(
            durationMillis = animDurationMs,
            delayMillis = ZERO,
            easing = LinearOutSlowInEasing
        ), label = EMPTY
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth().padding(cardPaddingDp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = cardElevationDp
        ),
        shape = RoundedCornerShape(DP_16)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(DP_24))
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
}

/**
 * A composable that displays a list of items within a detailed pie chart, each represented by a
 * [PieChartSegment].
 *
 * @param segmentItems A list of [PieChartSegment] objects representing the segments of the pie chart.
 */
@Composable
private fun DetailsPieChart(segmentItems: List<PieChartSegment>) {
    Spacer(modifier = Modifier.height(DP_32))
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .padding(top = DP_16, bottom = DP_8).fillMaxWidth()
    ) {
        items(segmentItems) { segmentItem ->
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
            .padding(vertical = DP_8, horizontal = DP_32),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(color = segmentItem.color, shape = RoundedCornerShape(DP_8))
                    .size(DP_48)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = DP_16),
                    text = segmentItem.label,
                    style = segmentItem.upperTextTypography,
                    color = segmentItem.upperTextColor
                )
                Text(
                    modifier = Modifier.padding(start = DP_16),
                    text = segmentItem.value.toString(),
                    style = segmentItem.lowerTextTypography,
                    color = segmentItem.lowerTextColor
                )
            }
        }
    }
}