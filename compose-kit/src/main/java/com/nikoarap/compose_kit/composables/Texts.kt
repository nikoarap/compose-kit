package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * Composable function to display a text with customizable text attributes.
 *
 * @param modifier              The modifier for the Text composable.
 * @param textValue             The text content to be displayed.
 * @param typography            The style of the text in material design scale
 * @param maxLines              The maximum number of lines to display, 0 for unlimited.
 * @param textColor             The color of the text.
 * @param softWrap              Whether the text should wrap to the next line if it overflows its container.
 *
 * This Composable function creates a Text element with customizable text attributes. You can specify the [textValue], [typography], [maxLines], [textColor], and whether text should [softWrap].
 *
 * Example usage:
 * ```
 * SimpleText(
 *     modifier = Modifier.fillMaxWidth(),
 *     textValue = "Hello, World!",
 *     typography = MaterialTheme.typography.bodyLarge,
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
    typography: TextStyle,
    maxLines: Int,
    textColor: Color,
    softWrap: Boolean
) {
    Text(
        modifier = modifier,
        text = textValue,
        style = typography,
        color = textColor,
        maxLines = maxLines,
        softWrap = softWrap,
    )
}

/**
 * Composable function to display a text within a Row layout with customizable text attributes.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param textModifier           The modifier for the Text composable.
 * @param textValue              The text content to be displayed.
 * @param typography             The style of the text in material design scale
 * @param maxLines               The maximum number of lines to display, 0 for unlimited.
 * @param textColor              The color of the text.
 * @param softWrap               Whether the text should wrap to the next line if it overflows its container.
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row, it displays a Text element
 * with customizable text attributes. You can specify the [textModifier], [textValue],
 * [typography], [maxLines], [textColor], and whether text should [softWrap].
 *
 * Example usage:
 * ```
 * SimpleTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     textModifier = Modifier.padding(16.dp),
 *     textValue = "Hello, World!",
 *     typography = MaterialTheme.typography.bodyLarge,
 *     maxLines = 2,
 *     textColor = Color.Black,
 *     softWrap = true
 * )
 * ```
 */
@Composable
fun CustomizedTextRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    textModifier: Modifier,
    textValue: String,
    typography: TextStyle,
    maxLines: Int,
    textColor: Color,
    softWrap: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        Text(
            modifier = textModifier,
            text = textValue,
            style = typography,
            color = textColor,
            maxLines = maxLines,
            softWrap = softWrap,
        )
    }
}

/**
 * Composable function to display a text within a Column layout with customizable text attributes.
 *
 * @param modifier              The modifier for the Column layout.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param textModifier          The modifier for the Text composable.
 * @param textValue             The text content to be displayed.
 * @param typography            The style of the text in material design scale
 * @param maxLines              The maximum number of lines to display, 0 for unlimited.
 * @param textColor             The color of the text.
 * @param softWrap              Whether the text should wrap to the next line if it overflows its container.
 *
 * This Composable function creates a Column layout with the provided [modifier]. Within the Column, it displays a Text element
 * with customizable text attributes. You can specify the [textModifier], [textValue], [typography],
 * [maxLines], [textColor], and whether text should [softWrap].
 *
 * Example usage:
 * ```
 * SimpleTextColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     textModifier = Modifier.padding(16.dp),
 *     textValue = "Hello, World!",
*      typography = MaterialTheme.typography.bodyLarge,
 *     maxLines = 2,
 *     textColor = Color.Black,
 *     softWrap = true
 * )
 * ```
 */
@Composable
fun CustomizedTextColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    textModifier: Modifier,
    textValue: String,
    typography: TextStyle,
    maxLines: Int,
    textColor: Color,
    softWrap: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            modifier = textModifier,
            text = textValue,
            style = typography,
            color = textColor,
            maxLines = maxLines,
            softWrap = softWrap,
        )
    }
}