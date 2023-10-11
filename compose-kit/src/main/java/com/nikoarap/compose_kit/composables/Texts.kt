package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Composable function to display a text with customizable text attributes.
 *
 * @param modifier              The modifier for the Text composable.
 * @param textValue             The text content to be displayed.
 * @param textSizeSp            The text size in scale-independent pixels (sp).
 * @param fontWeight            The weight of the font (e.g., [FontWeight.Bold]).
 * @param fontStyle             The style of the font (e.g., [FontStyle.Italic]).
 * @param fontFamily            The font family for the text.
 * @param maxLines              The maximum number of lines to display, 0 for unlimited.
 * @param textColor             The color of the text.
 * @param softWrap              Whether the text should wrap to the next line if it overflows its container.
 *
 * This Composable function creates a Text element with customizable text attributes. You can specify the [textValue],
 * [textSizeSp], [fontWeight], [fontStyle], [fontFamily], [maxLines], [textColor], and whether text should [softWrap].
 *
 * Example usage:
 * ```
 * SimpleText(
 *     modifier = Modifier.fillMaxWidth(),
 *     textValue = "Hello, World!",
 *     textSizeSp = 18,
 *     fontWeight = FontWeight.Normal,
 *     fontStyle = FontStyle.Normal,
 *     fontFamily = FontFamily.Serif,
 *     maxLines = 2,
 *     textColor = Color.Black,
 *     softWrap = true
 * )
 * ```
 */
@Composable
fun CustomizedText(
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

/**
 * Composable function to display a text within a Row layout with customizable text attributes.
 *
 * @param modifier              The modifier for the Row layout.
 * @param textModifier          The modifier for the Text composable.
 * @param textValue             The text content to be displayed.
 * @param textSizeSp            The text size in scale-independent pixels (sp).
 * @param fontWeight            The weight of the font (e.g., [FontWeight.Bold]).
 * @param fontStyle             The style of the font (e.g., [FontStyle.Italic]).
 * @param fontFamily            The font family for the text.
 * @param maxLines              The maximum number of lines to display, 0 for unlimited.
 * @param textColor             The color of the text.
 * @param softWrap              Whether the text should wrap to the next line if it overflows its container.
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row, it displays a Text element
 * with customizable text attributes. You can specify the [textModifier], [textValue], [textSizeSp], [fontWeight], [fontStyle],
 * [fontFamily], [maxLines], [textColor], and whether text should [softWrap].
 *
 * Example usage:
 * ```
 * SimpleTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     textModifier = Modifier.padding(16.dp),
 *     textValue = "Hello, World!",
 *     textSizeSp = 18,
 *     fontWeight = FontWeight.Normal,
 *     fontStyle = FontStyle.Normal,
 *     fontFamily = FontFamily.Serif,
 *     maxLines = 2,
 *     textColor = Color.Black,
 *     softWrap = true
 * )
 * ```
 */
@Composable
fun CustomizedTextRow(
    modifier: Modifier,
    textModifier: Modifier,
    textValue: String,
    textSizeSp: Int,
    fontWeight: Int,
    fontStyle: Int,
    fontFamily: FontFamily,
    maxLines: Int,
    textColor: Color,
    softWrap: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = textModifier,
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
}

/**
 * Composable function to display a text within a Column layout with customizable text attributes.
 *
 * @param modifier              The modifier for the Column layout.
 * @param textModifier          The modifier for the Text composable.
 * @param textValue             The text content to be displayed.
 * @param textSizeSp            The text size in scale-independent pixels (sp).
 * @param fontWeight            The weight of the font (e.g., [FontWeight.Bold]).
 * @param fontStyle             The style of the font (e.g., [FontStyle.Italic]).
 * @param fontFamily            The font family for the text.
 * @param maxLines              The maximum number of lines to display, 0 for unlimited.
 * @param textColor             The color of the text.
 * @param softWrap              Whether the text should wrap to the next line if it overflows its container.
 *
 * This Composable function creates a Column layout with the provided [modifier]. Within the Column, it displays a Text element
 * with customizable text attributes. You can specify the [textModifier], [textValue], [textSizeSp], [fontWeight], [fontStyle],
 * [fontFamily], [maxLines], [textColor], and whether text should [softWrap].
 *
 * Example usage:
 * ```
 * SimpleTextColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     textModifier = Modifier.padding(16.dp),
 *     textValue = "Hello, World!",
 *     textSizeSp = 18,
 *     fontWeight = FontWeight.Normal,
 *     fontStyle = FontStyle.Normal,
 *     fontFamily = FontFamily.Serif,
 *     maxLines = 2,
 *     textColor = Color.Black,
 *     softWrap = true
 * )
 * ```
 */
@Composable
fun CustomizedTextColumn(
    modifier: Modifier,
    textModifier: Modifier,
    textValue: String,
    textSizeSp: Int,
    fontWeight: Int,
    fontStyle: Int,
    fontFamily: FontFamily,
    maxLines: Int,
    textColor: Color,
    softWrap: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = textModifier,
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
}