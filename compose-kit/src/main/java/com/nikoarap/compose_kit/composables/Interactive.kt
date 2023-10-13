package com.nikoarap.compose_kit.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.CHECKED_CHIP_ALPHA
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.FONT_STYLE_NORMAL
import com.nikoarap.compose_kit.utils.Constants.Companion.FW_MEDIUM
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import com.nikoarap.compose_kit.utils.LayoutUtils


/**
 * Composable function to display a checkable chip with customizable attributes.
 *
 * @param modifier                  The modifier for the CheckableChip composable.
 * @param chipTextValue             The text content to be displayed on the chip.
 * @param chipTextTypography        The style of the text in material design scale
 * @param textSidePaddingsDp        The padding on the sides of the chip text in density-independent pixels (dp).
 * @param textColor                 The color of the chip text.
 * @param cornerRadiusDp            The corner radius of the chip in density-independent pixels (dp).
 * @param borderColor               The border color of the chip.
 * @param iconSizeDp                The size of the check icon in density-independent pixels (dp).
 * @param iconStartPaddingDp        The padding from the start edge of the chip to the check icon in density-independent pixels (dp).
 * @param iconResName               The resource name for the check icon.
 * @param isChecked                 The initial checked state of the chip.
 * @param checkedColor              The color when the chip is checked.
 * @param uncheckedColor            The color when the chip is unchecked.
 * @param onChecked                 The callback function to handle the chip's checked state changes.
 *
 * This Composable function creates a checkable chip with customizable attributes. You can specify the [modifier],
 * [chipTextValue], [chipTextTypography], [textSidePaddingsDp], [textColor], [cornerRadiusDp], [borderColor], [iconSizeDp],
 * [iconStartPaddingDp], [iconResName], [isChecked], [checkedColor], [uncheckedColor], and [onChecked] callback. The chip's
 * appearance and behavior change based on the checked state.
 *
 * Example usage:
 * ```
 * CheckableChip(
 *     modifier = Modifier.padding(8.dp),
 *     chipTextValue = "Option 1",
 *     chipTextTypography = MaterialTheme.typography.bodyLarge,
 *     textSidePaddingsDp = 12,
 *     textColor = Color.Black,
 *     cornerRadiusDp = 16,
 *     borderColor = Color.Gray,
 *     iconSizeDp = 20,
 *     iconStartPaddingDp = 8,
 *     iconResName = "ic_check",
 *     isChecked = true,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     onChecked = { isChecked ->
 *         // Handle chip checked state changes
 *     }
 * )
 * ```
 */
@Composable
fun CheckableChip(
    modifier: Modifier,
    chipTextValue: String,
    chipTextTypography: TextStyle,
    textSidePaddingsDp: Int,
    textColor: Color,
    cornerRadiusDp: Int,
    borderColor: Color,
    iconSizeDp: Int,
    iconStartPaddingDp: Int,
    iconResName: String,
    isChecked: Boolean,
    checkedColor: Color,
    uncheckedColor: Color,
    onChecked: (isChecked: Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val surfaceColor by animateColorAsState(targetValue = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor), label = EMPTY)
    val surfaceColorWithAlpha = surfaceColor.copy(alpha = CHECKED_CHIP_ALPHA)
    val surfaceBorderWidth by animateIntAsState(targetValue = if (checkedState) ZERO else ONE, label = EMPTY)

    Surface(
        modifier = modifier
            .border(
                width = surfaceBorderWidth.dp,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadiusDp.dp)
            ),
        shape = RoundedCornerShape(cornerRadiusDp.dp),
        color = surfaceColorWithAlpha
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    checkedState = !checkedState
                    onChecked(checkedState)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (checkedState) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            modifier = Modifier
                                .size(iconSizeDp.dp)
                                .padding(start = iconStartPaddingDp.dp),
                            painter = it,
                            contentDescription = IMAGE,
                            tint = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor)
                        )
                    }
            }
            CustomizedText(
                modifier =  Modifier.padding(start = textSidePaddingsDp.dp, end = textSidePaddingsDp.dp),
                textValue = chipTextValue,
                typography = chipTextTypography,
                maxLines = ONE,
                textColor = textColor,
                softWrap = true
            )
        }
    }
}

