package com.nikoarap.compose_kit.models

import androidx.compose.ui.graphics.Color
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER

/**
 * A data class representing an item for a navigation drawer or menu.
 *
 * @param order                     The order in which the item should appear in the menu.
 * @param label                     The label or text to display for the item.
 * @param iconResName               The name of the icon resource to use for the item's icon.
 * @param iconTintColor             The tint color for the item's icon.
 * @param selectedContainerColor    The background color when the item is selected.
 * @param selectedIconColor         The color of the icon when the item is selected.
 * @param selectedTextColor         The color of the text when the item is selected.
 * @param onClick                   A lambda function to handle the action when the item is clicked.
 */
data class NavDrawerItem(
    var order: Int = 0,
    var label: String = EMPTY,
    var iconResName: String = PLACEHOLDER,
    var iconTintColor: Color = Color.Black,
    var selectedContainerColor: Color = Color.Transparent,
    var selectedIconColor: Color = Color.Blue,
    var selectedTextColor: Color = Color.Blue,
    var onClick: () -> Unit,
)