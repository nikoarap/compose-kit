package com.nikoarap.compose_kit.composables


import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import com.nikoarap.compose_kit.utils.Constants.Companion.FALLBACK_IMG_PLACEHOLDER
import com.nikoarap.compose_kit.utils.Constants.Companion.TYPE_IMAGE
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * Wrapper for the Icon Composable.
 * Composes an icon based on the resource name given. Icon can be styled with a size, tint and side paddings.
 *
 * @param modifier        Modifier to apply attributes to
 * @param drawableResName unique string pointing to a material icon resource
 * @param tintColorHex    tint color hex string identifier
 * @param iconSizeDp      icon size in dp
 * @param startPadding    icon start padding in dp
 * @param endPadding      icon end padding in dp
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

/**
 * Wrapper for the Image Composable.
 * Composes an image based on the bitmap given. Image can be styled with a size and side paddings.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param startPadding    image start padding in dp
 * @param endPadding      mage end padding in dp
 * @param context         context
 *
 */
@Composable
fun ImageFromBitmap(
    modifier: Modifier,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    startPadding: Int,
    endPadding: Int,
    context: Context
) {
    Spacer(Modifier.width(startPadding.dp))
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = TYPE_IMAGE,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imgSizeDp.dp)
                    .clip(CircleShape)
            )
        } else {
            LayoutUtils.getDrawableResourceId(context, FALLBACK_IMG_PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = TYPE_IMAGE,
                        painter = it,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(imgSizeDp.dp)
                            .clip(CircleShape)
                    )
                }
        }
    }
    Spacer(Modifier.width(endPadding.dp))
}