package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE

/**
 * A customizable dialog with an icon, text, and buttons.
 *
 * @param isOpen                                Whether the dialog is open or closed.
 * @param iconResName                           The name of the icon resource.
 * @param iconTintColor                         The color of the icon.
 * @param iconSizeDp                            The size of the icon in density-independent pixels (dp).
 * @param dialogHeightDp                        The height of the dialog in density-independent pixels (dp).
 * @param dialogText                            The text content of the dialog.
 * @param dialogTextMaxLines                    The maximum number of lines to display for the dialog text.
 * @param dialogTextColor                       The color of the dialog text.
 * @param positiveButtonText                    The text for the positive (confirm) button.
 * @param negativeButtonText                    The text for the negative (dismiss) button.
 * @param positiveButtonBackgroundColor         The background color of the positive button.
 * @param negativeButtonBackgroundColor         The background color of the negative button.
 * @param dismissOnBackPress                    Whether the dialog should be dismissed when the back button is pressed.
 * @param dismissOnClickOutside                 Whether the dialog should be dismissed when clicked outside of it.
 * @param buttonTextAllCaps                     Whether to convert button text to all uppercase.
 * @param onDismiss                             Callback when the dialog is dismissed.
 * @param onConfirm                             Callback when the positive button is clicked.
 *
 *  * Usage:
 *  ```
 *  CustomizableDialogWithIcon(
 *         isOpen = true,
 *         iconResName ="ic_add" ,
 *         iconTintColor = Color.Black,
 *         iconSizeDp = 24,
 *         dialogHeightDp = 220,
 *         dialogText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam in justo auctor, vulputate libero in, convallis ligula. Praesent malesuada odio in ex congue, nec egestas arcu convallis. Suspendisse potenti. Nulla facilisi. Quisque et justo quis elit venenatis varius. Vestibulum ac tincidunt lectus, et pharetra ex. Vivamus efficitur nulla ac nisl vehicula, nec volutpat metus malesuada. Proin nec purus ut libero cursus pellentesque. Nullam sit amet tortor id nisi dignissim vulputate. In hac habitasse platea dictumst. Integer et purus ac neque sagittis aliquet. Sed ac laoreet odio.",
 *         dialogTextMaxLines = 5,
 *         dialogTextColor = Color.LightGray,
 *         positiveButtonText = "ok",
 *         negativeButtonText = "cancel",
 *         positiveButtonBackgroundColor = Color.Cyan,
 *         negativeButtonBackgroundColor = Color.Cyan,
 *         dismissOnBackPress = true,
 *         dismissOnClickOutside = false,
 *         buttonTextAllCaps = true,
 *         onDismiss = {
 *              //handle on click presses here
 *         },
 *         onConfirm = {
 *              //handle on click presses here
 *         }
 *  )
 *  ```
 *
 *
 */
@Composable
fun CustomizableDialogWithIcon(
    isOpen: Boolean,
    iconResName: String,
    iconTintColor: Color,
    iconSizeDp: Int,
    dialogHeightDp: Int,
    dialogText: String,
    dialogTextMaxLines: Int,
    dialogTextColor: Color,
    positiveButtonText: String,
    negativeButtonText: String,
    positiveButtonBackgroundColor: Color,
    negativeButtonBackgroundColor: Color,
    dismissOnBackPress: Boolean,
    dismissOnClickOutside: Boolean,
    buttonTextAllCaps: Boolean,
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
                    .height(dialogHeightDp.dp),
                shape = RoundedCornerShape(DP_16.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(bottom = DP_16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconFromResource(iconResName, iconTintColor, iconSizeDp)
                    Spacer(modifier = Modifier.height(DP_8.dp))
                    Text(
                        text = dialogText,
                        modifier = Modifier.padding(bottom = DP_16.dp),
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = dialogTextColor,
                        maxLines = dialogTextMaxLines
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(end = DP_16.dp, top = DP_16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(DP_16.dp)
                            .clickable { onDismiss() },
                        text = if (buttonTextAllCaps) negativeButtonText.uppercase() else negativeButtonText,
                        style = MaterialTheme.typography.labelMedium,
                        color = negativeButtonBackgroundColor
                    )
                    Text(
                        modifier = Modifier
                            .padding(DP_16.dp)
                            .clickable { onConfirm() },
                        text = if (buttonTextAllCaps) positiveButtonText.uppercase() else positiveButtonText,
                        style = MaterialTheme.typography.labelMedium,
                        color = positiveButtonBackgroundColor
                    )
                }
            }
        }
    }
}

