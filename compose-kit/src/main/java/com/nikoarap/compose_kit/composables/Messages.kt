package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.LayoutUtils
import kotlinx.coroutines.launch

/**
 * A Jetpack Compose composable that displays a Snackbar with customizable appearance, including an optional icon and styling.
 * The provided icon allows dismissing the Snackbar when clicked.
 *
 * @param message                       The message to display in the Snackbar.
 * @param snackbarBorderWidthDp         The border width of the Snackbar in DP.
 * @param snackbarBorderColor           The border color of the Snackbar.
 * @param snackbarPaddingDp             The padding around the Snackbar in DP.
 * @param backgroundColor               The background color of the Snackbar.
 * @param messageColor                  The text color of the message.
 * @param messageTypography             The style of the text in material design scale
 * @param iconResName                   The resource name of the optional icon to display for dismissal.
 * @param tintColor                     The tint color of the icon.
 * @param iconSizeDp                    The size of the icon in DP.
 *
 * Example usage:
 * ```
 * StyledSnackbar(
 *     message = "Snackbar Message",
 *     snackbarBorderWidthDp = 2.dp,
 *     snackbarBorderColor = Color.Gray,
 *     snackbarPaddingDp = 16.dp,
 *     backgroundColor = Color.Red,
 *     messageColor = Color.White,
 *     messageTypography = Material.typography.h1,
 *     iconResName = "ic_close",
 *     tintColor = Color.Yellow,
 *     iconSizeDp = 24.dp
 * )
 * ```
 */
@Composable
fun StyledSnackbar(
    message: String,
    snackbarBorderWidthDp: Dp,
    snackbarBorderColor: Color,
    snackbarPaddingDp: Dp,
    backgroundColor: Color,
    messageColor: Color,
    messageTypography: TextStyle,
    iconResName: String?,
    tintColor: Color,
    iconSizeDp: Dp
) {
    val snackbarHostState = remember {SnackbarHostState()}
    val scope = rememberCoroutineScope()
    LaunchedEffect(snackbarHostState) { snackbarHostState.showSnackbar(message) }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.padding(16.dp),
        snackbar = {
            Snackbar(
                modifier = Modifier.border(snackbarBorderWidthDp, snackbarBorderColor).padding(snackbarPaddingDp),
                action = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        }
                    ) {
                        LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
                            ?.let { painterResource(it) }?.let {
                                Icon(
                                    modifier = Modifier.size(iconSizeDp),
                                    painter = it,
                                    contentDescription = ICON,
                                    tint = tintColor
                                )
                        }
                    }
                },
                containerColor = backgroundColor,
                contentColor = messageColor
            ) {
                Text(text = message, style = messageTypography)
            }
        }
    )
}