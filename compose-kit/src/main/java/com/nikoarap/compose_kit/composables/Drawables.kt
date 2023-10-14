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
import coil.compose.rememberAsyncImagePainter
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * Composable function to display an icon from a drawable resource based on the provided resource name.
 *
 * @param drawableResName      The name of the drawable resource to be displayed as an icon.
 * @param tintColor            The color to tint the icon with.
 * @param iconSizeDp           The size of the icon in density-independent pixels (dp).
 *
 * This Composable function takes a drawable resource name and attempts to retrieve the associated
 * resource ID using the current Android context. If the resource is found, it is displayed as an
 * Icon with the specified [tintColor] and [iconSizeDp].
 *
 * Example usage:
 * ```
 * IconFromResource(
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24
 * )
 * ```
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
                contentDescription = ICON,
                tint = tintColor
            )
    }
}

/**
 * Composable function to display an icon from a drawable resource within a Row layout.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param drawableResName        The name of the drawable resource to be displayed as an icon.
 * @param tintColor              The color to tint the icon with.
 * @param iconSizeDp             The size of the icon in density-independent pixels (dp).
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row,
 * it attempts to retrieve the drawable resource associated with [drawableResName] using the current
 * Android context. If the resource is found, it is displayed as an Icon with the specified [tintColor]
 * and [iconSizeDp], horizontally centered and vertically aligned within the Row.
 *
 * Example usage:
 * ```
 * IconFromResourceRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24
 * )
 * ```
 */
@Composable
fun IconFromResourceRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(iconSizeDp.dp),
                    painter = it,
                    contentDescription = ICON,
                    tint = tintColor
                )
        }
    }
}

/**
 * Composable function to display an icon from a drawable resource within a Column layout, with customizable vertical and horizontal alignment.
 *
 * @param modifier               The modifier for the Column layout.
 * @param verticalArrangement    The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment    The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param drawableResName        The name of the drawable resource to be displayed as an icon.
 * @param tintColor              The color to tint the icon with.
 * @param iconSizeDp             The size of the icon in density-independent pixels (dp).
 *
 * This Composable function creates a Column layout with the provided [modifier] and allows you to customize the vertical and horizontal alignment using the [verticalArrangement] and [horizontalAlignment] parameters. Within the Column, it attempts to retrieve the drawable resource associated with [drawableResName] using the current Android context. If the resource is found, it is displayed as an Icon with the specified [tintColor] and [iconSizeDp], using the specified alignment and arrangement within the Column.
 *
 * Example usage:
 * ```
 * IconFromResourceColumn(
 *     modifier = Modifier.fillMaxSize(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24
 * )
 * ```
 */
@Composable
fun IconFromResourceColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(iconSizeDp.dp),
                    painter = it,
                    contentDescription = ICON,
                    tint = tintColor,
                )
            }
    }
}

/**
 * Composable function to display an image either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param bitmap                The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
 *
 * This Composable function displays an image within an Image composable. If a non-null [bitmap] is provided,
 * that Bitmap is displayed with the specified [contentScale] and [imgSizeDp]. If [bitmap] is null, it attempts to
 * retrieve a drawable resource using the current Android context and displays the placeholder image. The placeholder
 * image is displayed with the specified [contentScale] and [imgSizeDp].
 *
 * Example usage:
 * ```
 * ImageFromBitmap(
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop
 * )
 * ```
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
 * Composable function to display an image within a Row layout, either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param bitmap                 The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp              The size of the image in density-independent pixels (dp).
 * @param contentScale           The content scale for the image.
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row,
 * it evaluates the [bitmap] parameter. If a non-null [bitmap] is provided, that Bitmap is displayed
 * with the specified [contentScale] and [imgSizeDp], horizontally centered and vertically aligned within the Row.
 * If [bitmap] is null, it attempts to retrieve a drawable resource using the current Android context and displays the placeholder image.
 * The placeholder image is displayed with the specified [contentScale] and [imgSizeDp], horizontally centered and vertically aligned within the Row.
 *
 * Example usage:
 * ```
 * ImageFromBitmapRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop
 * )
 * ```
 */
