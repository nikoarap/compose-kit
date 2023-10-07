package com.nikoarap.compose_kit.composables

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
import com.nikoarap.compose_kit.utils.Constants.Companion.FONT_WEIGHT_MEDIUM
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
    onChecked: () -> Unit
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
                    onChecked
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
                fontWeight = FONT_WEIGHT_MEDIUM,
                fontStyle = FONT_STYLE_NORMAL,
                fontFamily = FontFamily.SansSerif,
                maxLines = ONE,
                textColor = textColor,
                softWrap = true
            )
        }
    }
}