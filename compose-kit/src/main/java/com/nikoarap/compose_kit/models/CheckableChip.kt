package com.nikoarap.compose_kit.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER

/**
 * Data class representing a checkable chip, which can be used in UI components where users can toggle its checked state.
 *
 * @property order                      The order or position of the chip (optional, default is 0).
 * @property aroundSpacingDp            The padding value of the spacing around the chip in dp.
 * @property textValue                  The text value displayed in the chip.
 * @property typography                 The text style for the chip's text (optional, default is [TextStyle.Default]).
 * @property textColor                  The text color of the chip.
 * @property cornerRadiusDp             The corner radius of the chip in density-independent pixels (dp).
 * @property borderColor                The border color of the chip.
 * @property iconSizeDp                 The size of the chip's icon in density-independent pixels (dp).
 * @property iconResName                The name of the icon resource to use in the chip (e.g., "ic_check").
 * @property isChecked                  The checked state of the chip (true if checked, false if unchecked).
 * @property isChecked                  Boolean that indicates if the icon should be at the left or right side of the text
 * @property iconTintColor              The tint color of the icon.
 * @property checkedColor               The background color of the chip when it is checked.
 * @property uncheckedColor             The background color of the chip when it is unchecked.
 * @property onChecked                  A lambda function to handle changes in the chip's checked state.
 */
data class CheckableChip(
    var order: Int = 0,
    var aroundSpacingDp: Int = 4,
    var textValue: String = EMPTY,
    var typography: TextStyle = TextStyle.Default,
    var textColor: Color = Color.Black,
    var cornerRadiusDp: Int = 4,
    var borderColor: Color = Color.Black,
    var iconSizeDp: Int = 16,
    var iconResName: String = PLACEHOLDER,
    var isChecked: Boolean,
    var iconLeftSide: Boolean,
    var iconTintColor: Color = Color.Gray,
    var checkedColor: Color = Color.Cyan,
    var uncheckedColor: Color = Color.White,
    var onChecked: (isChecked: Boolean) -> Unit
)