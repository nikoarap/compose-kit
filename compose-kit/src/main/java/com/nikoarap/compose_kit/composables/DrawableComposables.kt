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
import androidx.compose.ui.graphics.Shape
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
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
                contentDescription = IMAGE,
                tint = Color(android.graphics.Color.parseColor(tintColorHex)),
                )
            }
    }
    Spacer(Modifier.width(endPadding.dp))
}

/**
 * Wrapper for the Image Composable.
 * Composes an image based on the bitmap given. Image can be styled with a size, contentScale and side paddings.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param startPadding    image start padding in dp
 * @param endPadding      mage end padding in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
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
    contentScale: ContentScale,
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
                contentDescription = IMAGE,
                contentScale = contentScale,
                modifier = Modifier
                    .size(imgSizeDp.dp)
            )
        } else {
            LayoutUtils.getDrawableResourceId(context, PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = IMAGE,
                        painter = it,
                        contentScale = contentScale,
                        modifier = Modifier
                            .size(imgSizeDp.dp)
                    )
                }
        }
    }
    Spacer(Modifier.width(endPadding.dp))
}

/**
 * Wrapper for the Image Composable.
 * Composes an image that can be clipped based on the bitmap given. Image can be styled with a size, contentScale and side paddings. You can also provide a clip shape to the image.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param startPadding    image start padding in dp
 * @param endPadding      mage end padding in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 * @param clipShape       shape to set for the clipping of the image
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
    contentScale: ContentScale,
    clipShape: Shape,
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
                contentDescription = IMAGE,
                contentScale = contentScale,
                modifier = Modifier
                    .size(imgSizeDp.dp)
                    .clip(clipShape)
            )
        } else {
            LayoutUtils.getDrawableResourceId(context, PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = IMAGE,
                        painter = it,
                        contentScale = contentScale,
                        modifier = Modifier
                            .size(imgSizeDp.dp)
                            .clip(clipShape)
                    )
                }
        }
    }
    Spacer(Modifier.width(endPadding.dp))
}