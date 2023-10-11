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
 * Composes a simple circular shape with a text that is aligned at its' center.
 *
 * @param textValue          string representing the text value
 * @param textSizeSp         text size in sp
 * @param circleSizeDp       circle size in dp
 * @param backgroundColor    circle background color
 * @param textColor          color of the text
 *
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