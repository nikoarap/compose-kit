package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function to display text within a colored circle with customizable attributes.
 *
 * @param textValue             The text content to be displayed within the circle.
 * @param textSizeSp            The text size in scale-independent pixels (sp).
 * @param circleSizeDp          The size of the circle in density-independent pixels (dp).
 * @param backgroundColor       The background color of the circle.
 * @param textColor             The color of the text.
 *
 * This Composable function creates a colored circle with the provided [textValue], [textSizeSp], [circleSizeDp], [backgroundColor],
 * and [textColor]. The text is centered within the circle and can be customized with the specified attributes.
 *
 * Example usage:
 * ```
 * SimpleCircle(
 *     textValue = "A",
 *     textSizeSp = 18,
 *     circleSizeDp = 48,
 *     backgroundColor = Color.Blue,
 *     textColor = Color.White
 * )
 * ```
 */
@Composable
fun SimpleCircle(
    textValue: String,
    textSizeSp: Int,
    circleSizeDp: Int,
    backgroundColor: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .size(circleSizeDp.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = textValue,
            color = textColor,
            fontSize = textSizeSp.sp
        )
    }
}