@Composable
fun ImageFromBitmapRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
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
 * Composable function to display an image within a Column layout, either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param modifier                  The modifier for the Column layout.
 * @param verticalArrangement       The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment       The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param bitmap                    The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp                 The size of the image in density-independent pixels (dp).
 * @param contentScale              The content scale for the image.
 *
 * This Composable function creates a Column layout with the provided [modifier] and allows you to customize the vertical and horizontal alignment using the [verticalArrangement] and [horizontalAlignment] parameters. Within the Column, it evaluates the [bitmap] parameter. If a non-null [bitmap] is provided, that Bitmap is displayed with the specified [contentScale] and [imgSizeDp], using the specified alignment and arrangement within the Column. If [bitmap] is null, it attempts to retrieve a drawable resource using the current Android context and displays the placeholder image. The placeholder image is displayed with the specified [contentScale] and [imgSizeDp], using the specified alignment and arrangement within the Column.
 *
 * Example usage:
 * ```
 * ImageFromBitmapColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop
 * )
 * ```
 */
@Composable
fun ImageFromBitmapColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
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
 * Composable function to load and display an image from a URL in Jetpack Compose.
 *
 * @param imageUrl           The URL of the image to be loaded and displayed.
 * @param imgSizeDp          The size of the displayed image in density-independent pixels (dp).
 * @param contentScale       The scale type for the image content, such as [ContentScale.Crop], [ContentScale.FillBounds], etc.
 * @param placeHolderResName The name of the drawable resource to be used as a placeholder image while the URL image is loading. It can be null if no placeholder is required.
 *
 * This Composable function loads and displays an image from the provided [imageUrl] within a Jetpack Compose layout. It supports specifying the [imgSizeDp] for the image's size in dp and the [contentScale] for determining how the image content is scaled. An optional [placeHolderResName] can be provided to use a placeholder image during loading.
 *
 * Example usage:
 * ```
 * ImageFromUrl(
 *     imageUrl = "https://example.com/image.jpg",
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     placeHolderResName = "placeholder_image"
 * )
 * ```
 */
@Composable
fun ImageFromUrl(
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = placeholder,
            error = placeholder,
        ),
        contentScale = contentScale,
        contentDescription = IMAGE,
        modifier = Modifier.size(imgSizeDp.dp)
    )
}

/**
 * Composable function to load and display an image from a URL within a Row layout in Jetpack Compose.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param imageUrl               The URL of the image to be loaded and displayed.
 * @param imgSizeDp              The size of the displayed image in density-independent pixels (dp).
 * @param contentScale           The scale type for the image content, such as [ContentScale.Crop], [ContentScale.FillBounds], etc.
 * @param placeHolderResName     The name of the drawable resource to be used as a placeholder image while the URL image is loading. It can be null if no placeholder is required.
 *
 * This Composable function loads and displays an image from the provided [imageUrl] within a Row layout in Jetpack Compose. It supports specifying the [imgSizeDp] for the image's size in dp, the [contentScale] for determining how the image content is scaled, and an optional [placeHolderResName] to use a placeholder image during loading.
 *
 * Example usage:
 * ```
 * ImageFromUrlRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     imageUrl = "https://example.com/image.jpg",
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     placeHolderResName = "placeholder_image"
 * )
 * ```
 */
@Composable
fun ImageFromUrlRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                placeholder = placeholder,
                error = placeholder,
            ),
            contentScale = contentScale,
            contentDescription = IMAGE,
            modifier = Modifier.size(imgSizeDp.dp)
        )
    }
}

