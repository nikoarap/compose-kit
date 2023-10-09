package com.nikoarap.compose_kit.composables

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.CHECKED_CHIP_ALPHA
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.FONT_STYLE_NORMAL
import com.nikoarap.compose_kit.utils.Constants.Companion.FW_MEDIUM
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import com.nikoarap.compose_kit.utils.LayoutUtils


/**
 * Composes a checkable chip with a text and an icon.
 * You can pass a function that is called when the chip is checked and take actions with it. Also visual behaviour changes when the chip is checked.
 * You can style the chip, text and icon accordingly.
 *
 * @param modifier                    Modifier to apply attributes to
 * @param chipTextValue               string representing the text value
 * @param textSizeSp                  text size in sp
 * @param textSidePaddingsDp          side paddings of the text in dp
 * @param textColor                   color of the text
 * @param cornerRadiusDp              radius of the chip's corners in dp
 * @param borderColor                 color of the chip's border
 * @param iconSizeDp                  icon size in dp
 * @param iconStartPaddingDp          icon start padding in dp
 * @param iconResName                 unique string pointing to a material icon resource
 * @param isChecked                   keeps the state of the chip (checked/unchecked)
 * @param checkedColor                chip color in checked state
 * @param uncheckedColor              chip color in unchecked state
 * @param context                     Context
 * @param onChecked                   triggered every time you interact with the chip. You can pass a function that takes an action based on the state of the chip.
 *
 */
@Composable
fun CheckableChip(
    modifier: Modifier,
    chipTextValue: String,
    textSizeSp: Int,
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
    context: Context,
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
                LayoutUtils.getDrawableResourceId(context, iconResName)
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
            SimpleText(
                modifier =  Modifier.padding(start = textSidePaddingsDp.dp, end = textSidePaddingsDp.dp),
                textValue = chipTextValue,
                textSizeSp = textSizeSp,
                fontWeight = FW_MEDIUM,
                fontStyle = FONT_STYLE_NORMAL,
                fontFamily = FontFamily.SansSerif,
                maxLines = ONE,
                textColor = textColor,
                softWrap = true
            )
        }
    }
}

/**
 * Composes a Row containing a checkable chip with a text and an icon.
 * You can pass a function that is called when the chip is checked and take actions with it. Also visual behaviour changes when the chip is checked.
 * You can style the chip, text and icon accordingly.
 *
 * @param modifier                    Modifier to apply attributes to the Row
 * @param chipModifier                Modifier to apply attributes to the chip
 * @param chipTextValue               string representing the text value
 * @param textSizeSp                  text size in sp
 * @param textSidePaddingsDp          side paddings of the text in dp
 * @param textColor                   color of the text
 * @param cornerRadiusDp              radius of the chip's corners in dp
 * @param borderColor                 color of the chip's border
 * @param iconSizeDp                  icon size in dp
 * @param iconStartPaddingDp          icon start padding in dp
 * @param iconResName                 unique string pointing to a material icon resource
 * @param isChecked                   keeps the state of the chip (checked/unchecked)
 * @param checkedColor                chip color in checked state
 * @param uncheckedColor              chip color in unchecked state
 * @param context                     Context
 * @param onChecked                   triggered every time you interact with the chip. You can pass a function that takes an action based on the state of the chip.
 *
 */
@Composable
fun CheckableChipRow(
    modifier: Modifier,
    chipModifier: Modifier,
    chipTextValue: String,
    textSizeSp: Int,
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
    context: Context,
    onChecked: (isChecked: Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val surfaceColor by animateColorAsState(targetValue = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor), label = EMPTY)
    val surfaceColorWithAlpha = surfaceColor.copy(alpha = CHECKED_CHIP_ALPHA)
    val surfaceBorderWidth by animateIntAsState(targetValue = if (checkedState) ZERO else ONE, label = EMPTY)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
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
                    LayoutUtils.getDrawableResourceId(context, iconResName)
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
                SimpleText(
                    modifier =  Modifier.padding(start = textSidePaddingsDp.dp, end = textSidePaddingsDp.dp),
                    textValue = chipTextValue,
                    textSizeSp = textSizeSp,
                    fontWeight = FW_MEDIUM,
                    fontStyle = FONT_STYLE_NORMAL,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = ONE,
                    textColor = textColor,
                    softWrap = true
                )
            }
        }
    }
}

/**
 * Composes a Column containing a checkable chip with a text and an icon.
 * You can pass a function that is called when the chip is checked and take actions with it. Also visual behaviour changes when the chip is checked.
 * You can style the chip, text and icon accordingly.
 *
 * @param modifier                    Modifier to apply attributes to the Column
 * @param chipModifier                Modifier to apply attributes to the chip
 * @param chipTextValue               string representing the text value
 * @param textSizeSp                  text size in sp
 * @param textSidePaddingsDp          side paddings of the text in dp
 * @param textColor                   color of the text
 * @param cornerRadiusDp              radius of the chip's corners in dp
 * @param borderColor                 color of the chip's border
 * @param iconSizeDp                  icon size in dp
 * @param iconStartPaddingDp          icon start padding in dp
 * @param iconResName                 unique string pointing to a material icon resource
 * @param isChecked                   keeps the state of the chip (checked/unchecked)
 * @param checkedColor                chip color in checked state
 * @param uncheckedColor              chip color in unchecked state
 * @param context                     Context
 * @param onChecked                   triggered every time you interact with the chip. You can pass a function that takes an action based on the state of the chip.
 *
 */
