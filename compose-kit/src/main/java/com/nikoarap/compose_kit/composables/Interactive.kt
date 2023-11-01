package com.nikoarap.compose_kit.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.models.CheckableChip
import com.nikoarap.compose_kit.styles.DP_1
import com.nikoarap.compose_kit.styles.DP_16
import com.nikoarap.compose_kit.styles.DP_4
import com.nikoarap.compose_kit.styles.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * A composable function that creates a checkable chip, allowing users to toggle its checked state by clicking.
 * The chip can be easily created by leveraging the [CheckableChip] object.
 * A [CheckableChip] contains data about the order, icon, paddings and color of the chip, as well as an onChecked function that will trigger an event of your preference.
 * This way the data for each chip is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param chip  A [CheckableChip] object containing information about the chip, including its appearance and behavior.
 *
 * @sample
 * ```
 *     val primaryColor = Color(0xFF6495ED)
 *
 *     CreateCheckableChip(
 *         chip = CheckableChip(
 *             textValue = "Example Chip",
 *             aroundSpacingDp = 4,
 *             isChecked = false,
 *             onChecked = { isChecked ->
 *                 // Handle chip checked state change, e.g., update UI or perform an action
 *             },
 *             iconResName = "ic_check",
 *             iconSizeDp = 36,
 *             typography = MaterialTheme.typography.bodyLarge,
 *             textColor = primaryColor,
 *             iconTintColor = primaryColor,
 *             checkedColor = primaryColor.copy(alpha = .3f),
 *             uncheckedColor = Color.White,
 *             borderColor = Color.LightGray,
 *             iconLeftSide = false,
 *             cornerRadiusDp = 16
 *         )
 *     )
 *     ```
 */
@Composable
fun CreateCheckableChip(chip: CheckableChip) {
    var checkedState by remember { mutableStateOf(chip.isChecked) }
    val surfaceColor by animateColorAsState(targetValue = if (checkedState) chip.checkedColor else chip.uncheckedColor, label = EMPTY)
    val surfaceBorderWidth by animateIntAsState(targetValue = if (checkedState) ZERO else ONE, label = EMPTY)

    Surface(
        modifier = Modifier
            .padding(chip.aroundSpacingDp.dp)
            .border(
                width = surfaceBorderWidth.dp,
                color = chip.borderColor,
                shape = RoundedCornerShape(chip.cornerRadiusDp.dp)
            ),
        shape = RoundedCornerShape(chip.cornerRadiusDp.dp),
        color = surfaceColor
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    checkedState = !checkedState
                    chip.onChecked(checkedState)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (chip.iconLeftSide) {
                if (checkedState) {
                    LayoutUtils.getDrawableResourceId(LocalContext.current, chip.iconResName)
                        ?.let { painterResource(it) }?.let {
                            Icon(
                                modifier = Modifier
                                    .size(chip.iconSizeDp.dp)
                                    .padding(start = DP_8),
                                painter = it,
                                contentDescription = ICON,
                                tint = chip.iconTintColor
                            )
                        }
                }
                CustomizedText(
                    modifier =  Modifier.padding(DP_8),
                    textValue = chip.textValue,
                    typography = chip.typography,
                    maxLines = ONE,
                    textColor = chip.textColor,
                    softWrap = true
                )
            } else {
                CustomizedText(
                    modifier =  Modifier.padding(DP_8),
                    textValue = chip.textValue,
                    typography = chip.typography,
                    maxLines = ONE,
                    textColor = chip.textColor,
                    softWrap = true
                )
                if (checkedState) {
                    LayoutUtils.getDrawableResourceId(LocalContext.current, chip.iconResName)
                        ?.let { painterResource(it) }?.let {
                            Icon(
                                modifier = Modifier
                                    .size(chip.iconSizeDp.dp)
                                    .padding(end = DP_8),
                                painter = it,
                                contentDescription = ICON,
                                tint = chip.iconTintColor
                            )
                        }
                }
            }
        }
    }
}

