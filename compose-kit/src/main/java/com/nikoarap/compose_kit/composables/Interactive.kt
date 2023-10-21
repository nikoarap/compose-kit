package com.nikoarap.compose_kit.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.models.CheckableChip
import com.nikoarap.compose_kit.utils.Constants.Companion.CHECKED_CHIP_ALPHA
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
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
                                    .padding(start = DP_8.dp),
                                painter = it,
                                contentDescription = ICON,
                                tint = chip.iconTintColor
                            )
                        }
                }
                CustomizedText(
                    modifier =  Modifier.padding(DP_8.dp),
                    textValue = chip.textValue,
                    typography = chip.typography,
                    maxLines = ONE,
                    textColor = chip.textColor,
                    softWrap = true
                )
            } else {
                CustomizedText(
                    modifier =  Modifier.padding(DP_8.dp),
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
                                    .padding(end = DP_8.dp),
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
 * @param textLeftSide  Boolean that indicates if the text should be at the left or the right side of the checkbox
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
 *     typography = MaterialTheme.typography.bodyLarge,
 *     textLeftSide = true
 * )
 * ```
 */
@Composable
fun CheckboxWithText(
    modifier: Modifier,
    isChecked: Boolean,
    textValue: String,
    typography: TextStyle,
    textLeftSide: Boolean,
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
        if (textLeftSide) {
            Text(
                text = textValue,
                style = typography,

            )
            Checkbox(
                checked = checkedState,
                onCheckedChange = null,
                modifier = Modifier.padding(start = DP_16.dp)
            )
        } else {
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