package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

/**
 * Wrapper to the Button composable.
 * Composes a button with a text inside and an onClick listener. Button and text color can be styled.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the button
 * @param backgroundColor    background color of the button
 * @param contentColor       color of the text
 * @param onClick            pass a function that will be called when the button detects click gestures
 *
 */
@Composable
fun ButtonWithText(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(buttonText)
    }
}

/**
 * Wrapper to the Button composable.
 * Composes a Row containing a button with a text inside and an onClick listener. Button and text color can be styled.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the button
 * @param backgroundColor    background color of the button
 * @param contentColor       color of the text
 * @param onClick            pass a function that will be called when the button detects click gestures
 *
 */
@Composable
fun ButtonWithTextRow(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
        ) {
            Text(buttonText)
        }
    }
}

/**
 * Wrapper to the Button composable.
 * Composes a Column containing a button with a text inside and an onClick listener. Button and text color can be styled.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the button
 * @param backgroundColor    background color of the button
 * @param contentColor       color of the text
 * @param onClick            pass a function that will be called when the button detects click gestures
 *
 */
@Composable
fun ButtonWithTextColumn(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
        ) {
            Text(buttonText)
        }
    }
}

/**
 * Wrapper to the OutlinedButton composable.
 * Composes an outlined button with a text inside and an onClick listener. Button and text color can be styled.
 *
 *
 * @param modifier              Modifier to apply attributes to. You can specify the width and height of the button in the modifier you pass to this function
 * @param buttonText            text to be displayed on the button
 * @param backgroundColor       background button color
 * @param contentColor          content button color
 * @param onClick               onClick listener
 *
 */
@Composable
fun OutlinedButtonWithText(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(buttonText)
    }
}

/**
 * Wrapper to the OutlinedButton composable.
 * Composes a Row containing an outlined button with a text inside and an onClick listener. Button and text color can be styled.
 *
 *
 * @param modifier              Modifier to apply attributes to. You can specify the width and height of the button in the modifier you pass to this function
 * @param buttonText            text to be displayed on the button
 * @param backgroundColor       background button color
 * @param contentColor          content button color
 * @param onClick               onClick listener
 *
 */
@Composable
fun OutlinedButtonWithTextRow(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
        ) {
            Text(buttonText)
        }
    }
}

/**
 * Wrapper to the OutlinedButton composable.
 * Composes a Column containing an outlined button with a text inside and an onClick listener. Button and text color can be styled.
 *
 *
 * @param modifier              Modifier to apply attributes to. You can specify the width and height of the button in the modifier you pass to this function
 * @param buttonText            text to be displayed on the button
 * @param backgroundColor       background button color
 * @param contentColor          content button color
 * @param onClick               onClick listener
 *
 */
@Composable
fun OutlinedButtonWithTextColumn(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
        ) {
            Text(buttonText)
        }
    }
}