/**
 * A composable function that displays a carousel of checkable chips, allowing users to toggle the checked state of individual chips.
 * The chips can be easily created by leveraging the [CheckableChip] object.
 * A [CheckableChip] contains data about the order, icon, paddings and color of the chip, as well as an onChecked function that will trigger an event of your preference.
 * This way the data for each chip is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param backgroundColor       The background color of the carousel.
 * @param chips                 A list of [CheckableChip] objects representing the individual chips in the carousel.
 *
 * @sample
 * ```
 *     val sampleChips = listOf(
 *     CheckableChip(
 *            aroundSpacingDp = 4,
 *            textValue = "Example Chip",
 *            isChecked = false,
 *            onChecked = { isChecked ->
 *                // Handle chip checked state change, e.g., update UI or perform an action
 *            },
 *            iconResName = "ic_check",
 *            iconSizeDp = 24,
 *            typography = MaterialTheme.typography.bodyMedium,
 *            textColor = Color.Black,
 *            iconTintColor = Color.Gray
 *            checkedColor = Color.Green,
 *            uncheckedColor = Color.Gray,
 *            borderColor = Color.Gray,
 *            cornerRadiusDp = 16
 *     )
 *      //add more chips to the list
 *     )
 *     CheckableChipCarousel(
 *         backgroundColor = Color.White,
 *         chips = sampleChips
 *     )
 *     ```
 */
@Composable
fun CheckableChipCarousel(
    backgroundColor: Color,
    chips: List<CheckableChip>
) {
    Column {
        Surface(
            modifier = Modifier
                .wrapContentWidth(),
            color = backgroundColor,
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (chip in chips) {
                        CreateCheckableChip(chip)
                    }
                }
            }
        }
    }
}

/**
 * Composable function to display a switch button with customizable colors and the possibility to be read-only.
 *
 * @param checkedColor          The color when the switch is checked.
 * @param uncheckedColor        The color when the switch is unchecked.
 * @param isChecked             The boolean that represents the checked state of the switch.
 * @param isReadOnly            A boolean flag indicating whether the switch button is read-only.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. If the [isReadOnly] flag is set to `true`, the switch button is not interactive
 * and cannot be changed. If [isReadOnly] is `false`, the user can change the switch state by tapping on it.
 *
 * Example usage:
 * ```
 *
 * SwitchButton(
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = false,
 *     isReadOnly = true
 * )
 * ```
 */
@Composable
fun SwitchButton(
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: Boolean,
    isReadOnly: Boolean
) {
    var isSwitchChecked by remember { mutableStateOf(isChecked) }
    Switch(
        checked = isSwitchChecked,
        onCheckedChange = {
            if (!isReadOnly)  {
                isSwitchChecked = it
            }
        },
        colors = getSwitchColors(
            isReadOnly = isReadOnly,
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )
    )
}

/**
 * Composable function to display a switch button with an icon resource drawn at its' thumb, customizable colors and the possibility to be read-only.
 *
 * @param checkedColor          The color when the switch is checked.
 * @param uncheckedColor        The color when the switch is unchecked.
 * @param iconResName           The resource name of the icon to be drawn.
 * @param iconSizeDp            The icon size in dp.
 * @param iconTintColor         The tint color of the icon.
 * @param isChecked             The boolean that represents the checked state of the switch.
 * @param isReadOnly            A boolean flag indicating whether the switch button is read-only.
 *
 * This Composable function creates a switch button with customizable colors and allows you to control its state through a
 * [MutableState] parameter [isChecked]. If the [isReadOnly] flag is set to `true`, the switch button is not interactive
 * and cannot be changed. If [isReadOnly] is `false`, the user can change the switch state by tapping on it.
 *
 * Example usage:
 * ```
 *
 * SwitchButton(
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     isChecked = false,
 *     isReadOnly = true
 * )
 * ```
 */
@Composable
fun SwitchButtonWithIcon(
    checkedColor: Color,
    uncheckedColor: Color,
    iconResName: String,
    iconSizeDp: Int,
    iconTintColor: Color,
    isChecked: Boolean,
    isReadOnly: Boolean
) {
    var isSwitchChecked by remember { mutableStateOf(isChecked) }
    Switch(
        checked = isSwitchChecked,
        onCheckedChange = {
            if (!isReadOnly)  {
                isSwitchChecked = it
            }
        },
        thumbContent = {
            LayoutUtils.getDrawableResourceId(LocalContext.current, iconResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        modifier = Modifier.size(iconSizeDp.dp),
                        painter = it,
                        contentDescription = ICON,
                        tint = iconTintColor,
                    )
            }
        },
        colors = getSwitchColors(
            isReadOnly = isReadOnly,
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )
    )
}


