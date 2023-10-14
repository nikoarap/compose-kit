package com.nikoarap.compose_kit.composables

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.EIGHTY_PERCENT
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.IC_CARET_DOWN
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE_EIGHTY_FLOAT
import com.nikoarap.compose_kit.utils.Constants.Companion.THREE
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO_FLOAT
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * Composable function to display an expandable section with a title, subtitle, and optional content.
 *
 * @param title                     The title text of the expandable section.
 * @param subtitle                  The subtitle text of the expandable section.
 * @param titleTypography           The style of the title in material design scale
 * @param subtitleTypography        The style of the subtitle in material design scale
 * @param titleColor                The color of the title text.
 * @param subtitleColor             The color of the subtitle text.
 * @param textStartPaddingsDp       The start paddings for the title and subtitle texts.
 * @param iconSizeDp                The size for the expand/collapse icon.
 * @param iconSidePaddingsDp        The paddings for the expand/collapse icon.
 * @param iconTintColor             The color of the expand/collapse icon.
 * @param dividerColor              The color of the divider line.
 * @param isExpanded                A [MutableState] representing whether the section is expanded or collapsed.
 * @param expandableContent         The content to be displayed when the section is expanded.
 *
 * This Composable function creates an expandable section with a title, subtitle, and an optional content area.
 * The section can be expanded or collapsed by tapping on it.
 *
 * Example usage:
 * ```kotlin
 * var isExpanded by remember { mutableStateOf(false) }
 * ExpandableSection(
 *     title = "Section Title",
 *     subtitle = "Section Subtitle",
 *     titleTypography = MaterialTheme.typography.bodyLarge,
 *     subtitleTypography = MaterialTheme.typography.bodyLarge,
 *     titleColor = Color.Black,
 *     subtitleColor = Color.Gray,
 *     textStartPaddingsDp = 16,
 *     iconSizeDp = 24,
 *     iconSidePaddingsDp = 16,
 *     iconTintColor = Color.Gray,
 *     dividerColor = Color.Gray,
 *     isExpanded = isExpanded,
 *     expandableContent = {
 *         // Content to display when the section is expanded
 *         Text("This is the expandable content.")
 *     }
 * )
 * ```
 */
@Composable
fun ExpandableSection(
    title: String,
    subtitle: String,
    titleTypography: TextStyle,
    subtitleTypography: TextStyle,
    titleColor: Color,
    subtitleColor: Color,
    textStartPaddingsDp: Int,
    iconSizeDp: Int,
    iconSidePaddingsDp: Int,
    iconTintColor: Color,
    dividerColor: Color,
    isExpanded: MutableState<Boolean>,
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
                CustomizedText(
                    modifier = Modifier.padding(start = textStartPaddingsDp.dp),
                    textValue = title,
                    typography = titleTypography,
                    maxLines = ONE,
                    textColor = titleColor,
                    softWrap = true
                )

                CustomizedText(
                    modifier = Modifier.padding(start = textStartPaddingsDp.dp),
                    textValue = subtitle,
                    typography = subtitleTypography,
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
                    LayoutUtils.getDrawableResourceId(LocalContext.current, IC_CARET_DOWN)
                        ?.let { painterResource(it) }?.let {
                            Icon(
                                modifier = Modifier
                                    .size(iconSizeDp.dp)
                                    .rotate(rotationState),
                                painter = it,
                                contentDescription = ICON,
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