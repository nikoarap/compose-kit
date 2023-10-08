package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.EDIT_TEXT_CORNER_RADIUS
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY

/**
 * Wrapper to the OutlinedTextField composable.
 * Composes a editable and clickable text field with a label and an icon at it's end, that clears the field.
 * onFieldClicked can be used to pass a function that does something when the field is clicked (e.g. A function that automatically set a text value, or redirection to another view)
 *
 * @param modifier               Modifier to apply attributes to
 * @param textValue              string value of the edit text
 * @param label                  label of the edit text
 * @param onFieldClicked         You can pass a function here that is executed when the edit text is clicked
 * @param onClearClicked         You can pass a function here that is executed when the clear field "x" icon is clicked
 *
 */
@Composable
fun SimpleEditText(
    modifier: Modifier,
    textValue: String,
    label: String,
    onFieldClicked: () -> Unit,
    onClearClicked: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                onClearClicked()
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
        modifier = modifier.clickable {
            onFieldClicked()
        },
        shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
        trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
        label = { Text(label) },
        enabled = false,
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
 * Wrapper to the OutlinedTextField composable.
 * Composes a editable and clickable text field with a label and an icon at it's end, that clears the field.
 * Text field can be styled with colors for it's text, cursor, background etc. If you make the field read-only, all will default to the LightGrey color.
 * onFieldClicked can be used to pass a function that does something when the field is clicked (e.g. A function that automatically set a text value, or redirection to another view)
 *
 * @param modifier               Modifier to apply attributes to
 * @param textValue              string value of the edit text
 * @param label                  label of the edit text
 * @param textColor              The text color to use when the TextField is not in read-only mode.
 * @param disabledTextColor      The text color to use when the TextField is disabled.
 * @param backgroundColor        The background color of the TextField.
 * @param cursorColor            The cursor color to use when the TextField is not in read-only mode.
 * @param errorCursorColor       The cursor color to use when there is an error in the TextField.
 * @param iconTintColor          tint color of the clear field "x" icon
 * @param isReadOnly             if true, edit text is read-only, meaning it can be edited and click listeners do not respond to events
 * @param onFieldClicked         You can pass a function here that is executed when the edit text is clicked
 * @param onClearClicked         You can pass a function here that is executed when the clear field "x" icon is clicked
 *
 */
@Composable
fun StyledEditText(
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
    onFieldClicked: () -> Unit,
    onClearClicked: () -> Unit
) {
    var fieldValue: String = textValue
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                if (!isReadOnly) {
                    onClearClicked()
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
                onFieldClicked()
            }
        },
        shape = RoundedCornerShape(EDIT_TEXT_CORNER_RADIUS.dp),
        trailingIcon = if (fieldValue.isNotBlank()) trailingIconView else null,
        label = { Text(label) },
        enabled = false,
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
fun createTextFieldColors(
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
