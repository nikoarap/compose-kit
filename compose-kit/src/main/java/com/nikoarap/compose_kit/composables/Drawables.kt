package com.nikoarap.compose_kit.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * Wrapper for the Icon Composable.
 * Composes an icon based on the resource name given. Icon can be styled with a size and a color tint.
 *
 * @param drawableResName       unique string pointing to a material icon resource
 * @param tintColor             tint color
 * @param iconSizeDp            icon size in dp*
 */
@Composable
fun IconFromResource(
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int
) {
    LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
        ?.let { painterResource(it) }?.let {
            Icon(
                modifier = Modifier.size(iconSizeDp.dp),
                painter = it,
                contentDescription = IMAGE,
                tint = tintColor
            )
    }
}

/**
 * Wrapper for the Icon Composable.
 * Composes a Row containing an icon based on the resource name given. Icon can be styled with a size and a color tint.
 *
 * @param modifier            Modifier to apply attributes to the Row
 * @param drawableResName     unique string pointing to a material icon resource
 * @param tintColor           tint color
 * @param iconSizeDp          icon size in dp
 */
@Composable
fun IconFromResourceRow(
    modifier: Modifier,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(iconSizeDp.dp),
                    painter = it,
                    contentDescription = IMAGE,
                    tint = tintColor
                )
        }
    }
}

/**
 * Wrapper for the Icon Composable.
 * Composes a Column containing an icon based on the resource name given. Icon can be styled with a size and a color tint.
 *
 * @param modifier            Modifier to apply attributes to the Column
 * @param drawableResName     unique string pointing to a material icon resource
 * @param tintColor           tint color
 * @param iconSizeDp          icon size in dp
 */
@Composable
fun IconFromResourceColumn(
    modifier: Modifier,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(iconSizeDp.dp),
                    painter = it,
                    contentDescription = IMAGE,
                    tint = tintColor,
                )
        }
    }
}

/**
 * Wrapper for the Image Composable.
 * Composes an image based on the bitmap given. Image can be styled with a size and contentScale.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 */
@Composable
fun ImageFromBitmap(
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale
) {
    when {
        bitmap != null -> Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = IMAGE,
            contentScale = contentScale,
            modifier = Modifier
                .size(imgSizeDp.dp)
        )
        else -> LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
            ?.let { painterResource(it) }?.let {
                Image(
                    contentDescription = IMAGE,
                    painter = it,
                    contentScale = contentScale,
                    modifier = Modifier.size(imgSizeDp.dp)
                )
            }
    }
}

/**
 * Wrapper for the Image Composable.
 * Composes a Row containing an image based on the bitmap given. Image can be styled with a size and contentScale.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to the ROw
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 */
@Composable
fun ImageFromBitmapRow(
    modifier: Modifier,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when {
            bitmap != null -> Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = IMAGE,
                contentScale = contentScale,
                modifier = Modifier
                    .size(imgSizeDp.dp)
            )
            else -> LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = IMAGE,
                        painter = it,
                        contentScale = contentScale,
                        modifier = Modifier.size(imgSizeDp.dp)
                    )
                }
        }
    }
}

/**
 * Wrapper for the Image Composable.
 * Composes a Column containing an image based on the bitmap given. Image can be styled with a size and contentScale.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to the Column
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 */
@Composable
fun ImageFromBitmapColumn(
    modifier: Modifier,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            bitmap != null -> Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = IMAGE,
                contentScale = contentScale,
                modifier = Modifier.size(imgSizeDp.dp)
            )
            else -> LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = IMAGE,
                        painter = it,
                        contentScale = contentScale,
                        modifier = Modifier.size(imgSizeDp.dp)
                    )
                }
        }
    }
}

/**
 * Wrapper for the Image Composable.
 * Composes an image that can be clipped based on the bitmap given. Image can be styled with a size, contentScale, and a clip shape to clip the image.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 * @param clipShape       shape to set for the clipping of the image (e.g. to clip your image onto a circle use circleShape)
 */