/**
 * Composable function to display a checkable chip with customizable attributes in a Row layout.
 *
 * @param modifier                  The modifier for the Row layout.
 * @param horizontalArrangement     The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment         The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param chipModifier              The modifier for the chip container.
 * @param chipTextValue             The text content to be displayed on the chip.
 * @param chipTextTypography        The style of the text in material design scale
 * @param textSidePaddingsDp        The padding on the sides of the chip text in density-independent pixels (dp).
 * @param textColor                 The color of the chip text.
 * @param cornerRadiusDp            The corner radius of the chip in density-independent pixels (dp).
 * @param borderColor               The border color of the chip.
 * @param iconSizeDp                The size of the check icon in density-independent pixels (dp).
 * @param iconStartPaddingDp        The padding from the start edge of the chip to the check icon in density-independent pixels (dp).
 * @param iconResName               The resource name for the check icon.
 * @param isChecked                 The initial checked state of the chip.
 * @param checkedColor              The color when the chip is checked.
 * @param uncheckedColor            The color when the chip is unchecked.
 * @param onChecked                 The callback function to handle the chip's checked state changes.
 *
 * This Composable function creates a checkable chip with customizable attributes within a Row layout. You can specify the
 * [modifier], [chipModifier], [chipTextValue], [chipTextTypography], [textSidePaddingsDp], [textColor], [cornerRadiusDp], [borderColor],
 * [iconSizeDp], [iconStartPaddingDp], [iconResName], [isChecked], [checkedColor], [uncheckedColor], and [onChecked] callback.
 * The chip's appearance and behavior change based on the checked state.
 *
 * Example usage:
 * ```
 * CheckableChipRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     chipModifier = Modifier.padding(8.dp),
 *     chipTextValue = "Option 1",
 *     chipTextTypography = MaterialTheme.typography.bodyLarge,
 *     textSidePaddingsDp = 12,
 *     textColor = Color.Black,
 *     cornerRadiusDp = 16,
 *     borderColor = Color.Gray,
 *     iconSizeDp = 20,
 *     iconStartPaddingDp = 8,
 *     iconResName = "ic_check",
 *     isChecked = true,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     onChecked = { isChecked ->
 *         // Handle chip checked state changes
 *     }
 * )
 * ```
 */
@Composable
fun CheckableChipRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    chipModifier: Modifier,
    chipTextValue: String,
    chipTextTypography: TextStyle,
    textSidePaddingsDp: Int,
    textColor: Color,
    cornerRadiusDp: Int,
    borderColor: Color,
    iconSizeDp: Int,
    iconStartPaddingDp: Int,
    iconResName: String,
    isChecked: Boolean,
    checkedColor: Color,
    uncheckedColor: Color,
    onChecked: (isChecked: Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val surfaceColor by animateColorAsState(targetValue = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor), label = EMPTY)
    val surfaceColorWithAlpha = surfaceColor.copy(alpha = CHECKED_CHIP_ALPHA)
    val surfaceBorderWidth by animateIntAsState(targetValue = if (checkedState) ZERO else ONE, label = EMPTY)

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        Surface(
            modifier = chipModifier
                .border(
                    width = surfaceBorderWidth.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(cornerRadiusDp.dp)
                ),
            shape = RoundedCornerShape(cornerRadiusDp.dp),
            color = surfaceColorWithAlpha
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        checkedState = !checkedState
                        onChecked(checkedState)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (checkedState) {
                    LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
                        ?.let { painterResource(it) }?.let {
                            Icon(
                                modifier = Modifier
                                    .size(iconSizeDp.dp)
                                    .padding(start = iconStartPaddingDp.dp),
                                painter = it,
                                contentDescription = IMAGE,
                                tint = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor)
                            )
                        }
                }
                CustomizedText(
                    modifier =  Modifier.padding(start = textSidePaddingsDp.dp, end = textSidePaddingsDp.dp),
                    textValue = chipTextValue,
                    typography = chipTextTypography,
                    maxLines = ONE,
                    textColor = textColor,
                    softWrap = true
                )
            }
        }
    }
}