/**
 * Composable function to load and display an image from a URL within a Column layout in Jetpack Compose, with customizable vertical and horizontal alignment.
 *
 * @param modifier              The modifier for the Column layout.
 * @param verticalArrangement   The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment   The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param imageUrl              The URL of the image to be loaded and displayed.
 * @param imgSizeDp             The size of the displayed image in density-independent pixels (dp).
 * @param contentScale          The scale type for the image content, such as [ContentScale.Crop], [ContentScale.FillBounds], etc.
 * @param placeHolderResName    The name of the drawable resource to be used as a placeholder image while the URL image is loading. It can be null if no placeholder is required.
 *
 * This Composable function allows you to load and display an image from the provided [imageUrl] within a Column layout in Jetpack Compose. You can customize the vertical and horizontal alignment using the [verticalArrangement] and [horizontalAlignment] parameters. Additionally, you can specify the [imgSizeDp] for the image's size in dp, the [contentScale] for determining how the image content is scaled, and an optional [placeHolderResName] to use a placeholder image during loading.
 *
 * Example usage:
 * ```
 * ImageFromUrlColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     imageUrl = "https://example.com/image.jpg",
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     placeHolderResName = "placeholder_image"
 * )
 * ```
 */
@Composable
fun ImageFromUrlColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                placeholder = placeholder,
                error = placeholder,
            ),
            contentScale = contentScale,
            contentDescription = IMAGE,
            modifier = Modifier.size(imgSizeDp.dp)
        )
    }
}

/**
 * Composable function to display a clipped image either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param bitmap                The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
 * @param clipShape             The shape used to clip the image.
 *
 * This Composable function displays an image within an Image composable. If a non-null [bitmap] is provided,
 * that Bitmap is displayed with the specified [contentScale] and [imgSizeDp], and it is clipped using the provided [clipShape].
 * If [bitmap] is null, it attempts to retrieve a drawable resource using the current Android context and displays the placeholder image.
 * The placeholder image is displayed with the specified [contentScale], [imgSizeDp], and is also clipped using the provided [clipShape].
 *
 * Example usage:
 * ```
 * ImageFromBitmapClipped(
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     clipShape = RoundedCornerShape(16.dp)
 * )
 * ```
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
 * Composable function to display a clipped image within a Row layout, either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param bitmap                 The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp              The size of the image in density-independent pixels (dp).
 * @param contentScale           The content scale for the image.
 * @param clipShape              The shape used to clip the image.
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row,
 * it evaluates the [bitmap] parameter. If a non-null [bitmap] is provided, that Bitmap is displayed
 * with the specified [contentScale] and [imgSizeDp], horizontally centered and vertically aligned within the Row.
 * The image is also clipped using the provided [clipShape]. If [bitmap] is null, it attempts to retrieve a drawable
 * resource using the current Android context and displays the placeholder image. The placeholder image is displayed with
 * the specified [contentScale], [imgSizeDp], and is also clipped using the provided [clipShape].
 *
 * Example usage:
 * ```
 * ImageFromBitmapClippedRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     clipShape = RoundedCornerShape(16.dp)
 * )
 * ```
 */
@Composable
fun ImageFromBitmapClippedRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale,
    clipShape: Shape
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
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
 * Composable function to display a clipped image within a Column layout, either from a provided Bitmap or a placeholder drawable resource, with customizable vertical and horizontal alignment.
 *
 * @param modifier               The modifier for the Column layout.
 * @param verticalArrangement    The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment    The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param bitmap                 The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp              The size of the image in density-independent pixels (dp).
 * @param contentScale           The content scale for the image.
 * @param clipShape              The shape used to clip the image.
 *
 * This Composable function creates a Column layout with the provided [modifier] and allows you to customize the vertical and horizontal alignment using the [verticalArrangement] and [horizontalAlignment] parameters. Within the Column, it evaluates the [bitmap] parameter. If a non-null [bitmap] is provided, that Bitmap is displayed with the specified [contentScale] and [imgSizeDp], using the specified alignment and arrangement within the Column. The image is also clipped using the provided [clipShape]. If [bitmap] is null, it attempts to retrieve a drawable resource using the current Android context and displays the placeholder image. The placeholder image is displayed with the specified [contentScale], [imgSizeDp], and is also clipped using the provided [clipShape].
 *
 * Example usage:
 * ```
 * ImageFromBitmapClippedColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     clipShape = RoundedCornerShape(16.dp)
 * )
 * ```
 */
