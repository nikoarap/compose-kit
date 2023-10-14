package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.EDIT_TEXT_CORNER_RADIUS
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY

/**
 * Composable function to display a simple edit text field with a label and clear button.
 *
 * @param textValue         The initial text value to display in the text field.
 * @param label             The label for the text field.
 * @param isError           A flag to indicate whether an error is present.
 * @param inputType         The visual transformation for the input text.
 * @param onClick           A lambda function to handle clicks on the text field.
 * @param onClear           A lambda function to handle clearing the text field when the clear button is clicked.
 *
 * This Composable function creates a simple edit text field with a label and a clear button.
 * The text field displays the provided [textValue], and users can click on it to trigger the [onClick] action.
 * A clear button is displayed to allow users to clear the text field, and the [onClear] action is triggered when it's clicked.
 *
 * Example usage:
 * ```kotlin
 * SimpleEditText(
 *     textValue = "Hello, World!",
 *     label = "Text Field",
 *     isError = false,
 *     inputType = PasswordVisualTransformation(),
 *     onClick = {
 *         // Handle text field click
 *     },
 *     onClear = {
 *         // Handle clear button click
 *     }
 * )
 * ```
 */
@Composable
fun SimpleEditText(
    textValue: String,
    label: String,
    isError: Boolean,
    inputType: VisualTransformation,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                onClear()
                fieldValue = EMPTY
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = EMPTY,
                tint = Color.LightGray
            )
        }
    }

    OutlinedTextField(
        value = fieldValue,
        onValueChange = { fieldValue = it },
        modifier = Modifier.clickable {
            onClick()
        },
        shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
        trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
        label = { Text(label) },
        enabled = false,
        isError = isError,
        visualTransformation = inputType,
        colors = TextFieldDefaults.textFieldColors(
            textColor =  Color.Black,
            disabledTextColor = Color.Black,
            backgroundColor = Color.White,
            cursorColor = Color.White,
            errorCursorColor = Color.White
        )
    )
}

/**
 * Composable function to display a simple edit text field with a label and clear button in a row layout.
 *
 * @param modifier               The modifier for the row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param isError                A flag to indicate whether an error is present.
 * @param inputType              The visual transformation for the input text.
 * @param textValue              The initial text value to display in the text field.
 * @param label                  The label for the text field.
 * @param onClick                A lambda function to handle clicks on the text field.
 * @param onClear                A lambda function to handle clearing the text field when the clear button is clicked.
 *
 * This Composable function creates a simple edit text field with a label and a clear button, arranged in a horizontal row layout.
 * The text field displays the provided [textValue], and users can click on it to trigger the [onClick] action.
 * A clear button is displayed to allow users to clear the text field, and the [onClear] action is triggered when it's clicked.
 *
 * @param modifier Optional modifier to customize the layout.
 *
 * Example usage:
 * ```kotlin
 * SimpleEditTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     isError = false,
 *     inputType = VisualTransformation.None,
 *     textValue = "Hello, World!",
 *     label = "Text Field",
 *     onClick = {
 *         // Handle text field click
 *     },
 *     onClear = {
 *         // Handle clear button click
 *     }
 * )
 * ```
 */
@Composable
fun SimpleEditTextRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    isError: Boolean,
    inputType: VisualTransformation,
    textValue: String,
    label: String,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                onClear()
                fieldValue = EMPTY
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = EMPTY,
                tint = Color.LightGray
            )
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = { fieldValue = it },
            modifier = Modifier.clickable {
                onClick()
            },
            shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = false,
            isError = isError,
            visualTransformation = inputType,
            colors = TextFieldDefaults.textFieldColors(
                textColor =  Color.Black,
                disabledTextColor = Color.Black,
                backgroundColor = Color.White,
                cursorColor = Color.White,
                errorCursorColor = Color.White
            )
        )
    }
}

/**
 * Composable function to display a simple edit text field with a label and clear button in a column layout.
 *
 * @param modifier              The modifier for the column layout.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param isError               A flag to indicate whether an error is present.
 * @param inputType             The visual transformation for the input text.
 * @param textValue             The initial text value to display in the text field.
 * @param label                 The label for the text field.
 * @param onClick               A lambda function to handle clicks on the text field.
 * @param onClear               A lambda function to handle clearing the text field when the clear button is clicked.
 *
 * This Composable function creates a simple edit text field with a label and a clear button, arranged in a vertical column layout.
 * The text field displays the provided [textValue], and users can click on it to trigger the [onClick] action.
 * A clear button is displayed to allow users to clear the text field, and the [onClear] action is triggered when it's clicked.
 *
 * @param modifier Optional modifier to customize the layout.
 *
 * Example usage:
 * ```kotlin
 * SimpleEditTextColumn(
 *     modifier = Modifier.fillMaxWidth(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     isError = false,
 *     inputType = VisualTransformation.None,
 *     textValue = "Hello, World!",
 *     label = "Text Field",
 *     onClick = {
 *         // Handle text field click
 *     },
 *     onClear = {
 *         // Handle clear button click
 *     }
 * )
 * ```
 */
