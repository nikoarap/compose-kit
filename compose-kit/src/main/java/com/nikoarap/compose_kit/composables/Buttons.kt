package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_12
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_24
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_32
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_48
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * A composable function that creates a styled button with text.
 *
 * @param modifier          The modifier to apply to the button.
 * @param buttonText        The text to display on the button.
 * @param contentPadding    The padding values for the button's content.
 * @param backgroundColor   The background color of the button.
 * @param contentColor      The color of the button's text content.
 * @param typography        The text style to apply to the button's text.
 * @param onClick           Lambda function to be executed when the button is clicked.
 *
 * Example usage:
 * ```
 * StyledButtonWithText(
 *     modifier = Modifier.padding(16.dp),
 *     buttonText = "Click Me!",
 *     contentPadding = PaddingValues(16.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     typography = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
 * ) {
 *     // Define the action to be executed when the button is clicked.
 *     // For example, you can show a toast or navigate to another screen.
 * }
 * ```
 */
@Composable
fun StyledButtonWithText(
    modifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    backgroundColor: Color,
    contentColor: Color,
    typography: TextStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = contentPadding
    ) {
        Text(
            text = buttonText,
            style = typography,
            maxLines = ONE,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * A composable function that creates a styled button with text wrapped in a Row.
 *
 * @param modifier                  The modifier to apply to the surrounding Row.
 * @param horizontalArrangement     The horizontal arrangement strategy for the Row.
 * @param verticalAlignment         The vertical alignment strategy for the Row.
 * @param buttonModifier            The modifier to apply to the button.
 * @param buttonText                The text to display on the button.
 * @param contentPadding            The padding values for the button's content.
 * @param backgroundColor           The background color of the button.
 * @param contentColor              The color of the button's text content.
 * @param typography                The text style to apply to the button's text.
 * @param onClick                   Lambda function to be executed when the button is clicked.
 *
 * Example usage:
 * ```
 * StyledButtonWithTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     buttonText = "Click Me!",
 *     contentPadding = PaddingValues(16.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     typography = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
 * ) {
 *     // Define the action to be executed when the button is clicked.
 *     // For example, you can show a toast or navigate to another screen.
 * }
 * ```
 */
@Composable
fun StyledButtonWithTextRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    buttonModifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    backgroundColor: Color,
    contentColor: Color,
    typography: TextStyle,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        Button(
            modifier = buttonModifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            ),
            contentPadding = contentPadding
        ) {
            Text(
                text = buttonText,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * A composable function that creates a styled button with text wrapped in a Column.
 *
 * @param modifier                  The modifier to apply to the surrounding Column.
 * @param verticalArrangement       The vertical arrangement strategy for the Column.
 * @param horizontalAlignment       The horizontal alignment strategy for the Column.
 * @param buttonModifier            The modifier to apply to the button.
 * @param buttonText                The text to display on the button.
 * @param contentPadding            The padding values for the button's content.
 * @param backgroundColor           The background color of the button.
 * @param contentColor              The color of the button's text content.
 * @param typography                The text style to apply to the button's text.
 * @param onClick                   Lambda function to be executed when the button is clicked.
 *
 * Example usage:
 * ```
 * StyledButtonWithTextColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     buttonText = "Click Me!",
 *     contentPadding = PaddingValues(16.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     typography = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
 * ) {
 *     // Define the action to be executed when the button is clicked.
 *     // For example, you can show a toast or navigate to another screen.
 * }
 * ```
 */
@Composable
fun StyledButtonWithTextColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    buttonModifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    backgroundColor: Color,
    contentColor: Color,
    typography: TextStyle,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Button(
            modifier = buttonModifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            ),
            contentPadding = contentPadding
        ) {
            Text(
                text = buttonText,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * A composable function that creates a styled outlined button with text.
 *
 * @param modifier                  The modifier to apply to the outlined button.
 * @param buttonText                The text to display on the button.
 * @param contentPadding            The padding values for the button's content.
 * @param borderStrokeWidthDp       The width of the border stroke in DP.
 * @param borderColor               The color of the button's border.
 * @param backgroundColor           The background color of the button.
 * @param contentColor              The color of the button's text content.
 * @param typography                The text style to apply to the button's text.
 * @param onClick                   Lambda function to be executed when the button is clicked.
 *
 * Example usage:
 * ```
 * StyledOutlineButtonWithText(
 *     modifier = Modifier.padding(16.dp),
 *     buttonText = "Click Me!",
 *     contentPadding = PaddingValues(16.dp),
 *     borderStrokeWidthDp = 2,
 *     borderColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     contentColor = Color.Black,
 *     typography = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
 * ) {
 *     // Define the action to be executed when the button is clicked.
 *     // For example, you can show a toast or navigate to another screen.
 * }
 * ```
 */
@Composable
fun StyledOutlineButtonWithText(
    modifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    borderStrokeWidthDp: Int,
    borderColor: Color,
    backgroundColor: Color,
    contentColor: Color,
    typography: TextStyle,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = contentPadding,
        border = BorderStroke(width = borderStrokeWidthDp.dp, color = borderColor)
    ) {
        Text(
            text = buttonText,
            style = typography,
            maxLines = ONE,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * A composable function that creates a styled outlined button with text wrapped in a Row.
 *
 * @param modifier                      The modifier to apply to the surrounding Row.
 * @param horizontalArrangement         The horizontal arrangement strategy for the Row.
 * @param verticalAlignment             The vertical alignment strategy for the Row.
 * @param buttonModifier                The modifier to apply to the outlined button.
 * @param buttonText                    The text to display on the button.
 * @param contentPadding                The padding values for the button's content.
 * @param borderStrokeWidthDp           The width of the border stroke in DP.
 * @param borderColor                   The color of the button's border.
 * @param backgroundColor               The background color of the button.
 * @param contentColor                  The color of the button's text content.
 * @param typography                    The text style to apply to the button's text.
 * @param onClick                       Lambda function to be executed when the button is clicked.
 *
 * Example usage:
 * ```
 * StyledOutlineButtonWithTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     buttonText = "Click Me!",
 *     contentPadding = PaddingValues(16.dp),
 *     borderStrokeWidthDp = 2,
 *     borderColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     contentColor = Color.Black,
 *     typography = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
 * ) {
 *     // Define the action to be executed when the button is clicked.
 *     // For example, you can show a toast or navigate to another screen.
 * }
 * ```
 */
@Composable
fun StyledOutlineButtonWithTextRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    buttonModifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    borderStrokeWidthDp: Int,
    borderColor: Color,
    backgroundColor: Color,
    contentColor: Color,
    typography: TextStyle,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        OutlinedButton(
            modifier = buttonModifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            ),
            contentPadding = contentPadding,
            border = BorderStroke(width = borderStrokeWidthDp.dp, color = borderColor)
        ) {
            Text(
                text = buttonText,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * A composable function that creates a styled outlined button with text wrapped in a Column.
 *
 * @param modifier                  The modifier to apply to the surrounding Column.
 * @param verticalArrangement       The vertical arrangement strategy for the Column.
 * @param horizontalAlignment       The horizontal alignment strategy for the Column.
 * @param buttonModifier            The modifier to apply to the outlined button.
 * @param buttonText                The text to display on the button.
 * @param contentPadding            The padding values for the button's content.
 * @param borderStrokeWidthDp       The width of the border stroke in DP.
 * @param borderColor               The color of the button's border.
 * @param backgroundColor           The background color of the button.
 * @param contentColor              The color of the button's text content.
 * @param typography                The text style to apply to the button's text.
 * @param onClick                   Lambda function to be executed when the button is clicked.
 *
 * Example usage:
 * ```
 * StyledOutlineButtonWithTextColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     buttonText = "Click Me!",
 *     contentPadding = PaddingValues(16.dp),
 *     borderStrokeWidthDp = 2,
 *     borderColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     contentColor = Color.Black,
 *     typography = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
 * ) {
 *     // Define the action to be executed when the button is clicked.
 *     // For example, you can show a toast or navigate to another screen.
 * }
 * ```
 */
@Composable
fun StyledOutlineButtonWithTextColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    buttonModifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    borderStrokeWidthDp: Int,
    borderColor: Color,
    backgroundColor: Color,
    contentColor: Color,
    typography: TextStyle,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        OutlinedButton(
            modifier = buttonModifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            ),
            contentPadding = contentPadding,
            border = BorderStroke(width = borderStrokeWidthDp.dp, color = borderColor)
        ) {
            Text(
                text = buttonText,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * A composable function that creates a FloatingActionButton with an icon.
 *
 * @param size                  The size of the FloatingActionButton in density-independent pixels (dp).
 * @param iconSize              The size of the icon inside the FloatingActionButton in dp.
 * @param backgroundColor       The background color of the FloatingActionButton.
 * @param iconResName           The name of the icon resource to be displayed.
 * @param iconTintColor         The tint color applied to the icon.
 * @param fabShape              The shape of the FloatingActionButton.
 * @param onClick               Lambda function to be executed when the FloatingActionButton is clicked.
 *
 * Example usage:
 * ```
 * FABWithIcon(
 *     size = 56,
 *     iconSize = 24,
 *     backgroundColor = Color.Blue,
 *     iconResName = "ic_fab_icon",
 *     iconTintColor = Color.White,
 *     fabShape = CircleShape,
 * ) {
 *     // Define the action to be executed when the FloatingActionButton is clicked.
 *     // For example, you can trigger a specific action or navigation.
 * }
 * ```
 */
@Composable
fun FABWithIcon(
    size: Int,
    iconSize: Int,
    backgroundColor: Color,
    iconResName: String,
    iconTintColor: Color,
    fabShape: Shape,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier.size(size.dp),
        onClick = { onClick() },
        backgroundColor = backgroundColor,
        shape = fabShape
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(iconSize.dp),
                    painter = it,
                    contentDescription = ICON,
                    tint = iconTintColor
                )
            }
    }
}

/**
 * Composable function to create an Extended Floating Action Button (FAB) with an icon and text.
 *
 * @param backgroundColor  The background color for the FAB.
 * @param iconResName      The resource name of the icon to be displayed on the FAB.
 * @param iconTintColor    The tint color for the FAB icon.
 * @param textValue        The text to be displayed alongside the icon on the FAB.
 * @param textColor        The color of the text on the FAB.
 * @param fabShape         The shape of the FAB.
 * @param onClick          Lambda to be executed when the FAB is clicked.
 *
 * This Composable function creates an Extended FAB with an icon and text, allowing you to customize
 * its appearance and behavior.
 *
 * Example usage:
 * ```kotlin
 * ExtendedFABWithIcon(
 *     backgroundColor = Color.Blue,
 *     iconResName = "ic_add",
 *     iconTintColor = Color.White,
 *     textValue = "Add Item",
 *     textColor = Color.White,
 *     typography = MaterialTheme.typography.bodySmall,
 *     fabShape = RoundedCornerShape(50),  // Customize the shape
 *     onClick = {
 *         // Handle the FAB click action
 *     }
 * )
 * ```
 */
@Composable
fun ExtendedFABWithIcon(
    backgroundColor: Color,
    iconResName: String,
    iconTintColor: Color,
    textValue: String,
    textColor: Color,
    typography: TextStyle,
    fabShape: Shape,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        backgroundColor = backgroundColor,
        shape = fabShape,
        icon = {
            LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        painter = it,
                        contentDescription = ICON,
                        tint = iconTintColor
                    )
                }
        },
        text = {
            Text(text = textValue, color = textColor, style = typography)
        }
    )
}