package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
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
 * @param modifier                      The modifier for customizing the card's layout and appearance.
 * @param contentVerticalArrangement    The vertical arrangement of the content of the card.
 * @param contentHorizontalAlignment    The horizontal alignment of the content of the card.
 * @param borderStrokeWidthDp           The width of the border stroke around the card. (optional)
 * @param borderStrokeColor             The color of the border stroke around the card. (optional)
 * @param backgroundColor               The background color of the card.
 * @param elevationDp                   The elevation of the card, creating a shadow effect.
 * @param contentPaddingDp              The padding for the content within the card.
 * @param title                         The title or user name to display on the card.
 * @param subtitle                      Additional information or description to display on the card.
 * @param titleTypography               The style of the title in material design scale
 * @param subtitleTypography            The style of the subtitle in material design scale
 * @param painterResource               The [Painter] representing the image resource to be displayed. If `null`, a placeholder [Painter] is loaded and used.
 * @param onClick                       Lambda function to handle click events on the card.
 *
 * This Composable function creates a Card with customizable attributes to present user profile information.
 * It allows you to specify the card's appearance, including border, background color, elevation, and content padding.
 * The profile card can display a user's title, subtitle, and an image, with support for click interaction.
 *
 * Example usage:
 * ```
 * ClickableContactCard(
 *     modifier = Modifier.fillMaxWidth(),
 *     contentVerticalArrangement = Arrangement.Center,
 *     contentHorizontalAlignment = Alignment.CenterHorizontally,
 *     borderStrokeWidth = 1,
 *     borderStrokeColor = Color.Gray,
 *     backgroundColor = Color.White,
 *     elevation = 4,
 *     contentPadding = 16,
 *     title = "John Doe",
 *     subtitle = "Front-End Developer",
 *     titleTypography = Material.typography.h1,
 *     subtitleTypography = Material.typography.h2,
 *     titleTextAlignment = TextAlign.Center,
 *     subtitleTextAlignment = TextAlign.Center,
 *     painterResource = painterResource(id = R.drawable.cow),
 *     onClick = { /* Handle card click */ }
 * )
 * ```
 */
@Composable
fun ClickableContactCard(
    modifier: Modifier,
    contentVerticalArrangement: Arrangement.Vertical = Arrangement.Center,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    borderStrokeWidthDp: Int = 0,
    borderStrokeColor: Color = Color.Transparent,
    backgroundColor: Color,
    elevationDp: Int,
    contentPaddingDp: Int,
    title: String?,
    subtitle: String?,
    titleTypography: TextStyle,
    subtitleTypography: TextStyle,
    painterResource: Painter?,
    onClick: () -> Unit
) {
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
            modifier = Modifier.padding(contentPaddingDp.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = contentVerticalArrangement,
            horizontalAlignment = contentHorizontalAlignment
        ) {
            if (painterResource == null) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                    ?.let { painterResource(it) }?.let {
                        Image(
                            contentDescription = IMAGE,
                            painter = it,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(DP_200.dp)
                                .clip(shape = RoundedCornerShape(DP_16.dp))
                        )
                    }
            } else  {
                Image(
                    contentDescription = IMAGE,
                    painter = painterResource,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(DP_200.dp)
                        .clip(shape = RoundedCornerShape(DP_16.dp))
                )
            }
            Spacer(modifier = Modifier.height(DP_8.dp))
            title?.let { Text(text = it, style = titleTypography) }
            Spacer(modifier = Modifier.height(DP_8.dp))
            subtitle?.let { Text(text = it, style = subtitleTypography) }
        }
    }
}

