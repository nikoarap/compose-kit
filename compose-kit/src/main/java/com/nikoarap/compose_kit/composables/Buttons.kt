package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * Composable function to create a button with custom styled text, background color, and content color.
 *
 * @param modifier              The modifier for the button.
 * @param buttonText            The text to display on the button.
 * @param contentPadding        Padding values to be applied for the button's intrinsic vertical and horizontal paddings
 * @param backgroundColor       The background color of the button.
 * @param contentColor          The text color of the button content.
 * @param textColor             The text color of the button's text.
 * @param typography            The style of the text in material design scale
 * @param onClick               A lambda function to handle button clicks.
 *
 * This Composable function creates a button with a specified [buttonText], customizable visual properties such as [backgroundColor] for the button background,
 * [contentColor] for the text color of the button content, and [textColor] for the text color of the button's text. The [onClick] lambda is invoked when the button is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledButtonWithText(
 *     modifier = Modifier.fillMaxWidth(),
 *     buttonText = "Click Me",
*      contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     textColor = Color.Black,
 *     typography = MaterialTheme.typography.bodyLarge,
 *     onClick = {
 *         // Handle button click
 *     }
 * )
 * ```
 */
@Composable
fun StyledButtonWithText(
    modifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
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
            color = textColor,
            style = typography,
            maxLines = ONE,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Composable function to create a button with custom styled text, background color, and content color within a Row layout.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param buttonModifier         The modifier for the button.
 * @param buttonText             The text to display on the button.
 * @param contentPadding         Padding values to be applied for the button's intrinsic vertical and horizontal paddings
 * @param backgroundColor        The background color of the button.
 * @param contentColor           The text color of the button content.
 * @param textColor              The text color of the button's text.
 * @param typography             The style of the text in material design scale
 * @param onClick                A lambda function to handle button clicks.
 *
 * This Composable function creates a button with a specified [buttonText], customizable visual properties such as [backgroundColor] for the button background,
 * [contentColor] for the text color of the button content, and [textColor] for the text color of the button's text. The button is placed within a Row layout defined by [modifier], allowing for control over button placement.
 * The [onClick] lambda is invoked when the button is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledButtonWithTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     buttonModifier = Modifier.padding(8.dp),
 *     buttonText = "Click Me",
 *     contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     textColor = Color.Black,
 *     typography = MaterialTheme.typography.bodyLarge,
 *     onClick = {
 *         // Handle button click
 *     }
 * )
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
    textColor: Color,
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
                color = textColor,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Composable function to create a button with custom styled text, background color, and content color within a Column layout.
 *
 * @param modifier              The modifier for the Column layout.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param buttonModifier        The modifier for the button.
 * @param buttonText            The text to display on the button.
 * @param contentPadding        Padding values to be applied for the button's intrinsic vertical and horizontal paddings
 * @param backgroundColor       The background color of the button.
 * @param contentColor          The text color of the button content.
 * @param textColor             The text color of the button's text.
 * @param typography            The style of the text in material design scale
 * @param onClick               A lambda function to handle button clicks.
 *
 * This Composable function creates a button with a specified [buttonText], customizable visual properties such as [backgroundColor] for the button background,
 * [contentColor] for the text color of the button content, and [textColor] for the text color of the button's text. The button is placed within a Column layout defined by [modifier], allowing for control over button placement.
 * The [onClick] lambda is invoked when the button is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledButtonWithTextColumn(
 *     modifier = Modifier.fillMaxWidth(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     buttonModifier = Modifier.padding(8.dp),
 *     buttonText = "Click Me",
 *     contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     textColor = Color.Black,
 *     typography = MaterialTheme.typography.bodyLarge,
 *     onClick = {
 *         // Handle button click
 *     }
 * )
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
    textColor: Color,
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
                color = textColor,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Composable function to create an outlined button with styled text, allowing customization of background color, content color, text color, and text size, and defining a click action.
 *
 * @param modifier              The modifier for the button.
 * @param buttonText            The text to display on the button.
 * @param contentPadding        Padding values to be applied for the button's intrinsic vertical and horizontal paddings
 * @param backgroundColor       The background color of the button.
 * @param contentColor          The text color of the button content.
 * @param textColor             The color of the styled text.
 * @param typography            The style of the text in material design scale
 * @param onClick               A lambda function to handle button clicks.
 *
 * This Composable function creates an outlined button with the specified [buttonText], customizable visual properties such as [backgroundColor] for the button background,
 * [contentColor] for the text color of the button content, and [textColor] for the color of the styled text.
 * The [modifier] can be used to adjust the size and placement of the button. The [onClick] lambda is invoked when the button is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledOutlineButtonWithText(
 *     modifier = Modifier.padding(8.dp),
 *     buttonText = "Click Me",
 *     contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     textColor = Color.Red,
 *     typography = MaterialTheme.typography.bodyLarge,
 *     onClick = {
 *         // Handle button click
 *     }
 * )
 * ```
 */
