package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.TEXT_FIELD_CORNER_RADIUS
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY

/**
 * Composable function to display a styled text field with a label, clear button, the possibility to become read-only and customizable visual properties.
 *
 * @param modifier                  The modifier for the text field.
 * @param textValue                 The initial text value to display in the text field.
 * @param label                     The label for the text field.
 * @param textColor                 The color of the text when the text field is enabled.
 * @param backgroundColor           The background color of the text field.
 * @param iconTintColor             The color of the clear button icon.
 * @param isReadOnly                A boolean flag indicating whether the text field is read-only.
 * @param onClick                   A lambda function to handle clicks on the text field.
 *
 * This Composable function creates a styled text field with customizable visual properties and the possibility to become read-only.It displays the provided [textValue],
 * and users can click on it to trigger the [onClick] action. A clear button is displayed to allow users to clear the text field.
=  Visual properties such as text color, background color, cursor color, and more
 * are fully customizable.
 *
 *
 * Example usage:
 * ```kotlin
 * StyledTextField(
 *     modifier = Modifier.fillMaxWidth().padding(16.dp),
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *     label = "Text Field",
 *     textColor = Color.Black,
 *     backgroundColor = Color.White,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     onClick = {
 *         // you can pass a function in your viewModel that triggers an event that will be observed in your fragment / activity
 *     }
 * )
 * ```
 */
@Composable
fun StyledTextField(
    modifier: Modifier,
    textValue: String,
    label: String,
    textColor: Color,
    backgroundColor: Color,
    iconTintColor: Color,
    isReadOnly: Boolean,
    onClick: () -> Unit
) {
    var fieldValue by remember { mutableStateOf(textValue) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
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
        modifier = modifier.clickable {
            if (!isReadOnly) {
                onClick()
            }
        },
        shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS.dp),
        trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
        label = { Text(label) },
        enabled = false,
        colors = createTextFieldColors(
            isReadOnly = isReadOnly,
            textColor = textColor,
            backgroundColor = backgroundColor
        )
    )
}

/**
 * Composable function to display a styled text field within a row layout with a label, clear button, the possibility to become read-only and customizable visual properties.
 *
 * @param modifier                  The modifier for the row layout.
 * @param horizontalArrangement     The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment         The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param textValue                 The initial text value to display in the text field.
 * @param label                     The label for the text field.
 * @param textColor                 The color of the text when the text field is enabled.
 * @param backgroundColor           The background color of the text field.
 * @param iconTintColor             The color of the clear button icon.
 * @param isReadOnly                A boolean flag indicating whether the text field is read-only.
 * @param onClick                   A lambda function to handle clicks on the text field.
 *
 * This Composable function creates a styled text field within a row layout with customizable visual properties and the possibility to become read-only. It displays the provided [textValue],
 * and users can click on it to trigger the [onClick] action. A clear button is displayed to allow users to clear the text field.
 * Visual properties such as text color, background color, cursor color, and more are fully customizable.
 *
 * Example usage:
 * ```kotlin
 * StyledTextFieldRow(
 *    modifier = Modifier.fillMaxSize(),
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *    label = "Text Field",
 *    textColor = Color.Black,
 *    backgroundColor = Color.White,
 *    iconTintColor = Color.Gray,
 *    isReadOnly = false,
 *    onClick = {
 *        // you can pass a function in your viewModel that triggers an event that will be observed in your fragment / activity
 *    }
 * )
 * ```
 */