@Composable
fun ImageFromBitmapClippedColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    bitmap: Bitmap?,
    imgSizeDp: Int,
    contentScale: ContentScale,
    clipShape: Shape
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
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
 * Composable function to display a clipped image fetched from a URL within a Composable, with customizable clipping shape.
 *
 * @param imageUrl              The URL of the image to be displayed.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
 * @param placeHolderResName    The name of the placeholder drawable resource to be displayed while the image is loading. If null, no placeholder is used.
 * @param clipShape             The shape used to clip the displayed image.
 *
 * This Composable function displays an image fetched from the specified [imageUrl] within a Composable. The image is displayed with the specified [contentScale] and [imgSizeDp] and is clipped using the provided [clipShape]. While the image is loading, the placeholder specified by [placeHolderResName] is displayed. If no placeholder is provided, the Composable will be empty until the image is loaded.
 *
 * Example usage:
 * ```
 * ImageFromUrlClipped(
 *     imageUrl = "https://example.com/my-image.jpg",
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     placeHolderResName = "placeholder_image",
 *     clipShape = RoundedCornerShape(16.dp)
 * )
 * ```
 */
@Composable
fun ImageFromUrlClipped(
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
    clipShape: Shape
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = placeholder,
            error = placeholder,
        ),
        contentScale = contentScale,
        contentDescription = IMAGE,
        modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
    )
}

/**
 * Composable function to display a clipped image fetched from a URL within a Row layout, with customizable layout and clipping parameters.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param imageUrl               The URL of the image to be displayed.
 * @param imgSizeDp              The size of the image in density-independent pixels (dp).
 * @param contentScale           The content scale for the image.
 * @param placeHolderResName     The name of the placeholder drawable resource to be displayed while the image is loading. If null, no placeholder is used.
 * @param clipShape              The shape used to clip the displayed image.
 *
 * This Composable function displays an image fetched from the specified [imageUrl] within a Row layout. You can customize the horizontal arrangement and vertical alignment using the [horizontalArrangement] and [verticalAlignment] parameters. The image is displayed with the specified [contentScale] and [imgSizeDp] and is clipped using the provided [clipShape]. While the image is loading, the placeholder specified by [placeHolderResName] is displayed. If no placeholder is provided, the Composable will be empty until the image is loaded.
 *
 * Example usage:
 * ```
 * ImageFromUrlClippedRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Start,
 *     verticalAlignment = Alignment.Top,
 *     imageUrl = "https://example.com/my-image.jpg",
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     placeHolderResName = "placeholder_image",
 *     clipShape = RoundedCornerShape(16.dp)
 * )
 * ```
 */
@Composable
fun ImageFromUrlClippedRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
    clipShape: Shape
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                placeholder = placeholder,
                error = placeholder,
            ),
            contentScale = contentScale,
            contentDescription = IMAGE,
            modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
        )
    }
}

/**
 * Composable function to display a clipped image fetched from a URL within a Column layout, with customizable layout and clipping parameters.
 *
 * @param modifier               The modifier for the Column layout.
 * @param verticalArrangement    The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment    The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param imageUrl               The URL of the image to be displayed.
 * @param imgSizeDp              The size of the image in density-independent pixels (dp).
 * @param contentScale           The content scale for the image.
 * @param placeHolderResName     The name of the placeholder drawable resource to be displayed while the image is loading. If null, no placeholder is used.
 * @param clipShape              The shape used to clip the displayed image.
 *
 * This Composable function displays an image fetched from the specified [imageUrl] within a Column layout. You can customize the vertical arrangement and horizontal alignment using the [verticalArrangement] and [horizontalAlignment] parameters. The image is displayed with the specified [contentScale] and [imgSizeDp] and is clipped using the provided [clipShape]. While the image is loading, the placeholder specified by [placeHolderResName] is displayed. If no placeholder is provided, the Composable will be empty until the image is loaded.
 *
 * Example usage:
 * ```
 * ImageFromUrlClippedColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     imageUrl = "https://example.com/my-image.jpg",
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop,
 *     placeHolderResName = "placeholder_image",
 *     clipShape = RoundedCornerShape(16.dp)
 * )
 * ```
 */
