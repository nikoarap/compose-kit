package com.nikoarap.compose_kit.composables


import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.TYPE_IMAGE
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * Wrapper for the Icon Composable.
 * Composes an icon based on the resource name given. Icon can be styled with a size, tint and side paddings.
 *
 * @param modifier        Modifier to apply attributes to
 * @param drawableResName unique string pointing to a material icon resource
 * @param tintColorHex    hex string identifier to be used for the tint
 * @param iconSizeDp      integer determining the icon size in dp
 * @param startPadding    integer determining the icon's start padding in dp
 * @param endPadding      integer determining the icon's end padding in dp
 * @param context         context
 *
 */
@Composable
fun IconFromResource(
    modifier: Modifier,
    drawableResName: String?,
    tintColorHex: String,
    iconSizeDp: Int,
    startPadding: Int,
    endPadding: Int,
    context: Context
) {
    Spacer(Modifier.width(startPadding.dp))
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LayoutUtils.getDrawableResourceId(context, drawableResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                modifier = modifier.size(iconSizeDp.dp),
                painter = it,
                contentDescription = TYPE_IMAGE,
                tint = Color(android.graphics.Color.parseColor(tintColorHex)),
                )
            }
    }
    Spacer(Modifier.width(endPadding.dp))
}