@Composable
fun StyledTextFieldRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    textValue: String,
    label: String,
    textColor: Color,
    backgroundColor: Color,
    iconTintColor: Color,
    isReadOnly: Boolean,
    onClick: () -> Unit
) {
    var fieldValue by remember { mutableStateOf(textValue) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
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
            shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = false,
            colors = createTextFieldColors(
                isReadOnly = isReadOnly,
                textColor = textColor,
                backgroundColor = backgroundColor
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
 * @param backgroundColor       The background color of the field.
 * @param iconTintColor         The color of the clear icon.
 * @param isReadOnly            A flag to indicate whether the field is read-only.
 * @param onClick               A lambda function to execute when the field is clicked.
 *
 * Example usage:
 * ```kotlin
 *
 * StyledTextFieldColumn(
 *    modifier = Modifier.fillMaxSize(),
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *    label = "Text Field",
 *    textColor = Color.Black,
 *    backgroundColor = Color.White,
 *    iconTintColor = Color.Gray,
 *    isReadOnly = false,
 *    onClick = {
 *        // you can pass a function in your viewModel that triggers an event that will be observed in your fragment / activity
 *    }
 * )
 * ```
 */
@Composable
fun StyledTextFieldColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    textValue: String,
    label: String,
    textColor: Color,
    backgroundColor: Color,
    iconTintColor: Color,
    isReadOnly: Boolean,
    onClick: () -> Unit
) {
    var fieldValue by remember { mutableStateOf(textValue) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
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
            shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = false,
            colors = createTextFieldColors(
                isReadOnly = isReadOnly,
                textColor = textColor,
                backgroundColor = backgroundColor
            )
        )
    }
}

/**
 * A composable for a styled and customizable edit text field with an optional clear button.
 *
 * @param modifier                  Modifier for styling and positioning the edit text field.
 * @param textValue                 The initial text value for the edit text field.
 * @param label                     The label to display above the edit text field.
 * @param textColor                 The color of the text.
 * @param disabledTextColor         The color of the text when the field is disabled.
 * @param backgroundColor           The background color of the edit text field.
 * @param cursorColor               The color of the cursor.
 * @param errorCursorColor          The color of the cursor when an error is present.
 * @param iconTintColor             The tint color of the clear icon.
 * @param isReadOnly                Whether the edit text field is read-only.
 * @param isError                   Whether the field is in an error state.
 * @param singleLine                Whether the edit text field should be a single line.
 * @param inputType                 Visual transformation for the input text (e.g., password, plain text).
 * @param keyboardType              The type of the keyboard input (e.g., text, email).
 * @param imeAction                 The IME action to trigger (e.g., "Done" action).
 *
 * Example usage:
 *
 * ```
 * val textValueState by remember { mutableStateOf("Initial Text") }
 *
 * StyledEditTextField(
 *     modifier = Modifier.fillMaxWidth(),
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *     label = "Enter Text",
 *     textColor = Color.Black,
 *     disabledTextColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     cursorColor = Color.Blue,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     singleLine = true,
 *     inputType = VisualTransformation.None,
 *     keyboardType = KeyboardType.Text,
 *     imeAction = ImeAction.Done
 * )
 * ```
 */
@Composable
fun StyledEditTextField(
    modifier: Modifier,
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
    singleLine: Boolean,
    inputType: VisualTransformation,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    var fieldValue by remember { mutableStateOf(textValue) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
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
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS.dp),
        trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
        singleLine = singleLine,
        label = { Text(label) },
        enabled = !isReadOnly,
        isError = isError,
        visualTransformation = inputType,
        colors = createEditTextFieldColors(
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
 * A composable for a styled and customizable edit text field within a row layout, with an optional clear button.
 *
 * @param modifier                      The modifier for the row layout.
 * @param horizontalArrangement         The horizontal arrangement of elements within the row.
 * @param verticalAlignment             The vertical alignment of elements within the row.
 * @param textValue                     The initial text value for the edit text field.
 * @param label                         The label to display above the edit text field.
 * @param textColor                     The color of the text.
 * @param disabledTextColor             The color of the text when the field is disabled.
 * @param backgroundColor               The background color of the edit text field.
 * @param cursorColor                   The color of the cursor.
 * @param errorCursorColor              The color of the cursor when an error is present.
 * @param iconTintColor                 The tint color of the clear icon.
 * @param isReadOnly                    Whether the edit text field is read-only.
 * @param isError                       Whether the field is in an error state.
 * @param inputType                     Visual transformation for the input text (e.g., password, plain text).
 *
 * Example usage:
 * ```
 * StyledEditTextFieldRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *     label = "Enter Text",
 *     textColor = Color.Black,
 *     disabledTextColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     cursorColor = Color.Blue,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     inputType = VisualTransformation.None,
 * )
 * ```
 *
 * @see StyledEditTextField
 */
@Composable
fun StyledEditTextFieldRow(
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
    inputType: VisualTransformation
) {
    var fieldValue by remember { mutableStateOf(textValue) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
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
            shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = !isReadOnly,
            isError = isError,
            visualTransformation = inputType,
            colors = createEditTextFieldColors(
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
 * A composable for a styled and customizable edit text field within a column layout, with an optional clear button.
 *
 * @param modifier                      The modifier for the column layout.
 * @param verticalArrangement           The vertical arrangement of elements within the column.
 * @param horizontalAlignment           The horizontal alignment of elements within the column.
 * @param textValue                     The initial text value for the edit text field.
 * @param label                         The label to display above the edit text field.
 * @param textColor                     The color of the text.
 * @param disabledTextColor             The color of the text when the field is disabled.
 * @param backgroundColor               The background color of the edit text field.
 * @param cursorColor                   The color of the cursor.
 * @param errorCursorColor              The color of the cursor when an error is present.
 * @param iconTintColor                 The tint color of the clear icon.
 * @param isReadOnly                    Whether the edit text field is read-only.
 * @param isError                       Whether the field is in an error state.
 * @param inputType                     Visual transformation for the input text (e.g., password, plain text).
 *
 * Example usage:
 *
 * ```
 * StyledEditTextFieldColumn(
 *     modifier = Modifier.fillMaxWidth(),
 *     verticalArrangement = Arrangement.Center,
 *     horizontalAlignment = Alignment.CenterHorizontally,
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *     label = "Enter Text",
 *     textColor = Color.Black,
 *     disabledTextColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     cursorColor = Color.Blue,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     inputType = VisualTransformation.None
 * )
 * ```
 *
 * @see StyledEditTextFieldRow
 */
@Composable
fun StyledEditTextFieldColumn(
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
    inputType: VisualTransformation
) {
    var fieldValue by remember { mutableStateOf(textValue) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
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
            shape = RoundedCornerShape(TEXT_FIELD_CORNER_RADIUS.dp),
            trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
            label = { Text(label) },
            enabled = !isReadOnly,
            isError = isError,
            visualTransformation = inputType,
            colors = createEditTextFieldColors(
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
 * @param backgroundColor        The background color of the TextField.
 * @return A [TextFieldColors]   object representing the state colors for the TextField.
 */
@Composable
private fun createTextFieldColors(
    isReadOnly: Boolean,
    textColor: Color,
    backgroundColor: Color
    ): TextFieldColors {

    return TextFieldDefaults.textFieldColors(
        textColor = if (isReadOnly) Color.LightGray else textColor,
        disabledTextColor = if (isReadOnly) Color.LightGray else textColor,
        backgroundColor = backgroundColor,
    )
}

/**
 * Creates a set of state colors for an edit text field based on the provided parameters.
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
private fun createEditTextFieldColors(
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