@Composable
fun CheckableChipColumn(
    modifier: Modifier,
    chipModifier: Modifier,
    chipTextValue: String,
    textSizeSp: Int,
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
    context: Context,
    onChecked: (isChecked: Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(isChecked) }
    val surfaceColor by animateColorAsState(targetValue = LayoutUtils.getCheckedColor(isChecked, checkedColor, uncheckedColor), label = EMPTY)
    val surfaceColorWithAlpha = surfaceColor.copy(alpha = CHECKED_CHIP_ALPHA)
    val surfaceBorderWidth by animateIntAsState(targetValue = if (checkedState) ZERO else ONE, label = EMPTY)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
                    LayoutUtils.getDrawableResourceId(context, iconResName)
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
                SimpleText(
                    modifier =  Modifier.padding(start = textSidePaddingsDp.dp, end = textSidePaddingsDp.dp),
                    textValue = chipTextValue,
                    textSizeSp = textSizeSp,
                    fontWeight = FW_MEDIUM,
                    fontStyle = FONT_STYLE_NORMAL,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = ONE,
                    textColor = textColor,
                    softWrap = true
                )
            }
        }
    }
}

/**
 * A Composable function that displays a switch button with customizable colors.
 *
 * This Composable renders a switch button with the option to specify custom colors for both
 * the checked and unchecked states.
 *
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @param isChecked             A [MutableState] representing the checked state of the switch. The value is updated based on user interaction.
 *
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
 * A Composable function that displays a Row containing a switch button with customizable colors.
 *
 * This Composable renders a switch button with the option to specify custom colors for both
 * the checked and unchecked states.
 *
 * @param modifier              Modifier to apply attributes to the Row
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @param isChecked             A [MutableState] representing the checked state of the switch. The value is updated based on user interaction.
 *
 */
@Composable
fun SwitchButtonRow(
    modifier: Modifier,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
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
 * A Composable function that displays a Column containing a switch button with customizable colors.
 *
 * This Composable renders a switch button with the option to specify custom colors for both
 * the checked and unchecked states.
 *
 * @param modifier              Modifier to apply attributes to the Column
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @param isChecked             A [MutableState] representing the checked state of the switch. The value is updated based on user interaction.
 *
 */
@Composable
fun SwitchButtonColumn(
    modifier: Modifier,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
 * A Composable function that displays switch button with customizable colors, that can also be read-only.
 *
 * This Composable renders a switch button with the option to specify custom colors for both
 * the checked and unchecked states. The switch button is read-only when [isReadOnly] is set to `true`.
 * When in read-only mode, the user cannot interact with the switch to change its state.
 *
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @param isChecked             A [MutableState] representing the checked state of the switch. The value is updated based on user interaction unless [isReadOnly] is `true`.
 * @param isReadOnly            A flag indicating whether the switch button is in read-only mode. When `true`, the switch cannot be interacted with.
 *
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
 * A Composable function that displays a Row containing a switch button with customizable colors, that can also be read-only.
 *
 * This Composable renders a switch button with the option to specify custom colors for both
 * the checked and unchecked states. The switch button is read-only when [isReadOnly] is set to `true`.
 * When in read-only mode, the user cannot interact with the switch to change its state.
 *
 * @param modifier              Modifier to apply attributes to the Row
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @param isChecked             A [MutableState] representing the checked state of the switch. The value is updated based on user interaction unless [isReadOnly] is `true`.
 * @param isReadOnly            A flag indicating whether the switch button is in read-only mode. When `true`, the switch cannot be interacted with.
 *
 */
@Composable
fun SwitchButtonReadOnlyRow(
    modifier: Modifier,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>,
    isReadOnly: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
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
 * A Composable function that displays a Column containing a switch button with customizable colors, that can also be read-only.
 *
 * This Composable renders a switch button with the option to specify custom colors for both
 * the checked and unchecked states. The switch button is read-only when [isReadOnly] is set to `true`.
 * When in read-only mode, the user cannot interact with the switch to change its state.
 *
 * @param modifier              Modifier to apply attributes to the Column
 * @param checkedColor          The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor        The color to use for the thumb and track when the switch is in the unchecked state.
 * @param isChecked             A [MutableState] representing the checked state of the switch. The value is updated based on user interaction unless [isReadOnly] is `true`.
 * @param isReadOnly            A flag indicating whether the switch button is in read-only mode. When `true`, the switch cannot be interacted with.
 *
 */
@Composable
fun SwitchButtonReadOnlyColumn(
    modifier: Modifier,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: MutableState<Boolean>,
    isReadOnly: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
 * Creates a [SwitchColors] configuration for a Composable Switch with custom colors.
 *
 * @param checkedColor The color to use for the thumb and track when the switch is in the checked state.
 * @param uncheckedColor The color to use for the thumb and track when the switch is in the unchecked state.
 * @return A [SwitchColors] configuration with custom colors for the switch.
 *

 */
@Composable
fun getSwitchColors(
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