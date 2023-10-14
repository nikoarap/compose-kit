package com.nikoarap.compose_kit.models

import androidx.compose.ui.graphics.Color
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER

/**
 * A data class representing an action for a bottom app bar, typically used for navigation or menu items.
 *
 * @param order                     The order in which the action should appear in the bottom app bar.
 * @param iconResName               The name of the icon resource to use for the action's icon.
 * @param iconTintColor             The tint color for the action's icon in its default state.
 * @param onClick                   A lambda function to handle the action when it is clicked.
 */
data class BottomAppBarAction(
    var order: Int = 0,
    var iconResName: String = PLACEHOLDER,
    var iconTintColor: Color = Color.Black,
    var onClick: () -> Unit,
)