/**
 * Composable function to display a checkable chip in a column layout with customizable attributes.
 *
 * @param modifier                  The modifier for the CheckableChipColumn composable.
 * @param verticalArrangement       The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment       The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param chipModifier              The modifier for the chip within the column.
 * @param chipTextValue             The text content to be displayed on the chip.
*  @param chipTextTypography        The style of the text in material design scale
 * @param textSidePaddingsDp        The padding on the sides of the chip text in density-independent pixels (dp).
 * @param textColor                 The color of the chip text.
 * @param cornerRadiusDp            The corner radius of the chip in density-independent pixels (dp).
 * @param borderColor               The border color of the chip.
 * @param iconSizeDp                The size of the check icon in density-independent pixels (dp).
 * @param iconStartPaddingDp        The padding from the start edge of the chip to the check icon in density-independent pixels (dp).
 * @param iconResName               The resource name for the check icon.
 * @param isChecked                 The initial checked state of the chip.
 * @param checkedColor              The color when the chip is checked.
 * @param uncheckedColor            The color when the chip is unchecked.
 * @param onChecked                 The callback function to handle the chip's checked state changes.
 *
 * This Composable function creates a checkable chip in a column layout with customizable attributes. You can specify the [modifier],
 * [chipModifier], [chipTextValue], [chipTextTypography], [textSidePaddingsDp], [textColor], [cornerRadiusDp], [borderColor], [iconSizeDp],
 * [iconStartPaddingDp], [iconResName], [isChecked], [checkedColor], [uncheckedColor], and [onChecked] callback. The chip's
 * appearance and behavior change based on the checked state.
 *
 * Example usage:
 * ```
 * CheckableChipColumn(
 *     modifier = Modifier.padding(8.dp),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     chipModifier = Modifier.fillMaxWidth(),
 *     chipTextValue = "Option 1",
 *     chipTextTypography = MaterialTheme.typography.bodyLarge,
 *     textSidePaddingsDp = 12,
 *     textColor = Color.Black,
 *     cornerRadiusDp = 16,
 *     borderColor = Color.Gray,
 *     iconSizeDp = 20,
 *     iconStartPaddingDp = 8,
 *     iconResName = "ic_check",
 *     isChecked = true,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     onChecked = { isChecked ->
 *         // Handle chip checked state changes
 *     }
 * )
 * ```
 */
@Composable
fun CheckableChipColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    chipModifier: Modifier,
    chipTextValue: String,
    chipTextTypography: TextStyle,
    textSidePaddingsDp: Int,
    textColor: Color,
    cornerRadiusDp: Int,
    borderColor: Color,
    iconSizeDp: Int,
    iconStartPaddingDp: Int,
    iconResName: String,
    isChecked: Boolean,
    checkedColor: Color,
    uncheckedColor: Color,
    onChecked: (isChecked: Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val surfaceColor by animateColorAsState(targetValue = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor), label = EMPTY)
    val surfaceColorWithAlpha = surfaceColor.copy(alpha = CHECKED_CHIP_ALPHA)
    val surfaceBorderWidth by animateIntAsState(targetValue = if (checkedState) ZERO else ONE, label = EMPTY)

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Surface(
            modifier = chipModifier
                .border(
                    width = surfaceBorderWidth.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(cornerRadiusDp.dp)
                ),
            shape = RoundedCornerShape(cornerRadiusDp.dp),
            color = surfaceColorWithAlpha
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        checkedState = !checkedState
                        onChecked(checkedState)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (checkedState) {
                    LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
                        ?.let { painterResource(it) }?.let {
                            Icon(
                                modifier = Modifier
                                    .size(iconSizeDp.dp)
                                    .padding(start = iconStartPaddingDp.dp),
                                painter = it,
                                contentDescription = IMAGE,
                                tint = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor)
                            )
                        }
                }
                CustomizedText(
                    modifier =  Modifier.padding(start = textSidePaddingsDp.dp, end = textSidePaddingsDp.dp),
                    textValue = chipTextValue,
                    typography = chipTextTypography,
                    maxLines = ONE,
                    textColor = textColor,
                    softWrap = true
                )
            }
        }
    }
}