/**
 * A composable that displays a switch button with accompanying text on either the left or right side.
 *
 * @param checkedColor              The color of the switch button when it's checked.
 * @param uncheckedColor            The color of the switch button when it's unchecked.
 * @param textColor                 The color of the text.
 * @param textValue                 The text to display alongside the switch button.
 * @param typography                The typography to apply to the text.
 * @param textLeftSide              If true, the text is displayed to the left of the switch button; otherwise, it's on the right.
 * @param isChecked                 The initial state of the switch button.
 * @param isReadOnly                If true, the switch button is in a read-only state and cannot be toggled.
 *
 * Example usage:
 *
 * ```
 * SwitchButtonWithText(
 *     checkedColor = Color.Green,
 *     uncheckedColor = Color.Gray,
 *     textColor = Color.Black,
 *     textValue = "This is a switch button with text",
 *     typography = MaterialTheme.typography.bodyMedium,
 *     textLeftSide = true,
 *     isChecked = true,
 *     isReadOnly = false
 * )
 * ```
 */
@Composable
fun SwitchButtonWithText(
    checkedColor: Color,
    uncheckedColor: Color,
    textColor: Color,
    textValue: String,
    typography: TextStyle,
    textLeftSide: Boolean,
    isChecked: Boolean,
    isReadOnly: Boolean
) {
    var isSwitchChecked by remember { mutableStateOf(isChecked) }
    Row(
        modifier = Modifier.padding(horizontal = DP_1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (textLeftSide) {
            Text(
                modifier = Modifier.padding(start = DP_16, end = DP_4),
                text = textValue,
                style = typography,
                color = textColor
            )
            Switch(
                checked = isSwitchChecked,
                onCheckedChange = {
                    if (!isReadOnly)  {
                        isSwitchChecked = it
                    }
                },
                colors = getSwitchColors(
                    isReadOnly = isReadOnly,
                    checkedColor = checkedColor,
                    uncheckedColor = uncheckedColor
                )
            )
        } else {
            Switch(
                checked = isSwitchChecked,
                onCheckedChange = {
                    if (!isReadOnly)  {
                        isSwitchChecked = it
                    }
                },
                colors = getSwitchColors(
                    isReadOnly = isReadOnly,
                    checkedColor = checkedColor,
                    uncheckedColor = uncheckedColor
                )
            )
            Text(
                modifier = Modifier.padding(start = DP_8),
                text = textValue,
                style = typography,
                color = textColor
            )
        }
    }
}

/**
 * A composable that displays a checkbox with customizable colors and checkmark.
 *
 * @param checkedColor          The color of the checkbox when it's checked.
 * @param uncheckedColor        The color of the checkbox when it's unchecked.
 * @param checkmarkColor        The color of the checkmark inside the checkbox.
 * @param isChecked             The initial state of the checkbox.
 * @param isReadOnly            If true, the checkbox is in a read-only state and cannot be toggled.
 *
 * Example usage:
 *
 * ```
 * Checkbox(
 *     checkedColor = Color.Blue,
 *     uncheckedColor = Color.Gray,
 *     checkmarkColor = Color.White,
 *     isChecked = true,
 *     isReadOnly = false
 * )
 * ```
 */
@Composable
fun Checkbox(
    checkedColor: Color,
    uncheckedColor: Color,
    checkmarkColor: Color,
    isChecked: Boolean,
    isReadOnly: Boolean
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(isChecked) }
    val checkBoxModifier = if (isReadOnly) {
        Modifier.padding(horizontal = DP_16)
    } else {
        Modifier
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = DP_16)
    }
    Checkbox(
        modifier = checkBoxModifier,
        checked = checkedState,
        onCheckedChange = null,
        colors = getCheckboxColors(
            isReadOnly = isReadOnly,
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor,
            checkmarkColor = checkmarkColor
        )
    )
}


