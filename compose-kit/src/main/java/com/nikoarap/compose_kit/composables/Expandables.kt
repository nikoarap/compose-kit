package com.nikoarap.compose_kit.composables

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.EIGHTY_PERCENT
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.FONT_STYLE_NORMAL
import com.nikoarap.compose_kit.utils.Constants.Companion.FW_MEDIUM
import com.nikoarap.compose_kit.utils.Constants.Companion.FW_NORMAL
import com.nikoarap.compose_kit.utils.Constants.Companion.IC_CARET_DOWN
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE_EIGHTY_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.THREE
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO_FLOAT
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * A Composable that represents an expandable section with a title, subtitle, and optional content.
 * The section can be toggled to expand or collapse to show or hide the content.
 *
 * @param title                     The title displayed at the top of the expandable section.
 * @param subtitle                  The subtitle displayed beneath the title.
 * @param titleTextSizeSp           The text size in SP for the title.
 * @param subtitleTextSizeSp        The text size in SP for the subtitle.
 * @param titleColor                The color of the title text.
 * @param subtitleColor             The color of the subtitle text.
 * @param textStartPaddingsDp       The padding at the start (left) of the title and subtitle text.
 * @param iconSizeDp                The size of the expand/collapse icon.
 * @param iconSidePaddingsDp        The horizontal padding around the expand/collapse icon.
 * @param iconTintColor             The tint color of the expand/collapse icon.
 * @param dividerColor              The color of the divider line.
 * @param isExpanded                A [MutableState] that controls the expanded or collapsed state of the section.
 * @param context                   The Android [Context] used for resource retrieval.
 * @param expandableContent         An optional @Composable lambda representing the content to be displayed when the section is expanded.
 */
@Composable
fun ExpandableSection(
    title: String,
    subtitle: String,
    titleTextSizeSp: Int,
    subtitleTextSizeSp: Int,
    titleColor: Color,
    subtitleColor: Color,
    textStartPaddingsDp: Int,
    iconSizeDp: Int,
    iconSidePaddingsDp: Int,
    iconTintColor: Color,
    dividerColor: Color,
    isExpanded: MutableState<Boolean>,
    context: Context,
    expandableContent: @Composable () -> Unit
) {
    var expandedState by remember { isExpanded }
    val rotationState by animateFloatAsState(targetValue = if (expandedState) ONE_EIGHTY_FLOAT else ZERO_FLOAT, label = EMPTY)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.clickable {
                expandedState = !expandedState
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                Modifier
                    .padding(top = DP_16.dp, bottom = DP_8.dp)
                    .height(intrinsicSize = IntrinsicSize.Max)
            ) {
                SimpleText(
                    modifier = Modifier.padding(start = textStartPaddingsDp.dp),
                    textValue = title,
                    textSizeSp = titleTextSizeSp,
                    fontWeight = FW_MEDIUM,
                    fontStyle = FONT_STYLE_NORMAL,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = ONE,
                    textColor = titleColor,
                    softWrap = true
                )

                SimpleText(
                    modifier = Modifier.padding(start = textStartPaddingsDp.dp),
                    textValue = subtitle,
                    textSizeSp = subtitleTextSizeSp,
                    fontWeight = FW_NORMAL,
                    fontStyle = FONT_STYLE_NORMAL,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = ONE,
                    textColor = subtitleColor,
                    softWrap = true
                )
            }
            Column(
                Modifier
                    .wrapContentHeight()
                    .weight(EIGHTY_PERCENT, true),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(Modifier.width(iconSidePaddingsDp.dp))
                    LayoutUtils.getDrawableResourceId(context, IC_CARET_DOWN)
                        ?.let { painterResource(it) }?.let {
                            Icon(
                                modifier = Modifier.size(iconSizeDp.dp).rotate(rotationState),
                                painter = it,
                                contentDescription = Constants.IMAGE,
                                tint = iconTintColor
                            )
                    }
                    Spacer(Modifier.width(iconSidePaddingsDp.dp))
                }
            }
        }
    }
    when {
        expandedState -> {
            expandableContent()
        }
        else -> {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = dividerColor,
                thickness = THREE.dp
            )
        }
    }
}