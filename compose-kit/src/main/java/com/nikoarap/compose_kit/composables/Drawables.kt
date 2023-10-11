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
                contentDescription = IMAGE,
                tint = tintColor
            )
    }
}

/**
 * Composable function to display an icon from a drawable resource within a Row layout.
 *
 * @param modifier              The modifier for the Row layout.
 * @param drawableResName       The name of the drawable resource to be displayed as an icon.
 * @param tintColor             The color to tint the icon with.
 * @param iconSizeDp            The size of the icon in density-independent pixels (dp).
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
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24
 * )
 * ```
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
 * Composable function to display an icon from a drawable resource within a Column layout.
 *
 * @param modifier              The modifier for the Column layout.
 * @param drawableResName       The name of the drawable resource to be displayed as an icon.
 * @param tintColor             The color to tint the icon with.
 * @param iconSizeDp            The size of the icon in density-independent pixels (dp).
 *
 * This Composable function creates a Column layout with the provided [modifier]. Within the Column,
 * it attempts to retrieve the drawable resource associated with [drawableResName] using the current
 * Android context. If the resource is found, it is displayed as an Icon with the specified [tintColor]
 * and [iconSizeDp], vertically centered and horizontally aligned within the Column.
 *
 * Example usage:
 * ```
 * IconFromResourceColumn(
 *     modifier = Modifier.fillMaxSize(),
 *     drawableResName = "ic_my_icon",
 *     tintColor = Color.Red,
 *     iconSizeDp = 24
 * )
 * ```
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
 * @param modifier              The modifier for the Row layout.
 * @param bitmap                The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
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
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop
 * )
 * ```
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
 * Composable function to display an image within a Column layout, either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param modifier              The modifier for the Column layout.
 * @param bitmap                The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
 *
 * This Composable function creates a Column layout with the provided [modifier]. Within the Column,
 * it evaluates the [bitmap] parameter. If a non-null [bitmap] is provided, that Bitmap is displayed
 * with the specified [contentScale] and [imgSizeDp], vertically centered and horizontally aligned within the Column.
 * If [bitmap] is null, it attempts to retrieve a drawable resource using the current Android context and displays the placeholder image.
 * The placeholder image is displayed with the specified [contentScale] and [imgSizeDp], vertically centered and horizontally aligned within the Column.
 *
 * Example usage:
 * ```
 * ImageFromBitmapColumn(
 *     modifier = Modifier.fillMaxHeight(),
 *     bitmap = myBitmap,
 *     imgSizeDp = 200,
 *     contentScale = ContentScale.Crop
 * )
 * ```
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
 * @param modifier           The modifier for the Row layout.
 * @param imageUrl           The URL of the image to be loaded and displayed.
 * @param imgSizeDp          The size of the displayed image in density-independent pixels (dp).
 * @param contentScale       The scale type for the image content, such as [ContentScale.Crop], [ContentScale.FillBounds], etc.
 * @param placeHolderResName The name of the drawable resource to be used as a placeholder image while the URL image is loading. It can be null if no placeholder is required.
 *
 * This Composable function loads and displays an image from the provided [imageUrl] within a Row layout in Jetpack Compose. It supports specifying the [imgSizeDp] for the image's size in dp, the [contentScale] for determining how the image content is scaled, and an optional [placeHolderResName] to use a placeholder image during loading.
 *
 * Example usage:
 * ```
 * ImageFromUrlRow(
 *     modifier = Modifier.fillMaxWidth(),
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
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
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
 * Composable function to load and display an image from a URL within a Column layout in Jetpack Compose.
 *
 * @param modifier           The modifier for the Column layout.
 * @param imageUrl           The URL of the image to be loaded and displayed.
 * @param imgSizeDp          The size of the displayed image in density-independent pixels (dp).
 * @param contentScale       The scale type for the image content, such as [ContentScale.Crop], [ContentScale.FillBounds], etc.
 * @param placeHolderResName The name of the drawable resource to be used as a placeholder image while the URL image is loading. It can be null if no placeholder is required.
 *
 * This Composable function loads and displays an image from the provided [imageUrl] within a Column layout in Jetpack Compose. It supports specifying the [imgSizeDp] for the image's size in dp, the [contentScale] for determining how the image content is scaled, and an optional [placeHolderResName] to use a placeholder image during loading.
 *
 * Example usage:
 * ```
 * ImageFromUrlColumn(
 *     modifier = Modifier.fillMaxHeight(),
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
    imageUrl: String,
    imgSizeDp: Int,
    contentScale: ContentScale,
    placeHolderResName: String?,
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, placeHolderResName)
        ?.let { painterResource(it) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
 * @param modifier              The modifier for the Row layout.
 * @param bitmap                The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
 * @param clipShape             The shape used to clip the image.
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
 * Composable function to display a clipped image within a Column layout, either from a provided Bitmap or a placeholder drawable resource.
 *
 * @param modifier              The modifier for the Column layout.
 * @param bitmap                The Bitmap to display as an image. If null, a placeholder image is used.
 * @param imgSizeDp             The size of the image in density-independent pixels (dp).
 * @param contentScale          The content scale for the image.
 * @param clipShape             The shape used to clip the image.
 *
 * This Composable function creates a Column layout with the provided [modifier]. Within the Column,
 * it evaluates the [bitmap] parameter. If a non-null [bitmap] is provided, that Bitmap is displayed
 * with the specified [contentScale] and [imgSizeDp], vertically centered and horizontally aligned within the Column.
 * The image is also clipped using the provided [clipShape]. If [bitmap] is null, it attempts to retrieve a drawable
 * resource using the current Android context and displays the placeholder image. The placeholder image is displayed with
 * the specified [contentScale], [imgSizeDp], and is also clipped using the provided [clipShape].
 *
 * Example usage:
 * ```
 * ImageFromBitmapClippedColumn(
 *     modifier = Modifier.fillMaxHeight(),
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
                    contentDescription = IMAGE,
                    tint = tintColor
                )
        }
    }
}

/**
 * Composable function to display a clickable icon from a drawable resource within a Row layout.
 *
 * @param modifier              The modifier for the Row layout.
 * @param drawableResName       The name of the drawable resource to be displayed as an icon.
 * @param tintColor             The color to tint the icon with.
 * @param iconSizeDp            The size of the icon in density-independent pixels (dp).
 * @param onClick               The lambda function to be executed when the icon is clicked.
 *
 * This Composable function creates a Row layout with the provided [modifier]. Within the Row, it displays a clickable icon using the specified
 * [drawableResName], [tintColor], and [iconSizeDp]. When the icon is clicked, the specified [onClick] lambda function is invoked.
 *
 * Example usage:
 * ```
 * ClickableIconFromResourceRow(
 *     modifier = Modifier.fillMaxWidth(),
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
 * Composable function to display a clickable icon from a drawable resource within a Column layout.
 *
 * @param modifier              The modifier for the Column layout.
 * @param drawableResName       The name of the drawable resource to be displayed as an icon.
 * @param tintColor             The color to tint the icon with.
 * @param iconSizeDp            The size of the icon in density-independent pixels (dp).
 * @param onClick               The lambda function to be executed when the icon is clicked.
 *
 * This Composable function creates a Column layout with the provided [modifier]. Within the Column, it displays a clickable icon using the specified
 * [drawableResName], [tintColor], and [iconSizeDp]. When the icon is clicked, the specified [onClick] lambda function is invoked.
 *
 * Example usage:
 * ```
 * ClickableIconFromResourceColumn(
 *     modifier = Modifier.fillMaxHeight(),
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