@Composable
fun StyledOutlineButtonWithText(
    modifier: Modifier,
    buttonText: String,
    contentPadding: PaddingValues,
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
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
        contentPadding = contentPadding
    ) {
        Text(
            text = buttonText,
            color = textColor,
            style = typography,
            maxLines = ONE,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Composable function to create an outlined button with styled text within a row layout, allowing customization of background color, content color, text color, text size, and defining a click action.
 *
 * @param modifier               The modifier for the row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param buttonModifier         The modifier for the button.
 * @param buttonText             The text to display on the button.
 * @param contentPadding         Padding values to be applied for the button's intrinsic vertical and horizontal paddings
 * @param backgroundColor        The background color of the button.
 * @param contentColor           The text color of the button content.
 * @param textColor              The color of the styled text.
 * @param typography             The style of the text in material design scale
 * @param onClick                A lambda function to handle button clicks.
 *
 * This Composable function creates an outlined button with styled text within a row layout. It allows for customizing the visual properties of the button, such as [backgroundColor] for the button background,
 * [contentColor] for the text color of the button content, and [textColor] for the color of the styled text.
 * The [modifier] and [buttonModifier] can be used to adjust the size and placement of the row layout and the button, respectively. The [onClick] lambda is invoked when the button is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledOutlineButtonWithTextRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     buttonModifier = Modifier.padding(8.dp),
 *     buttonText = "Click Me",
 *     contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     textColor = Color.Red,
 *     typography = MaterialTheme.typography.bodyLarge,
 *     onClick = {
 *         // Handle button click
 *     }
 * )
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
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
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
            contentPadding = contentPadding
        ) {
            Text(
                text = buttonText,
                color = textColor,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Composable function to create an outlined button with styled text within a column layout, allowing customization of background color, content color, text color, text size, and defining a click action.
 *
 * @param modifier              The modifier for the column layout.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param buttonModifier        The modifier for the button.
 * @param buttonText            The text to display on the button.
 * @param contentPadding        Padding values to be applied for the button's intrinsic vertical and horizontal paddings
 * @param backgroundColor       The background color of the button.
 * @param contentColor          The text color of the button content.
 * @param textColor             The color of the styled text.
 * @param typography            The style of the text in material design scale
 * @param onClick               A lambda function to handle button clicks.
 *
 * This Composable function creates an outlined button with styled text within a column layout. It allows for customizing the visual properties of the button, such as [backgroundColor] for the button background,
 * [contentColor] for the text color of the button content, and [textColor] for the color of the styled text.
 * The [modifier] and [buttonModifier] can be used to adjust the size and placement of the column layout and the button, respectively. The [onClick] lambda is invoked when the button is clicked.
 *
 * Example usage:
 * ```kotlin
 * StyledOutlineButtonWithTextColumn(
 *     modifier = Modifier.fillMaxWidth(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     buttonModifier = Modifier.padding(8.dp),
 *     buttonText = "Click Me",
 *     contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
 *     backgroundColor = Color.Blue,
 *     contentColor = Color.White,
 *     textColor = Color.Red,
 *     typography = MaterialTheme.typography.bodyLarge,
 *     onClick = {
 *         // Handle button click
 *     }
 * )
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
    backgroundColor: Color,
    contentColor: Color,
    textColor: Color,
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
            contentPadding = contentPadding
        ) {
            Text(
                text = buttonText,
                color = textColor,
                style = typography,
                maxLines = ONE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Composable function to create a Small Floating Action Button (FAB) with an icon.
 *
 * @param backgroundColor  The background color for the Small FAB.
 * @param iconResName      The resource name of the icon to be displayed on the Small FAB.
 * @param iconTintColor    The tint color for the Small FAB icon.
 * @param fabShape         The shape of the Small FAB.
 * @param onClick          Lambda to be executed when the Small FAB is clicked.
 *
 * This Composable function creates a Small Floating Action Button (FAB) with an icon,
 * allowing you to customize its appearance and behavior. Small FABs are typically used in more
 * constrained spaces or to represent secondary actions in your user interface.
 *
 * Example usage:
 * ```kotlin
 * SmallFABWithIcon(
 *     backgroundColor = Color.Red,
 *     iconResName = "ic_favorite",
 *     iconTintColor = Color.White,
 *     fabShape = RoundedCornerShape(50),  // Customize the shape
 *     onClick = {
 *         // Handle the Small FAB click action
 *     }
 * )
 * ```
 */
@Composable
fun SmallFABWithIcon(
    backgroundColor: Color,
    iconResName: String,
    iconTintColor: Color,
    fabShape: Shape,
    onClick: () -> Unit
) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = backgroundColor,
        contentColor = iconTintColor,
        shape = fabShape
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = ICON,
                )
            }
    }
}

/**
 * Composable function to create a Floating Action Button (FAB) with an icon.
 *
 * @param backgroundColor  The background color for the FAB.
 * @param iconResName      The resource name of the icon to be displayed on the FAB.
 * @param iconTintColor    The tint color for the FAB icon.
 * @param fabShape         The shape of the FAB.
 * @param onClick          Lambda to be executed when the FAB is clicked.
 *
 * This Composable function creates a standard Floating Action Button (FAB) with an icon,
 * allowing you to customize its appearance and behavior.
 *
 * Example usage:
 * ```kotlin
 * FABWithIcon(
 *     backgroundColor = Color.Blue,
 *     iconResName = "ic_add",
 *     iconTintColor = Color.White,
 *     fabShape = CircleShape,  // Customize the shape
 *     onClick = {
 *         // Handle the FAB click action
 *     }
 * )
 * ```
 */
@Composable
fun FABWithIcon(
    backgroundColor: Color,
    iconResName: String,
    iconTintColor: Color,
    fabShape: Shape,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        backgroundColor = backgroundColor,
        shape = fabShape
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = ICON,
                    tint = iconTintColor
                )
            }
    }
}

/**
 * Composable function to create a Large Floating Action Button (FAB) with an icon.
 *
 * @param backgroundColor  The background color for the FAB.
 * @param iconResName      The resource name of the icon to be displayed on the FAB.
 * @param iconTintColor    The tint color for the FAB icon.
 * @param fabShape         The shape of the FAB.
 * @param onClick          Lambda to be executed when the FAB is clicked.
 *
 * This Composable function creates a Large FAB with an icon, allowing you to customize
 * its appearance and behavior.
 *
 * Example usage:
 * ```kotlin
 * LargeFABWithIcon(
 *     backgroundColor = Color.Blue,
 *     iconResName = "ic_add",
 *     iconTintColor = Color.White,
 *     fabShape = RoundedCornerShape(50),  // Customize the shape
 *     onClick = {
 *         // Handle the FAB click action
 *     }
 * )
 * ```
 */
@Composable
fun LargeFABWithIcon(
    backgroundColor: Color,
    iconResName: String,
    iconTintColor: Color,
    fabShape: Shape,
    onClick: () -> Unit
) {
    LargeFloatingActionButton(
        onClick = { onClick() },
        containerColor = backgroundColor,
        contentColor = iconTintColor,
        shape = fabShape
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = ICON,
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
 *     text = "Add Item",
 *     textColor = Color.White,
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