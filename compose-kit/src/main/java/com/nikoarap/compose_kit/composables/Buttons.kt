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
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE

/**
 * Wrapper to the Button composable.
 * Composes a Button with a text inside and an onClick listener. Button colors can be styled.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the Button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the Button
 * @param backgroundColor    the background color of this Button when enabled
 * @param contentColor       the content color of this Button when enabled
 * @param onClick            pass a function that will be called when the Button detects click gestures
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
 * Composes a Row containing a Button with a text inside and an onClick listener. Button colors can be styled.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the Button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the Button
 * @param backgroundColor    the background color of this Button when enabled
 * @param contentColor       the content color of this Button when enabled
 * @param onClick            pass a function that will be called when the Button detects click gestures
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
 * Composes a Column containing a Button with a text inside and an onClick listener. Button colors can be styled.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the Button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the Button
 * @param backgroundColor    the background color of this Button when enabled
 * @param contentColor       the content color of this Button when enabled
 * @param onClick            pass a function that will be called when the Button detects click gestures
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
 * Wrapper to the Button composable.
 * Composes a Button with a text inside and an onClick listener. Button colors can be styled. Text color and size can be styled. Text is ellipsized if too long for the width of the button.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the Button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the Button
 * @param backgroundColor    the background color of this Button when enabled
 * @param contentColor       the content color of this Button when enabled
 * @param textColor          color of the text inside the Button
 * @param textSizeSp         size of the text displayed inside the Button in sp
 * @param onClick            pass a function that will be called when the Button detects click gestures
 *
 */
