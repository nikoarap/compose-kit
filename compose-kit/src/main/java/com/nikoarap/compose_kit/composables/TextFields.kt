package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.TEXT_FIELD_CORNER_RADIUS

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
 * ```
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
 * ```
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
 * ```
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
 * A Jetpack Compose composable that displays an OutlinedTextField with customizable styling and behavior.
 *
 * @param modifier                      The modifier for the text field.
 * @param textValue                     The initial text value to display in the field.
 * @param label                         The label text for the field.
 * @param focusedTextColor              The color of the text when the field is focused.
 * @param unfocusedTextColor            The color of the text when the field is unfocused.
 * @param disabledTextColor             The color of the text when the field is disabled.
 * @param focusedContainerColor         The background color of the field when focused.
 * @param unfocusedContainerColor       The background color of the field when unfocused.
 * @param disabledContainerColor        The background color of the field when disabled.
 * @param errorBackgroundColor          The background color of the field when there's an error.
 * @param cursorColor                   The color of the cursor in the field.
 * @param errorCursorColor              The color of the cursor when there's an error.
 * @param iconTintColor                 The color of the clear icon.
 * @param isReadOnly                    A flag to indicate whether the field is read-only.
 * @param isError                       A flag to indicate whether the field has an error.
 * @param singleLine                    A flag to indicate whether the field should be single-line or multi-line.
 * @param inputType                     The visual transformation to apply to the input.
 * @param keyboardType                  The keyboard type to use for the input.
 * @param imeAction                     The IME action to handle when the user presses the Enter key.
 *
 * Example usage:
 * ```
 * StyledEditTextField(
 *     modifier = Modifier.fillMaxWidth(),
 *     textValue = "This is a text value", // you can also use a mutable state value from your viewModel or from a local variable
 *     label = "Text Field",
 *     focusedTextColor = Color.Black,
 *     unfocusedTextColor = Color.Gray,
 *     disabledTextColor = Color.Gray,
 *     focusedContainerColor = Color.White,
 *     unfocusedContainerColor = Color.White,
 *     disabledContainerColor = Color.Gray,
 *     errorBackgroundColor = Color.Red,
 *     cursorColor = Color.Black,
 *     errorCursorColor = Color.Red,
 *     iconTintColor = Color.Gray,
 *     isReadOnly = false,
 *     isError = false,
 *     singleLine = true,
 *     inputType = VisualTransformation.None, // or any other VisualTransformation
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
    focusedTextColor: Color,
    unfocusedTextColor: Color,
    disabledTextColor: Color,
    focusedContainerColor: Color,
    unfocusedContainerColor: Color,
    disabledContainerColor: Color,
    errorBackgroundColor: Color,
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
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            disabledTextColor = disabledTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            disabledContainerColor = disabledContainerColor,
            errorBackgroundColor = errorBackgroundColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor
        )
    )
}

@Composable
private fun createEditTextFieldColors(
    isReadOnly: Boolean,
    focusedTextColor: Color,
    unfocusedTextColor: Color,
    disabledTextColor: Color,
    focusedContainerColor: Color,
    unfocusedContainerColor: Color,
    disabledContainerColor: Color,
    errorBackgroundColor: Color,
    cursorColor: Color,
    errorCursorColor: Color,
): TextFieldColors {

    return TextFieldDefaults.colors(
        focusedTextColor = if (isReadOnly) Color.LightGray else focusedTextColor,
        unfocusedTextColor = if (isReadOnly) Color.LightGray else unfocusedTextColor,
        disabledTextColor = if (isReadOnly) Color.LightGray else disabledTextColor,
        errorTextColor = if (isReadOnly) Color.LightGray else errorCursorColor,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        errorContainerColor = errorBackgroundColor,
        cursorColor = if (isReadOnly) Color.LightGray else cursorColor,
        errorCursorColor = if (isReadOnly) Color.LightGray else errorCursorColor,
    )
}

@Composable
private fun createTextFieldColors(
    isReadOnly: Boolean,
    textColor: Color,
    backgroundColor: Color
    ): TextFieldColors {

    return TextFieldDefaults.colors(
        disabledTextColor = if (isReadOnly) Color.LightGray else textColor,
        focusedContainerColor = backgroundColor,
        unfocusedContainerColor = backgroundColor,
        disabledContainerColor = backgroundColor,
    )
}