/**
 * A composable that displays a checkbox accompanied by text with customizable colors and checkmark.
 *
 * @param checkedColor              The color of the checkbox when it's checked.
 * @param uncheckedColor            The color of the checkbox when it's unchecked.
 * @param checkmarkColor            The color of the checkmark inside the checkbox.
 * @param textColor                 The color of the accompanying text.
 * @param isChecked                 The initial state of the checkbox.
 * @param textValue                 The text to display alongside the checkbox.
 * @param typography                The text style for the accompanying text.
 * @param textLeftSide              If true, the text is displayed to the left of the checkbox; otherwise, it's displayed to the right.
 * @param isReadOnly                If true, the checkbox is in a read-only state and cannot be toggled.
 *
 * Example usage:
 *
 * ```
 * CheckboxWithText(
 *     checkedColor = Color.Blue,
 *     uncheckedColor = Color.Gray,
 *     checkmarkColor = Color.White,
 *     textColor = Color.Black,
 *     isChecked = true,
 *     textValue = "Accept terms and conditions",
 *     typography = MaterialTheme.typography.bodyMedium,
 *     textLeftSide = false,
 *     isReadOnly = false
 * )
 * ```
 */
@Composable
fun CheckboxWithText(
    checkedColor: Color,
    uncheckedColor: Color,
    checkmarkColor: Color,
    textColor: Color,
    isChecked: Boolean,
    textValue: String,
    typography: TextStyle,
    textLeftSide: Boolean,
    isReadOnly: Boolean
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(isChecked) }
    val checkBoxModifier = if (isReadOnly) {
        Modifier.padding(horizontal = DP_16)
    } else {
        Modifier
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = DP_16)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (textLeftSide) {
            Text(
                modifier = Modifier.padding(start = DP_16),
                text = textValue,
                style = typography,
                color = textColor
            )
            Checkbox(
                modifier = checkBoxModifier,
                checked = checkedState,
                onCheckedChange = null,
                colors = getCheckboxColors(
                    isReadOnly = isReadOnly,
                    checkedColor = checkedColor,
                    uncheckedColor = uncheckedColor,
                    checkmarkColor = checkmarkColor
                )
            )
        } else {
            Checkbox(
                modifier = checkBoxModifier,
                checked = checkedState,
                onCheckedChange = null,
                colors = getCheckboxColors(
                    isReadOnly = isReadOnly,
                    checkedColor = checkedColor,
                    uncheckedColor = uncheckedColor,
                    checkmarkColor = checkmarkColor
                )
            )
            Text(
                text = textValue,
                style = typography,
                color = textColor
            )
        }
    }
}

/**
 * Creates a [SwitchColors] configuration for a Composable Switch with custom colors.
 *
 * @param isReadOnly            The color to use for the thumb and track when the switch is in read-only state.
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @return                      A [SwitchColors] configuration with custom colors for the switch.
 */
@Composable
private fun getSwitchColors(
    isReadOnly: Boolean,
    checkedColor: Color,
    uncheckedColor: Color
): SwitchColors {
    return SwitchDefaults.colors(
        checkedThumbColor = if (isReadOnly) Color.Gray else checkedColor,
        uncheckedThumbColor = if (isReadOnly) Color.Gray else uncheckedColor,
        checkedTrackColor = if (isReadOnly) Color.Gray else checkedColor,
        uncheckedTrackColor = if (isReadOnly) Color.Gray else uncheckedColor
    )
}

/**
 * Returns a [CheckboxColors] instance with customizable colors based on the read-only state.
 *
 * @param isReadOnly            If true, the colors for a read-only checkbox are used.
 * @param checkedColor          The color of the checkbox when it's checked.
 * @param uncheckedColor        The color of the checkbox when it's unchecked.
 * @param checkmarkColor        The color of the checkmark inside the checkbox.
 * @return                      A [CheckboxColors] instance with the specified colors based on the read-only state.
 */
@Composable
private fun getCheckboxColors(
    isReadOnly: Boolean,
    checkedColor: Color,
    uncheckedColor: Color,
    checkmarkColor: Color
): CheckboxColors {
    return CheckboxDefaults.colors(
        checkedColor = if (isReadOnly) Color.Gray else checkedColor,
        uncheckedColor = if (isReadOnly) Color.Gray else uncheckedColor,
        checkmarkColor = if (isReadOnly) Color.LightGray else checkmarkColor,
        disabledCheckedColor = Color.Gray,
        disabledUncheckedColor = Color.Gray
    )
}