/**
 * Composable function to display a customizable switch button.
 *
 * @param checkedColor          The color when the switch is checked.
 * @param uncheckedColor        The color when the switch is unchecked.
 * @param isChecked             The mutable state that represents the checked state of the switch.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. The switch button's appearance and behavior are determined by the provided colors
 * and the checked state.
 *
 * Example usage:
 * ```kotlin
 * var isSwitchChecked by remember { mutableStateOf(false) }
 * SwitchButton(
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = isSwitchChecked
 * )
 * ```
 */
@Composable
fun SwitchButton(
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>
) {
    Switch(
        checked = isChecked.value,
        onCheckedChange = {
            isChecked.value = it
        },
        colors = getSwitchColors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )
    )
}

/**
 * Composable function to display a customizable switch button in a row layout.
 *
 * @param modifier               The modifier for the SwitchButtonRow composable.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param checkedColor           The color when the switch is checked.
 * @param uncheckedColor         The color when the switch is unchecked.
 * @param isChecked              The mutable state that represents the checked state of the switch.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. The switch button is displayed in a row layout with customizable attributes.
 *
 * Example usage:
 * ```kotlin
 * var isSwitchChecked by remember { mutableStateOf(false) }
 * SwitchButtonRow(
 *     modifier = Modifier.padding(8.dp),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = isSwitchChecked
 * )
 * ```
 */
@Composable
fun SwitchButtonRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            },
            colors = getSwitchColors(
                checkedColor = checkedColor,
                uncheckedColor = uncheckedColor
            )
        )
    }
}

/**
 * Composable function to display a customizable switch button in a column layout.
 *
 * @param modifier              The modifier for the SwitchButtonColumn composable.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param checkedColor          The color when the switch is checked.
 * @param uncheckedColor        The color when the switch is unchecked.
 * @param isChecked             The mutable state that represents the checked state of the switch.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. The switch button is displayed in a column layout with customizable attributes.
 *
 * Example usage:
 * ```kotlin
 * var isSwitchChecked by remember { mutableStateOf(false) }
 * SwitchButtonColumn(
 *     modifier = Modifier.padding(8.dp),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = isSwitchChecked
 * )
 * ```
 */
@Composable
fun SwitchButtonColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            },
            colors = getSwitchColors(
                checkedColor = checkedColor,
                uncheckedColor = uncheckedColor
            )
        )
    }
}

/**
 * Composable function to display a switch button with customizable colors and the possibility to be read-only.
 *
 * @param checkedColor          The color when the switch is checked.
 * @param uncheckedColor        The color when the switch is unchecked.
 * @param isChecked             The mutable state that represents the checked state of the switch.
 * @param isReadOnly            A boolean flag indicating whether the switch button is read-only.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. If the [isReadOnly] flag is set to `true`, the switch button is not interactive
 * and cannot be changed. If [isReadOnly] is `false`, the user can change the switch state by tapping on it.
 *
 * Example usage:
 * ```kotlin
 * var isSwitchChecked by remember { mutableStateOf(false) }
 * SwitchButtonReadOnly(
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = isSwitchChecked,
 *     isReadOnly = true
 * )
 * ```
 */
@Composable
fun SwitchButtonReadOnly(
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>,
    isReadOnly: Boolean
) {
    Switch(
        checked = isChecked.value,
        onCheckedChange = {
            if (!isReadOnly)  {
                isChecked.value = it
            }
        },
        colors = getSwitchColors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )
    )
}

