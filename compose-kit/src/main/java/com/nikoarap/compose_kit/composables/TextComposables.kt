package com.nikoarap.compose_kit.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Wrapper of the Text Composable.
 * Composes a text that can be styled accordingly.
 *
 * @param modifier     Modifier to apply attributes to
 * @param textValue    string text value
 * @param textSizeSp   size of text in sp
 * @param fontWeight   text font weight (set value from 1 - 1000, e.g. 300 for normal, 600 for bold)
 * @param fontStyle    text font style (0 for normal, 1 for italic)
 * @param fontFamily   font family of text (one of: FontFamily.Default, FontFamily.SansSerif, FontFamily.Serif, FontFamily.Monospace, FontFamily.Cursive)
 * @param maxLines     maximum number of lines for the text to span
 * @param textColor    color of text
 * @param softWrap     Whether the text should break at soft line breaks
 */
@Composable
fun SimpleText(
    modifier: Modifier,
    textValue: String,
    textSizeSp: Int,
    fontWeight: Int,
    fontStyle: Int,
    fontFamily: FontFamily,
    maxLines: Int,
    textColor: Color,
    softWrap: Boolean
) {
    Text(
        modifier = modifier,
        text = textValue,
        fontSize = textSizeSp.sp,
        fontStyle = FontStyle(fontStyle),
        fontWeight = FontWeight(fontWeight),
        color = textColor,
        maxLines = maxLines,
        softWrap = softWrap,
        fontFamily = fontFamily
    )
}