/**
 * A customizable dialog with an image, text, and buttons.
 *
 * @param isOpen                                Whether the dialog is open or closed.
 * @param painter                               The painter for the image to be displayed.
 * @param dialogHeightDp                        The height of the dialog in density-independent pixels (dp).
 * @param dialogText                            The text content of the dialog.
 * @param dialogTextColor                       The color of the dialog text.
 * @param dialogTextMaxLines                    The maximum number of lines to display for the dialog text.
 * @param positiveButtonText                    The text for the positive (confirm) button.
 * @param negativeButtonText                    The text for the negative (dismiss) button.
 * @param positiveButtonBackgroundColor         The background color of the positive button.
 * @param negativeButtonBackgroundColor         The background color of the negative button.
 * @param dismissOnBackPress                    Whether the dialog should be dismissed when the back button is pressed.
 * @param dismissOnClickOutside                 Whether the dialog should be dismissed when clicked outside of it.
 * @param buttonTextAllCaps                     Whether to convert button text to all uppercase.
 * @param onDismiss                             Callback when the dialog is dismissed.
 * @param onConfirm                             Callback when the positive button is clicked.
 *
 *  *  * Usage:
 *  ```
 *  CustomizableDialogWithImage(
 *        isOpen = true,
 *        painter = imagePainter,
 *        dialogHeightDp = 360,
 *        dialogText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam in justo auctor, vulputate libero in, convallis ligula. Praesent malesuada odio in ex congue, nec egestas arcu convallis. Suspendisse potenti. Nulla facilisi. Quisque et justo quis elit venenatis varius. Vestibulum ac tincidunt lectus, et pharetra ex. Vivamus efficitur nulla ac nisl vehicula, nec volutpat metus malesuada. Proin nec purus ut libero cursus pellentesque. Nullam sit amet tortor id nisi dignissim vulputate. In hac habitasse platea dictumst. Integer et purus ac neque sagittis aliquet. Sed ac laoreet odio.",
 *        dialogTextMaxLines = 5,
 *        dialogTextColor = Color.LightGray,
 *        positiveButtonText = "ok",
 *        negativeButtonText = "cancel",
 *        positiveButtonBackgroundColor = Color.Cyan,
 *        negativeButtonBackgroundColor = Color.Cyan,
 *        dismissOnBackPress = true,
 *        dismissOnClickOutside = false,
 *        buttonTextAllCaps = true,
 *        onDismiss = {
 *              //handle on click presses here
 *        },
 *        onConfirm = {
 *              //handle on click presses here
 *        }
 *  )
 *  ```
 */
@Composable
fun CustomizableDialogWithImage(
    isOpen: Boolean,
    painter: Painter,
    dialogHeightDp: Int,
    dialogText: String,
    dialogTextColor: Color,
    dialogTextMaxLines: Int,
    positiveButtonText: String,
    negativeButtonText: String,
    positiveButtonBackgroundColor: Color,
    negativeButtonBackgroundColor: Color,
    dismissOnBackPress: Boolean,
    dismissOnClickOutside: Boolean,
    buttonTextAllCaps: Boolean,
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
                    .height(dialogHeightDp.dp),
                shape = RoundedCornerShape(DP_16.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(bottom = DP_16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painter,
                        contentDescription = IMAGE,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.height(160.dp)
                    )
                    Spacer(modifier = Modifier.height(DP_8.dp))
                    Text(
                        text = dialogText,
                        modifier = Modifier.padding(bottom = DP_16.dp),
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = dialogTextColor,
                        maxLines = dialogTextMaxLines
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(end = DP_16.dp, top = DP_16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(DP_16.dp)
                            .clickable { onDismiss() },
                        text = if (buttonTextAllCaps) negativeButtonText.uppercase() else negativeButtonText,
                        style = MaterialTheme.typography.labelMedium,
                        color = negativeButtonBackgroundColor
                    )
                    Text(
                        modifier = Modifier
                            .padding(DP_16.dp)
                            .clickable { onConfirm() },
                        text = if (buttonTextAllCaps) positiveButtonText.uppercase() else positiveButtonText,
                        style = MaterialTheme.typography.labelMedium,
                        color = positiveButtonBackgroundColor
                    )
                }
            }
        }
    }
}