@Composable
fun ImageFromUrlClippedColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
    clipShape: Shape
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                placeholder = placeholder,
                error = placeholder,
            ),
            contentScale = contentScale,
            contentDescription = IMAGE,
            modifier = Modifier.size(imgSizeDp.dp).clip(clipShape)
        )
    }
}

/**
 * Composable function to display a clickable icon from a drawable resource.
 *
 * @param drawableResName       The name of the drawable resource to be displayed as an icon.
 * @param tintColor             The color to tint the icon with.
 * @param iconSizeDp            The size of the icon in density-independent pixels (dp).
 * @param onClick               The lambda function to be executed when the icon is clicked.
 *
 * This Composable function creates a clickable icon using the provided [drawableResName], [tintColor], and [iconSizeDp].
 * When the icon is clicked, the specified [onClick] lambda function is invoked.
 *
 * Example usage:
 * ```
 * ClickableIconFromResource(
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24,
 *     onClick = { /* Handle click action here */ }
 * )
 * ```
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
                    contentDescription = ICON,
                    tint = tintColor
                )
        }
    }
}

/**
 * Composable function to display a clickable icon from a drawable resource within a Row layout.
 *
 * @param modifier               The modifier for the Row layout.
 * @param horizontalArrangement  The horizontal arrangement strategy within the Row. Default is [Arrangement.Center].
 * @param verticalAlignment      The vertical alignment strategy within the Row. Default is [Alignment.CenterVertically].
 * @param drawableResName        The name of the drawable resource to be displayed as an icon.
 * @param tintColor              The color to tint the icon with.
 * @param iconSizeDp             The size of the icon in density-independent pixels (dp).
 * @param onClick                The lambda function to be executed when the icon is clicked.
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row, it displays a clickable icon using the specified
 * [drawableResName], [tintColor], and [iconSizeDp]. When the icon is clicked, the specified [onClick] lambda function is invoked.
 *
 * Example usage:
 * ```
 * ClickableIconFromResourceRow(
 *     modifier = Modifier.fillMaxWidth(),
 *     horizontalArrangement = Arrangement.Center,
 *     verticalAlignment = Alignment.CenterVertically,
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24,
 *     onClick = { /* Handle click action here */ }
 * )
 * ```
 */
@Composable
fun ClickableIconFromResourceRow(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        modifier = Modifier.size(iconSizeDp.dp),
                        painter = it,
                        contentDescription = ICON,
                        tint = tintColor
                    )
                }
        }
    }
}

/**
 * Composable function to display a clickable icon from a drawable resource within a Column layout, with customizable vertical and horizontal alignment.
 *
 * @param modifier               The modifier for the Column layout.
 * @param verticalArrangement    The vertical arrangement strategy within the Column. Default is [Arrangement.Center].
 * @param horizontalAlignment    The horizontal alignment strategy within the Column. Default is [Alignment.CenterHorizontally].
 * @param drawableResName        The name of the drawable resource to be displayed as an icon.
 * @param tintColor              The color to tint the icon with.
 * @param iconSizeDp             The size of the icon in density-independent pixels (dp).
 * @param onClick                The lambda function to be executed when the icon is clicked.
 *
 * This Composable function creates a Column layout with the provided [modifier] and allows you to customize the vertical and horizontal alignment using the [verticalArrangement] and [horizontalAlignment] parameters. Within the Column, it displays a clickable icon using the specified [drawableResName], [tintColor], and [iconSizeDp]. When the icon is clicked, the specified [onClick] lambda function is invoked.
 *
 * Example usage:
 * ```
 * ClickableIconFromResourceColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     verticalArrangement = Arrangement.Top,
 *     horizontalAlignment = Alignment.Start,
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24,
 *     onClick = { /* Handle click action here */ }
 * )
 * ```
 */
@Composable
fun ClickableIconFromResourceColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    drawableResName: String?,
    tintColor: Color,
    iconSizeDp: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            LayoutUtils.getDrawableResourceId(LocalContext.current, drawableResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        modifier = Modifier.size(iconSizeDp.dp),
                        painter = it,
                        contentDescription = ICON,
                        tint = tintColor
                    )
                }
        }
    }
}