/**
 * Composable function to display a switch button in a row layout with customizable colors and the possibility to be read-only.
 *
 * @param modifier               The modifier for the SwitchButtonReadOnlyRow composable.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param checkedColor           The color when the switch is checked.
 * @param uncheckedColor         The color when the switch is unchecked.
 * @param isChecked              The mutable state that represents the checked state of the switch.
 * @param isReadOnly             A boolean flag indicating whether the switch button is read-only.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. If the [isReadOnly] flag is set to `true`, the switch button is not interactive
 * and cannot be changed. If [isReadOnly] is `false`, the user can change the switch state by tapping on it.
 *
 * Example usage:
 * ```kotlin
 * var isSwitchChecked by remember { mutableStateOf(false) }
 * SwitchButtonReadOnlyRow(
 *     modifier = Modifier.padding(8.dp),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = isSwitchChecked,
 *     isReadOnly = true
 * )
 * ```
 */
@Composable
fun SwitchButtonReadOnlyRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>,
    isReadOnly: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                if (!isReadOnly)  {
                    isChecked.value = it
                }
            },
            colors = getSwitchColors(
                checkedColor = checkedColor,
                uncheckedColor = uncheckedColor
            )
        )
    }
}

/**
 * Composable function to display a switch button in a column layout with customizable colors and the possibility to be read-only.
 *
 * @param modifier              The modifier for the SwitchButtonReadOnlyColumn composable.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param checkedColor          The color when the switch is checked.
 * @param uncheckedColor        The color when the switch is unchecked.
 * @param isChecked             The mutable state that represents the checked state of the switch.
 * @param isReadOnly            A boolean flag indicating whether the switch button is read-only.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. If the [isReadOnly] flag is set to `true`, the switch button is not interactive
 * and cannot be changed. If [isReadOnly] is `false`, the user can change the switch state by tapping on it.
 *
 * Example usage:
 * ```kotlin
 * var isSwitchChecked by remember { mutableStateOf(false) }
 * SwitchButtonReadOnlyColumn(
 *     modifier = Modifier.padding(8.dp),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = isSwitchChecked,
 *     isReadOnly = true
 * )
 * ```
 */
@Composable
fun SwitchButtonReadOnlyColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>,
    isReadOnly: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                if (!isReadOnly)  {
                    isChecked.value = it
                }
            },
            colors = getSwitchColors(
                checkedColor = checkedColor,
                uncheckedColor = uncheckedColor
            )
        )
    }
}

/**
 * Composable function to display a checkbox with accompanying text.
 *
 * @param modifier      Modifier for configuring the layout and behavior of the checkbox.
 * @param isChecked     A boolean value indicating whether the checkbox is checked.
 * @param textValue     The text associated with the checkbox.
 * @param typography    The style of the text in material design scale
 *
 * This Composable function creates a checkbox accompanied by text. Users can interact with the checkbox to toggle
 * its checked state. The textValue parameter provides a label or description for the checkbox.
 *
 * Example usage:
 * ```kotlin
 * CheckboxWithText(
 *     modifier = Modifier.fillMaxWidth(),
 *     isChecked = true,
 *     textValue = "Agree to Terms and Conditions",
 *     typography = MaterialTheme.typography.bodyLarge
 * )
 * ```
 */
@Composable
fun CheckboxWithText(
    modifier: Modifier,
    isChecked: Boolean,
    textValue: String,
    typography: TextStyle
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(isChecked) }
    Row(
        modifier = modifier
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = DP_16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null
        )
        Text(
            text = textValue,
            style = typography,
            modifier = Modifier.padding(start = DP_16.dp)
        )
    }
}

/**
 * Creates a [SwitchColors] configuration for a Composable Switch with custom colors.
 *
 * @param checkedColor The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor The color to use for the thumb and track when the switch is in the unchecked state.
 * @return A [SwitchColors] configuration with custom colors for the switch.
 */
@Composable
private fun getSwitchColors(
    checkedColor: Color,
    uncheckedColor: Color
): SwitchColors {
    return SwitchDefaults.colors(
        checkedThumbColor = checkedColor,
        uncheckedThumbColor = uncheckedColor,
        checkedTrackColor = checkedColor,
        uncheckedTrackColor = uncheckedColor
    )
}