@Composable
fun SimpleEditTextColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    isError: Boolean,
    inputType: VisualTransformation,
    textValue: String,
    label: String,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                onClear()
                fieldValue = EMPTY
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = EMPTY,
                tint = Color.LightGray
            )
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = { fieldValue = it },
            modifier = Modifier.clickable {
                onClick()
            },
            shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = false,
            isError = isError,
            visualTransformation = inputType,
            colors = TextFieldDefaults.textFieldColors(
                textColor =  Color.Black,
                disabledTextColor = Color.Black,
                backgroundColor = Color.White,
                cursorColor = Color.White,
                errorCursorColor = Color.White
            )
        )
    }
}

/**
 * Composable function to display a styled edit text field with a label, clear button, the possibility to become read-only and customizable visual properties.
 *
 * @param textValue                 The initial text value to display in the text field.
 * @param label                     The label for the text field.
 * @param textColor                 The color of the text when the text field is enabled.
 * @param disabledTextColor         The color of the text when the text field is disabled.
 * @param backgroundColor           The background color of the text field.
 * @param cursorColor               The color of the text cursor.
 * @param errorCursorColor          The color of the text cursor when in error state.
 * @param iconTintColor             The color of the clear button icon.
 * @param isReadOnly                A boolean flag indicating whether the text field is read-only.
 * @param isError                   A flag to indicate whether an error is present.
 * @param inputType                 The visual transformation for the input text.
 * @param onClick                   A lambda function to handle clicks on the text field.
 * @param onClear                   A lambda function to handle clearing the text field when the clear button is clicked.
 *
 * This Composable function creates a styled edit text field with customizable visual properties and the possibility to become read-only.It displays the provided [textValue],
 * and users can click on it to trigger the [onClick] action. A clear button is displayed to allow users to clear the text field,
 * and the [onClear] action is triggered when it's clicked. Visual properties such as text color, background color, cursor color, and more
 * are fully customizable.
 *
 *
 * Example usage:
 * ```kotlin
 * StyledEditText(
 *     textValue = "Hello, World!",
 *     label = "Text Field",
 *     textColor = Color.Black,
 *     disabledTextColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     cursorColor = Color.Blue,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     inputType = VisualTransformation.None,
 *     onClick = {
 *         // Handle text field click
 *     },
 *     onClear = {
 *         // Handle clear button click
 *     }
 * )
 * ```
 */
@Composable
fun StyledEditText(
    textValue: String,
    label: String,
    textColor: Color,
    disabledTextColor: Color,
    backgroundColor: Color,
    cursorColor: Color,
    errorCursorColor: Color,
    iconTintColor: Color,
    isReadOnly: Boolean,
    isError: Boolean,
    inputType: VisualTransformation,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
                    onClear()
                    fieldValue = EMPTY
                }
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = EMPTY,
                tint = iconTintColor
            )
        }
    }

    OutlinedTextField(
        value = fieldValue,
        onValueChange = { fieldValue = it },
        modifier = Modifier.clickable {
            if (!isReadOnly) {
                onClick()
            }
        },
        shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
        trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
        label = { Text(label) },
        enabled = false,
        isError = isError,
        visualTransformation = inputType,
        colors = createTextFieldColors(
            isReadOnly = isReadOnly,
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            backgroundColor = backgroundColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor
        )
    )
}

/**
 * Composable function to display a styled edit text field within a row layout with a label, clear button, the possibility to become read-only and customizable visual properties.
 *
 * @param modifier                  The modifier for the row layout.
 * @param horizontalArrangement     The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment         The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param textValue                 The initial text value to display in the text field.
 * @param label                     The label for the text field.
 * @param textColor                 The color of the text when the text field is enabled.
 * @param disabledTextColor         The color of the text when the text field is disabled.
 * @param backgroundColor           The background color of the text field.
 * @param cursorColor               The color of the text cursor.
 * @param errorCursorColor          The color of the text cursor when in error state.
 * @param iconTintColor             The color of the clear button icon.
 * @param isReadOnly                A boolean flag indicating whether the text field is read-only.
 * @param isError                   A flag to indicate whether an error is present.
 * @param inputType                 The visual transformation for the input text.
 * @param onClick                   A lambda function to handle clicks on the text field.
 * @param onClear                   A lambda function to handle clearing the text field when the clear button is clicked.
 *
 * This Composable function creates a styled edit text field within a row layout with customizable visual properties and the possibility to become read-only. It displays the provided [textValue],
 * and users can click on it to trigger the [onClick] action. A clear button is displayed to allow users to clear the text field, and the [onClear] action is
 * triggered when it's clicked. Visual properties such as text color, background color, cursor color, and more are fully customizable.
 *
 * @param modifier Optional modifier to customize the layout.
 *
 * Example usage:
 * ```kotlin
 * StyledEditTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     textValue = "Hello, World!",
 *     label = "Text Field",
 *     textColor = Color.Black,
 *     disabledTextColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     cursorColor = Color.Blue,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     inputType = VisualTransformation.None,
 *     onClick = {
 *         // Handle text field click
 *     },
 *     onClear = {
 *         // Handle clear button click
 *     }
 * )
 * ```
 */
