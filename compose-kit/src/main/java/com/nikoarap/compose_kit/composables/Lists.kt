package com.nikoarap.compose_kit.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_20
import com.nikoarap.compose_kit.utils.Constants.Companion.IMAGE
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER
import com.nikoarap.compose_kit.utils.LayoutUtils

/**
 * A generic Composable for displaying a simple lazy list of items.
 *
 * @param items                             The list of items to be displayed in the list.
 * @param listTopPaddingDp                  The top padding for the list in dp.
 * @param listBottomPaddingDp               The bottom padding for the list in dp.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the list in dp.
 * @param itemPaddingDp                     The padding around individual items in the list in dp.
 * @param backgroundColor                   The background color for the entire composable.
 * @param renderItem                        A composable lambda function that defines how to render each item in the list.
 */
@Composable
fun <T> LazyList(
    items: List<T>,
    listTopPaddingDp: Int,
    listBottomPaddingDp: Int,
    verticalSpaceBetweenItemsDp: Int,
    itemPaddingDp: Int,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .background(backgroundColor)
            .padding(top = listTopPaddingDp.dp, bottom = listBottomPaddingDp.dp),
        verticalArrangement = Arrangement.spacedBy(verticalSpaceBetweenItemsDp.dp),
        contentPadding = PaddingValues(horizontal = itemPaddingDp.dp, vertical = itemPaddingDp.dp)
    ) {
        items(items) { item ->
            renderItem(item)
        }
    }
}

/**
 * A generic Composable for displaying a simple lazy list of items.
 * If the list has 0 items, a fallback screen is shown instead with a text and and image, prompting the user that the list is empty instead of an empty view.
 *
 * @param items                             The list of items to be displayed in the list.
 * @param listTopPaddingDp                  The top padding for the list in dp.
 * @param listBottomPaddingDp               The bottom padding for the list in dp.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the list in dp.
 * @param itemPaddingDp                     The padding around individual items in the list in dp.
 * @param backgroundColor                   The background color for the entire composable.
 * @param renderItem                        A composable lambda function that defines how to render each item in the list.
 * @param fallbackMessage                   A fallback message with explanation to the user, in case there are no items in the list.
 * @param fallbackImage                     A fallback image to style the error handling message, in case there are no items in the list.
 * @param fallbackImageSizeDp               The size of the fallback image in dp.
 */
@Composable
fun <T> LazyList(
    items: List<T>,
    listTopPaddingDp: Int,
    listBottomPaddingDp: Int,
    verticalSpaceBetweenItemsDp: Int,
    itemPaddingDp: Int,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Int
) {
    val scrollState = rememberLazyListState()

    if (items.isNotEmpty()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .background(backgroundColor)
                .padding(top = listTopPaddingDp.dp, bottom = listBottomPaddingDp.dp),
            verticalArrangement = Arrangement.spacedBy(verticalSpaceBetweenItemsDp.dp),
            contentPadding = PaddingValues(horizontal = itemPaddingDp.dp, vertical = itemPaddingDp.dp)
        ) {
            items(items) { item ->
                renderItem(item)
            }
        }
    } else {
        EmptyListFallback(fallbackMessage, backgroundColor, fallbackImage, fallbackImageSizeDp)
    }
}

/**
 * A generic Composable that displays a scrollable list of items with a swipe-to-refresh functionality.
 *
 * This Composable provides a lazy-loading list of items that can be refreshed by swiping down. It accepts
 * a list of [items] to display, and a [renderItem] lambda to define how each item is rendered.
 *
 * @param items                                 The list of items to be displayed in the list.
 * @param refreshing                            Whether a refresh action is in progress.
 * @param listTopPaddingDp                      The top padding for the list in dp.
 * @param listBottomPaddingDp                   The bottom padding for the list in dp.
 * @param verticalSpaceBetweenItemsDp           The vertical space between items in the list in dp.
 * @param itemPaddingDp                         The padding around individual items in the list in dp.
 * @param backgroundColor                       The background color for the entire composable.
 * @param swipeRefreshIndicatorColor            The color of the swipe-to-refresh indicator.
 * @param onRefresh                             A callback function to trigger data refresh when a swipe-to-refresh action occurs.
 * @param renderItem                            A composable lambda function that defines how to render each item in the list.
 */
