package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nikoarap.compose_kit.utils.Constants.Companion.DIALOG_BUTTON_WEIGHT
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_375
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE

/**
 * A highly customizable Composable function to create a dialog with various parameters to tailor its appearance and behavior.
 *
 * @param isOpen                            Boolean representing whether the dialog is open and should be displayed.
 * @param iconResName                       The name of the icon resource to display in the dialog.
 * @param iconTintColor                     The color for tinting the dialog icon.
 * @param iconSizeDp                        The size of the dialog icon in density-independent pixels (dp).
 * @param dialogText                        The text content to display within the dialog.
 * @param dialogTextFontSizeSp              The font size of the dialog text in scale-independent pixels (sp).
 * @param dialogTextFontWeight              The font weight of the dialog text (e.g., FontWeight.Bold).
 * @param dialogTextColor                   The color of the dialog text.
 * @param positiveButtonText                The text for the positive (confirm) button.
 * @param negativeButtonText                The text for the negative (cancel) button.
 * @param positiveButtonBackgroundColor     The background color of the positive button.
 * @param positiveButtonContentColor        The text color of the positive button.
 * @param negativeButtonBackgroundColor     The background color of the negative button.
 * @param negativeButtonContentColor        The text color of the negative button.
 * @param dismissOnBackPress                Boolean to determine if the dialog should be dismissed when the back button is pressed.
 * @param dismissOnClickOutside             Boolean to determine if the dialog should be dismissed when clicked outside.
 * @param onDismiss                         Lambda function to handle the dismissal of the dialog.
 * @param onConfirm                         Lambda function to handle the positive button click.
 *
 * This Composable function allows you to create a fully customized dialog with a specified icon, text, buttons, and behavior. You can control the appearance and interactions of the dialog by providing various parameters.
 *
 * Example usage:
 * ```
 * CustomizableDialog(
 *     isOpen = isDialogOpen,
 *     iconResName = "icon_name",
 *     iconTintColor = Color.Blue,
 *     iconSizeDp = 48,
 *     dialogText = "This is a customizable dialog.",
 *     dialogTextFontSizeSp = 18,
 *     dialogTextFontWeight = FontWeight.Normal,
 *     dialogTextColor = Color.Black,
 *     positiveButtonText = "OK",
 *     negativeButtonText = "Cancel",
 *     positiveButtonBackgroundColor = Color.Green,
 *     positiveButtonContentColor = Color.White,
 *     negativeButtonBackgroundColor = Color.Red,
 *     negativeButtonContentColor = Color.White,
 *     dismissOnBackPress = true,
 *     dismissOnClickOutside = true,
 *     onDismiss = { isDialogOpen = false },
 *     onConfirm = { /* Handle confirmation */ }
 * )
 * ```
 */
@Composable
fun CustomizableDialogWithIcon(
    isOpen: Boolean,
    iconResName: String,
    iconTintColor: Color,
    iconSizeDp: Int,
    dialogText: String,
    dialogTextFontSizeSp: Int,
    dialogTextFontWeight: FontWeight,
    dialogTextColor: Color,
    positiveButtonText: String,
    negativeButtonText: String,
    positiveButtonBackgroundColor: Color,
    positiveButtonContentColor: Color,
    negativeButtonBackgroundColor: Color,
    negativeButtonContentColor: Color,
    dismissOnBackPress: Boolean,
    dismissOnClickOutside: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isOpen) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DP_375.dp)
                    .padding(DP_16.dp),
                shape = RoundedCornerShape(DP_16.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconFromResource(iconResName, iconTintColor, iconSizeDp)
                    Text(
                        text = dialogText,
                        modifier = Modifier.padding(DP_16.dp),
                        style = TextStyle(fontWeight = dialogTextFontWeight, fontSize = dialogTextFontSizeSp.sp, color = dialogTextColor)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { onDismiss() },
                            modifier = Modifier.padding(DP_8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = negativeButtonBackgroundColor,
                                contentColor = negativeButtonContentColor
                            )
                        ) {
                            Text(negativeButtonText)
                        }
                        TextButton(
                            onClick = { onConfirm() },
                            modifier = Modifier.padding(DP_8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = positiveButtonBackgroundColor,
                                contentColor = positiveButtonContentColor
                            )
                        ) {
                            Text(positiveButtonText)
                        }
                    }
                }
            }
        }
    }
}

/**
 * A Composable function to create a dialog with an image and customizable parameters for text, buttons, and behavior.
 *
 * @param isOpen                            Boolean representing whether the dialog is open and should be displayed.
 * @param painter                           The Painter for the image to be displayed in the dialog.
 * @param dialogText                        The text content to display within the dialog.
 * @param dialogTextFontSizeSp              The font size of the dialog text in scale-independent pixels (sp).
 * @param dialogTextFontWeight              The font weight of the dialog text (e.g., FontWeight.Bold).
 * @param dialogTextColor                   The color of the dialog text.
 * @param positiveButtonText                The text for the positive (confirm) button.
 * @param negativeButtonText                The text for the negative (cancel) button.
 * @param positiveButtonBackgroundColor     The background color of the positive button.
 * @param positiveButtonContentColor        The text color of the positive button.
 * @param negativeButtonBackgroundColor     The background color of the negative button.
 * @param negativeButtonContentColor        The text color of the negative button.
 * @param dismissOnBackPress                Boolean to determine if the dialog should be dismissed when the back button is pressed.
 * @param dismissOnClickOutside             Boolean to determine if the dialog should be dismissed when clicked outside.
 * @param onDismiss                         Lambda function to handle the dismissal of the dialog.
 * @param onConfirm                         Lambda function to handle the positive button click.
 *
 * This Composable function allows you to create a dialog with an image and customize various parameters, including text, button appearance, and behavior. You can control the appearance and interactions of the dialog with the specified parameters.
 *
 * Example usage:
 * ```
 * CustomizableDialogWithImage(
 *     isOpen = isDialogOpen,
 *     painter = rememberPainter(R.drawable.image),
 *     dialogText = "This is a customizable dialog with an image.",
 *     dialogTextFontSizeSp = 18,
 *     dialogTextFontWeight = FontWeight.Normal,
 *     dialogTextColor = Color.Black,
 *     positiveButtonText = "OK",
 *     negativeButtonText = "Cancel",
 *     positiveButtonBackgroundColor = Color.Green,
 *     positiveButtonContentColor = Color.White,
 *     negativeButtonBackgroundColor = Color.Red,
 *     negativeButtonContentColor = Color.White,
 *     dismissOnBackPress = true,
 *     dismissOnClickOutside = true,
 *     onDismiss = { isDialogOpen = false },
 *     onConfirm = { /* Handle confirmation */ }
 * )
 * ```
 */
@Composable
fun CustomizableDialogWithImage(
    isOpen: Boolean,
    painter: Painter,
    dialogText: String,
    dialogTextFontSizeSp: Int,
    dialogTextFontWeight: FontWeight,
    dialogTextColor: Color,
    positiveButtonText: String,
    negativeButtonText: String,
    positiveButtonBackgroundColor: Color,
    positiveButtonContentColor: Color,
    negativeButtonBackgroundColor: Color,
    negativeButtonContentColor: Color,
    dismissOnBackPress: Boolean,
    dismissOnClickOutside: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isOpen) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DP_375.dp)
                    .padding(DP_16.dp),
                shape = RoundedCornerShape(DP_16.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painter,
                        contentDescription = IMAGE,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.height(160.dp)
                    )
                    Text(
                        text = dialogText,
                        modifier = Modifier.padding(DP_16.dp),
                        style = TextStyle(fontWeight = dialogTextFontWeight, fontSize = dialogTextFontSizeSp.sp, color = dialogTextColor)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { onDismiss() },
                            modifier = Modifier.padding(DP_8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = negativeButtonBackgroundColor,
                                contentColor = negativeButtonContentColor
                            )
                        ) {
                            Text(negativeButtonText)
                        }
                        TextButton(
                            onClick = { onConfirm() },
                            modifier = Modifier.padding(DP_8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = positiveButtonBackgroundColor,
                                contentColor = positiveButtonContentColor
                            )
                        ) {
                            Text(positiveButtonText)
                        }
                    }
                }
            }
        }
    }
}