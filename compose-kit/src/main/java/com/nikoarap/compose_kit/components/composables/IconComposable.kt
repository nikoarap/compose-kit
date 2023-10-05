package com.nikoarap.compose_kit.components.composables


import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.FALLBACK_ICON_NOT_FOUND
import com.nikoarap.compose_kit.utils.Constants.Companion.RES_TYPE_DRAWABLE
import com.nikoarap.compose_kit.utils.Constants.Companion.TYPE_IMAGE

/**
 * Composable that creates an icon node of type "icon".
 *
 * @param listElement     configurable list object
 * @param context         context
 *
 */
@Composable
fun IconComposable(
    modifier: Modifier,
    imageResIdName: String?,
    imageColorString: String,
    iconSizeDp: Int,
    sidePadding: Int,
    context: Context
) {
    Spacer(Modifier.width(sidePadding.dp))
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val drawableRes: Int = try {
            context.resIdByName(imageResIdName, RES_TYPE_DRAWABLE)
        } catch (exception : Resources.NotFoundException) {
            context.resources.getIdentifier(FALLBACK_ICON_NOT_FOUND, RES_TYPE_DRAWABLE, context.packageName)
        }

        Icon(
            modifier = modifier.size(iconSizeDp.dp),
            painter = painterResource(drawableRes),
            contentDescription = TYPE_IMAGE,
            tint = ListLayoutUtils.getColorByHexString(imageColorString),
        )
    }
    Spacer(Modifier.width(sidePadding.dp))
}