package com.nikoarap.compose_kit.models

import androidx.compose.ui.graphics.Color
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER

/**
 * A data class representing an item for a navigation bar.
 *
 * @param order                     The order in which the item should appear in the menu.
 * @param label                     The label or text to display for the item.
 * @param iconResName               The name of the icon resource to use for the item's icon.
 * @param tintColor                 The tint color for the item's icon and label.
 * @param selectedTintColor         The color of the item's icon and label when is selected.
 * @param onSelected                A lambda function to handle the action when the item is selected.
 */
data class NavBottomItem(
    var order: Int = 0,
    var label: String = EMPTY,
    var iconResName: String = PLACEHOLDER,
    var tintColor: Color = Color.Black,
    var selectedTintColor: Color = Color.Blue,
    var onSelected: () -> Unit,
)