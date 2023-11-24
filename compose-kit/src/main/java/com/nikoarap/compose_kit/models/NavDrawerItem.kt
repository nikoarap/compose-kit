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
 * @param labelColor                The color for the item's label.
 * @param selectedContainerColor    The background color when the item is selected.
 * @param selectedIconColor         The color of the icon when the item is selected.
 * @param selectedTextColor         The color of the text when the item is selected.
 * @param onClick                   A lambda function to handle the action when the item is clicked.
 */
data class NavDrawerItem(
    val order: Int = 0,
    val label: String = EMPTY,
    val iconResName: String = PLACEHOLDER,
    val iconTintColor: Color = Color.White,
    val labelColor: Color = Color.White,
    val selectedContainerColor: Color = Color.Transparent,
    val selectedIconColor: Color = Color.Blue,
    val selectedTextColor: Color = Color.Blue,
    val onClick: () -> Unit,
)