@Composable
fun ButtonWithStyledText(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
    textSizeSp: Int,
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
        Text(
            text = buttonText,
            color = textColor,
            fontSize = textSizeSp.sp,
            maxLines = ONE,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Wrapper to the Button composable.
 * Composes a Row containing a Button with a text inside and an onClick listener. Button colors can be styled. Text color and size can be styled. Text is ellipsized if too long for the width of the button.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the Button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the Button
 * @param backgroundColor    the background color of this Button when enabled
 * @param contentColor       the content color of this Button when enabled
 * @param textColor          color of the text inside the Button
 * @param textSizeSp         size of the text displayed inside the Button in sp
 * @param onClick            pass a function that will be called when the Button detects click gestures
 *
 */
@Composable
fun ButtonWithStyledTextRow(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
    textSizeSp: Int,
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
            Text(
                text = buttonText,
                color = textColor,
                fontSize = textSizeSp.sp,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Wrapper to the Button composable.
 * Composes a Column containing a Button with a text inside and an onClick listener. Button colors can be styled. Text color and size can be styled. Text is ellipsized if too long for the width of the button.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the Button in the modifier you pass to this function
 * @param buttonText         text to be displayed on the Button
 * @param backgroundColor    the background color of this Button when enabled
 * @param contentColor       the content color of this Button when enabled
 * @param textColor          color of the text inside the Button
 * @param textSizeSp         size of the text displayed inside the Button in sp
 * @param onClick            pass a function that will be called when the Button detects click gestures
 *
 */
@Composable
fun ButtonWithStyledTextColumn(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
    textSizeSp: Int,
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
            Text(
                text = buttonText,
                color = textColor,
                fontSize = textSizeSp.sp,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Wrapper to the OutlinedButton composable.
 * Composes an OutlinedButton with a text inside and an onClick listener. OutlinedButton colors can be styled.
 *
 *
 * @param modifier              Modifier to apply attributes to. You can specify the width and height of the OutlinedButton in the modifier you pass to this function
 * @param buttonText            text to be displayed on the OutlinedButton
 * @param backgroundColor       the background color of this OutlinedButton when enabled
 * @param contentColor          the content color of this OutlinedButton when enabled
 * @param onClick               pass a function that will be called when the OutlinedButton detects click gestures
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
 * Composes a Row containing an OutlinedButton with a text inside and an onClick listener. OutlinedButton colors can be styled.
 *
 *
 * @param modifier              Modifier to apply attributes to. You can specify the width and height of the OutlinedButton in the modifier you pass to this function
 * @param buttonText            text to be displayed on the OutlinedButton
 * @param backgroundColor       the background color of this OutlinedButton when enabled
 * @param contentColor          the content color of this OutlinedButton when enabled
 * @param onClick               pass a function that will be called when the OutlinedButton detects click gestures
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
 * Composes a Column containing an OutlinedButton with a text inside and an onClick listener. OutlinedButton colors can be styled.
 *
 *
 * @param modifier              Modifier to apply attributes to. You can specify the width and height of the OutlinedButton in the modifier you pass to this function
 * @param buttonText            text to be displayed on the OutlinedButton
 * @param backgroundColor       the background color of this OutlinedButton when enabled
 * @param contentColor          the content color of this OutlinedButton when enabled
 * @param onClick               pass a function that will be called when the OutlinedButton detects click gestures
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

/**
 * Wrapper to the OutlinedButton composable.
 * Composes a OutlinedButton with a text inside and an onClick listener.
 * OutlinedButton colors can be styled. Text color and size can be styled. Text is ellipsized if too long for the width of the OutlinedButton.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the OutlinedButton in the modifier you pass to this function
 * @param buttonText         text to be displayed on the OutlinedButton
 * @param backgroundColor    the background color of this OutlinedButton when enabled
 * @param contentColor       the content color of this OutlinedButton when enabled
 * @param textColor          color of the text inside the OutlinedButton
 * @param textSizeSp         size of the text displayed inside the OutlinedButton in sp
 * @param onClick            pass a function that will be called when the OutlinedButton detects click gestures
 *
 */
@Composable
fun OutlinedButtonWithStyledText(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
    textSizeSp: Int,
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
        Text(
            text = buttonText,
            color = textColor,
            fontSize = textSizeSp.sp,
            maxLines = ONE,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Wrapper to the OutlinedButton composable.
 * Composes a Row containing a OutlinedButton with a text inside and an onClick listener.
 * OutlinedButton colors can be styled. Text color and size can be styled. Text is ellipsized if too long for the width of the OutlinedButton.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the OutlinedButton in the modifier you pass to this function
 * @param buttonText         text to be displayed on the OutlinedButton
 * @param backgroundColor    the background color of this OutlinedButton when enabled
 * @param contentColor       the content color of this OutlinedButton when enabled
 * @param textColor          color of the text inside the OutlinedButton
 * @param textSizeSp         size of the text displayed inside the OutlinedButton in sp
 * @param onClick            pass a function that will be called when the OutlinedButton detects click gestures
 *
 */
@Composable
fun OutlinedButtonWithStyledTextRow(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
    textSizeSp: Int,
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
            Text(
                text = buttonText,
                color = textColor,
                fontSize = textSizeSp.sp,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Wrapper to the OutlinedButton composable.
 * Composes a Column containing a OutlinedButton with a text inside and an onClick listener.
 * OutlinedButton colors can be styled. Text color and size can be styled. Text is ellipsized if too long for the width of the OutlinedButton.
 *
 * @param modifier           Modifier to apply attributes to. You can specify the width and height of the OutlinedButton in the modifier you pass to this function
 * @param buttonText         text to be displayed on the OutlinedButton
 * @param backgroundColor    the background color of this OutlinedButton when enabled
 * @param contentColor       the content color of this OutlinedButton when enabled
 * @param textColor          color of the text inside the OutlinedButton
 * @param textSizeSp         size of the text displayed inside the OutlinedButton in sp
 * @param onClick            pass a function that will be called when the OutlinedButton detects click gestures
 *
 */
@Composable
fun OutlinedButtonWithStyledTextColumn(
    modifier: Modifier,
    buttonText: String,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
    textSizeSp: Int,
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
            Text(
                text = buttonText,
                color = textColor,
                fontSize = textSizeSp.sp,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}