package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_200
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * A Composable function to create a clickable user profile card, which displays user information
 * with an optional image and supports user interaction.
 *
 * @param modifier                    The modifier for customizing the card's layout and appearance.
 * @param borderStrokeWidthDp         The width of the border stroke around the card.
 * @param borderStrokeColor           The color of the border stroke around the card.
 * @param backgroundColor             The background color of the card.
 * @param elevationDp                 The elevation of the card, creating a shadow effect.
 * @param contentPaddingDp            The padding for the content within the card.
 * @param title                       The title or user name to display on the card.
 * @param subtitle                    Additional information or description to display on the card.
 * @param titleTypography             The style of the title in material design scale
 * @param subtitleTypography          The style of the subtitle in material design scale
 * @param imageUrl                    The URL of the image to be displayed on the card.
 * @param onClick                     Lambda function to handle click events on the card.
 *
 * This Composable function creates a Card with customizable attributes to present user profile information.
 * It allows you to specify the card's appearance, including border, background color, elevation, and content padding.
 * The profile card can display a user's title, subtitle, and an image, with support for click interaction.
 *
 * Example usage:
 * ```
 * ClickableProfileCard(
 *     modifier = Modifier.fillMaxWidth(),
 *     borderStrokeWidth = 1,
 *     borderStrokeColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     elevation = 4,
 *     contentPadding = 16,
 *     title = "John Doe",
 *     subtitle = "Front-End Developer",
 *     titleTypography = Material.typography.h1,
 *     subtitleTypography = Material.typography.h2,
 *     imageUrl = "https://example.com/user.jpg",
 *     onClick = { /* Handle card click */ }
 * )
 * ```
 */
@Composable
fun ClickableProfileCard(
    modifier: Modifier,
    borderStrokeWidthDp: Int,
    borderStrokeColor: Color,
    backgroundColor: Color,
    elevationDp: Int,
    contentPaddingDp: Int,
    title: String?,
    subtitle: String?,
    titleTypography: TextStyle,
    subtitleTypography: TextStyle,
    imageUrl: String?,
    onClick: () -> Unit
) {
    val placeholder = LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
        ?.let { painterResource(it) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        backgroundColor = backgroundColor,
        elevation = elevationDp.dp,
        shape = RoundedCornerShape(DP_16.dp),
        border = BorderStroke(borderStrokeWidthDp.dp, borderStrokeColor)
    ) {
        Column(
            modifier = Modifier.padding(contentPaddingDp.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl,
                    placeholder = placeholder,
                    error = placeholder,
                ),
                contentScale = ContentScale.Crop,
                contentDescription = IMAGE,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DP_200.dp)
                    .clip(shape = RoundedCornerShape(DP_16.dp))
            )
            Spacer(modifier = Modifier.height(DP_8.dp))
            title?.let { Text(text = it, style = titleTypography) }
            Spacer(modifier = Modifier.height(DP_8.dp))
            subtitle?.let { Text(text = it, style = subtitleTypography) }
        }
    }
}