@Composable
fun ImageFromBitmapClipped(
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale,
    clipShape: Shape
) {
    when {
        bitmap != null -> Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = IMAGE,
            contentScale = contentScale,
            modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
        )
        else -> LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
            ?.let { painterResource(it) }?.let {
                Image(
                    contentDescription = IMAGE,
                    painter = it,
                    contentScale = contentScale,
                    modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
                )
            }
    }
}

/**
 * Wrapper for the Image Composable.
 * Composes a Row containing an image that can be clipped based on the bitmap given. Image can be styled with a size, contentScale, and a clip shape to clip the image.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to the Row
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 * @param clipShape       shape to set for the clipping of the image (e.g. to clip your image onto a circle use circleShape)
 */
@Composable
fun ImageFromBitmapClippedRow(
    modifier: Modifier,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale,
    clipShape: Shape
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {
            bitmap != null -> Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = IMAGE,
                contentScale = contentScale,
                modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
            )
            else -> LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = IMAGE,
                        painter = it,
                        contentScale = contentScale,
                        modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
                    )
                }
        }
    }
}

/**
 * Wrapper for the Image Composable.
 * Composes a Column containing an image that can be clipped based on the bitmap given. Image can be styled with a size, contentScale, and a clip shape to clip the image.
 * If the bitmap is invalid, shows a placeholder image instead.
 *
 * @param modifier        Modifier to apply attributes to the Column
 * @param bitmap          Bitmap to load the image
 * @param imgSizeDp       image size in dp
 * @param contentScale    content scale rule to apply in order to scale the image(one of: Crop, Fit, FillHeight, FillWidth, Inside, FillBounds, None)
 * @param clipShape       shape to set for the clipping of the image (e.g. to clip your image onto a circle use circleShape)
 */
@Composable
fun ImageFromBitmapClippedColumn(
    modifier: Modifier,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale,
    clipShape: Shape
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            bitmap != null -> Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = IMAGE,
                contentScale = contentScale,
                modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
            )
            else -> LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                ?.let { painterResource(it) }?.let {
                    Image(
                        contentDescription = IMAGE,
                        painter = it,
                        contentScale = contentScale,
                        modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
                    )
                }
        }
    }
}

/**
 * Wrapper for the IconButton Composable.
 * Composes a clickable icon based on the resource name given. Icon can be styled with a size and a color tint.
 *
 * @param drawableResName       unique string pointing to a material icon resource
 * @param tintColor             tint color
 * @param iconSizeDp            icon size in dp
 * @param onClick               onClick listener that detects clicks on the IconButton
 */
@Composable
fun ClickableIconFromResource(
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(iconSizeDp.dp),
                    painter = it,
                    contentDescription = IMAGE,
                    tint = tintColor
                )
        }
    }
}

/**
 * Wrapper for the IconButton Composable.
 * Composes a Row containing a clickable icon based on the resource name given. Icon can be styled with a size and a color tint.
 *
 * @param modifier        Modifier to apply attributes to the Row
 * @param drawableResName unique string pointing to a material icon resource
 * @param tintColor       tint color
 * @param iconSizeDp      icon size in dp
 * @param onClick         onClick listener that detects clicks on the IconButton
 */
@Composable
fun ClickableIconFromResourceRow(
    modifier: Modifier,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        modifier = Modifier.size(iconSizeDp.dp),
                        painter = it,
                        contentDescription = IMAGE,
                        tint = tintColor
                    )
                }
        }
    }
}

/**
 * Wrapper for the IconButton Composable.
 * Composes a Column containing a clickable icon based on the resource name given. Icon can be styled with a size and a color tint.
 *
 * @param modifier              Modifier to apply attributes to the Column
 * @param drawableResName       unique string pointing to a material icon resource
 * @param tintColor             tint color
 * @param iconSizeDp            icon size in dp
 * @param onClick               onClick listener that detects clicks on the IconButton
 */
@Composable
fun ClickableIconFromResourceColumn(
    modifier: Modifier,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        modifier = Modifier.size(iconSizeDp.dp),
                        painter = it,
                        contentDescription = IMAGE,
                        tint = tintColor
                    )
                }
        }
    }
}