@Composable
fun StyledEditTextRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    textValue: String,
    label: String,
    textColor: Color,
    disabledTextColor: Color,
    backgroundColor: Color,
    cursorColor: Color,
    errorCursorColor: Color,
    iconTintColor: Color,
    isReadOnly: Boolean,
    isError: Boolean,
    inputType: VisualTransformation,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
                    onClear()
                    fieldValue = EMPTY
                }
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = EMPTY,
                tint = iconTintColor
            )
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = { fieldValue = it },
            modifier = Modifier.clickable {
                if (!isReadOnly) {
                    onClick()
                }
            },
            shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = false,
            isError = isError,
            visualTransformation = inputType,
            colors = createTextFieldColors(
                isReadOnly = isReadOnly,
                textColor = textColor,
                disabledTextColor = disabledTextColor,
                backgroundColor = backgroundColor,
                cursorColor = cursorColor,
                errorCursorColor = errorCursorColor
            )
        )
    }
}

/**
 * A Jetpack Compose composable that displays an OutlinedTextField with customizable styling and behavior, contained in a column layout.
 *
 * @param modifier              The modifier for the column.
 * @param verticalArrangement   The vertical arrangement strategy for the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy for the Column. Default is [Alignment.CenterHorizontally].
 * @param textValue             The initial text value to display in the field.
 * @param label                 The label text for the field.
 * @param textColor             The color of the text when the field is enabled.
 * @param disabledTextColor     The color of the text when the field is disabled.
 * @param backgroundColor       The background color of the field.
 * @param cursorColor           The color of the cursor.
 * @param errorCursorColor      The color of the cursor when an error is present.
 * @param iconTintColor         The color of the clear icon.
 * @param isReadOnly            A flag to indicate whether the field is read-only.
 * @param isError               A flag to indicate whether an error is present.
 * @param inputType             The visual transformation for the input text.
 * @param onClick               A lambda function to execute when the field is clicked.
 * @param onClear               A lambda function to execute when the clear icon is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledEditTextColumn(
 *     modifier = Modifier.fillMaxWidth(),
 *     textValue = "Input Text",
 *     label = "Field Label",
 *     textColor = Color.Black,
 *     disabledTextColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     cursorColor = Color.Blue,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     inputType = VisualTransformation.None,
 *     onClick = { /* handle click action */ },
 *     onClear = { /* handle clear action */ }
 * )
 * ```
 */
@Composable
fun StyledEditTextColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    textValue: String,
    label: String,
    textColor: Color,
    disabledTextColor: Color,
    backgroundColor: Color,
    cursorColor: Color,
    errorCursorColor: Color,
    iconTintColor: Color,
    isReadOnly: Boolean,
    isError: Boolean,
    inputType: VisualTransformation,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
                    onClear()
                    fieldValue = EMPTY
                }
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = EMPTY,
                tint = iconTintColor
            )
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = { fieldValue = it },
            modifier = Modifier.clickable {
                if (!isReadOnly) {
                    onClick()
                }
            },
            shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = false,
            isError = isError,
            visualTransformation = inputType,
            colors = createTextFieldColors(
                isReadOnly = isReadOnly,
                textColor = textColor,
                disabledTextColor = disabledTextColor,
                backgroundColor = backgroundColor,
                cursorColor = cursorColor,
                errorCursorColor = errorCursorColor
            )
        )
    }
}

/**
 * Creates a set of state colors for a TextField based on the provided parameters.
 *
 * @param isReadOnly             Whether the TextField is in read-only mode.
 * @param textColor              The text color to use when the TextField is not in read-only mode.
 * @param disabledTextColor      The text color to use when the TextField is disabled.
 * @param backgroundColor        The background color of the TextField.
 * @param cursorColor            The cursor color to use when the TextField is not in read-only mode.
 * @param errorCursorColor       The cursor color to use when there is an error in the TextField.
 * @return A [TextFieldColors]   object representing the state colors for the TextField.
 */
@Composable
private fun createTextFieldColors(
    isReadOnly: Boolean,
    textColor: Color,
    disabledTextColor: Color,
    backgroundColor: Color,
    cursorColor: Color,
    errorCursorColor: Color,
    ): TextFieldColors {

    return TextFieldDefaults.textFieldColors(
        textColor = if (isReadOnly) Color.LightGray else textColor,
        disabledTextColor = if (isReadOnly) Color.LightGray else disabledTextColor,
        backgroundColor = backgroundColor,
        cursorColor = if (isReadOnly) Color.LightGray else cursorColor,
        errorCursorColor = if (isReadOnly) Color.LightGray else errorCursorColor
    )
}