@Composable
fun <T> LazyListWithSwipeRefresh(
    items: List<T>,
    refreshing: Boolean,
    listTopPaddingDp: Int,
    listBottomPaddingDp: Int,
    verticalSpaceBetweenItemsDp: Int,
    itemPaddingDp: Int,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit
) {
    val scrollState = rememberLazyListState()

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listTopPaddingDp.dp, bottom = listBottomPaddingDp.dp)
            .background(backgroundColor),
        state = rememberSwipeRefreshState(refreshing),
        onRefresh = onRefresh,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                contentColor = swipeRefreshIndicatorColor
            )
        },
        content = {
            ScrollableCard(backgroundColor)
            LazyColumn(
                state = scrollState,
                modifier = Modifier.background(backgroundColor),
                verticalArrangement = Arrangement.spacedBy(verticalSpaceBetweenItemsDp.dp),
                contentPadding = PaddingValues(horizontal = itemPaddingDp.dp, vertical = itemPaddingDp.dp)
            )
            {
                items(items) { item ->
                    renderItem(item)
                }
            }
        }
    )
}

/**
 * A generic Composable that displays a scrollable list of items with a swipe-to-refresh functionality.
 * If the list has 0 items, a fallback screen is shown instead with a text and and image, prompting the user that the list is empty instead of an empty view.
 *
 * This Composable provides a lazy-loading list of items that can be refreshed by swiping down. It accepts
 * a list of [items] to display, and a [renderItem] lambda to define how each item is rendered.
 *
 * @param items                                 The list of items to be displayed in the list.
 * @param refreshing                            Whether a refresh action is in progress.
 * @param listTopPaddingDp                      The top padding for the list in dp.
 * @param listBottomPaddingDp                   The bottom padding for the list in dp.
 * @param verticalSpaceBetweenItemsDp           The vertical space between items in the list in dp.
 * @param itemPaddingDp                         The padding around individual items in the list in dp.
 * @param backgroundColor                       The background color for the entire composable.
 * @param swipeRefreshIndicatorColor            The color of the swipe-to-refresh indicator.
 * @param onRefresh                             A callback function to trigger data refresh when a swipe-to-refresh action occurs.
 * @param renderItem                            A composable lambda function that defines how to render each item in the list.
 * @param fallbackMessage                   A fallback message with explanation to the user, in case there are no items in the list.
 * @param fallbackImage                     A fallback image to style the error handling message, in case there are no items in the list.
 * @param fallbackImageSizeDp               The size of the fallback image in dp.
 */
@Composable
fun <T> LazyListWithSwipeRefresh(
    items: List<T>,
    refreshing: Boolean,
    listTopPaddingDp: Int,
    listBottomPaddingDp: Int,
    verticalSpaceBetweenItemsDp: Int,
    itemPaddingDp: Int,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Int
) {
    val scrollState = rememberLazyListState()

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listTopPaddingDp.dp, bottom = listBottomPaddingDp.dp)
            .background(backgroundColor),
        state = rememberSwipeRefreshState(refreshing),
        onRefresh = onRefresh,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                contentColor = swipeRefreshIndicatorColor
            )
        },
        content = {
            ScrollableCard(backgroundColor)
            if (items.isNotEmpty()) {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.background(backgroundColor),
                    verticalArrangement = Arrangement.spacedBy(verticalSpaceBetweenItemsDp.dp),
                    contentPadding = PaddingValues(horizontal = itemPaddingDp.dp, vertical = itemPaddingDp.dp)
                )
                {
                    items(items) { item ->
                        renderItem(item)
                    }
                }
            } else {
                EmptyListFallback(fallbackMessage, backgroundColor, fallbackImage, fallbackImageSizeDp)
            }
        }
    )
}

/**
 * A Jetpack Compose composable that displays a fallback UI when a list is empty.
 *
 * @param fallbackMessage       String representing the message of the fallback UI.
 * @param backgroundColor       The background color of the fallback UI.
 * @param bitmap                A bitmap to display as a fallback image (if not null).
 * @param imgSizeDp             The size of the fallback image in density-independent pixels (dp).
 */
@Composable
fun EmptyListFallback(
    fallbackMessage: String,
    backgroundColor: Color,
    bitmap: Bitmap?,
    imgSizeDp: Int
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            Modifier
                .fillMaxSize()
                .padding(top = DP_20.dp, start = DP_16.dp, end = DP_16.dp, bottom = DP_20.dp)
        ) {
            when {
                bitmap != null -> {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = IMAGE,
                        modifier = Modifier.size(imgSizeDp.dp)
                    )
                }
                else -> {
                    LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                        ?.let { painterResource(it) }?.let {
                            Image(
                                contentDescription = IMAGE,
                                painter = it,
                                modifier = Modifier.size(imgSizeDp.dp)
                            )
                        }
                }
            }
            Spacer(Modifier.height(DP_16.dp))
            Text(fallbackMessage)
        }
    }
}

/**
 * Composable function that displays a Card component within a scrollable container.
 *
 * @param backgroundColor The background color of the scrollable component.
 */
@Composable
fun ScrollableCard(backgroundColor: Color) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(backgroundColor)
    ) {
        Card(Modifier.fillMaxSize()) {}
    }
}