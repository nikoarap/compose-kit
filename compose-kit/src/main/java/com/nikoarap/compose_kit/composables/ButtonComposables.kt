package com.nikoarap.compose_kit.composables

import android.annotation.SuppressLint
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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