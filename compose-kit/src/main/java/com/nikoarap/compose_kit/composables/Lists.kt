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
 * Composable function to display a lazy list of items with customizable properties.
 *
 * @param items                             The list of items to be displayed in the lazy list.
 * @param listTopPaddingDp                  The top padding of the lazy list.
 * @param listBottomPaddingDp               The bottom padding of the lazy list.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the lazy list.
 * @param itemPaddingDp                     The padding around each item in the lazy list.
 * @param backgroundColor                   The background color of the lazy list.
 * @param renderItem                        The composable function used to render each item in the lazy list.
 *
 * This Composable function creates a lazy list using [LazyColumn]. It allows you to customize various properties of the
 * lazy list, including the padding, spacing between items, and the background color. You can define the rendering of each
 * item using the [renderItem] composable function.
 *
 * Example usage:
 * ```kotlin
 * val items = (1..100).toList()
 * LazyList(
 *     items = items,
 *     listTopPaddingDp = 16,
 *     listBottomPaddingDp = 16,
 *     verticalSpaceBetweenItemsDp = 8,
 *     itemPaddingDp = 16,
 *     backgroundColor = Color.White
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the list.
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
 * Composable function to display a lazy list of items with customizable properties and a fallback content
 * to be displayed when the list is empty.
 *
 * @param items                             The list of items to be displayed in the lazy list.
 * @param listTopPaddingDp                  The top padding of the lazy list.
 * @param listBottomPaddingDp               The bottom padding of the lazy list.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the lazy list.
 * @param itemPaddingDp                     The padding around each item in the lazy list.
 * @param backgroundColor                   The background color of the lazy list.
 * @param renderItem                        The composable function used to render each item in the lazy list.
 * @param fallbackMessage                   The message to display when the list is empty.
 * @param fallbackImage                     The optional image to display as fallback when the list is empty.
 * @param fallbackImageSizeDp               The size of the fallback image in DP.
 *
 * This Composable function creates a lazy list using [LazyColumn]. It allows you to customize various properties of the
 * lazy list, including the padding, spacing between items, and the background color. You can define the rendering of each
 * item using the [renderItem] composable function. If the list is empty, a fallback message and an optional image can be
 * displayed as a placeholder.
 *
 * Example usage:
 * ```kotlin
 * val items = (1..100).toList()
 * LazyList(
 *     items = items,
 *     listTopPaddingDp = 16,
 *     listBottomPaddingDp = 16,
 *     verticalSpaceBetweenItemsDp = 8,
 *     itemPaddingDp = 16,
 *     backgroundColor = Color.White,
 *     fallbackMessage = "No items available",
 *     fallbackImage = myImageBitmap,
 *     fallbackImageSizeDp = 100
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the list.
 */
@Composable
fun <T> LazyListWithFallback(
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
 * Composable function to display a lazy list of items with pull-to-refresh functionality using SwipeRefresh.
 *
 * @param items                             The list of items to be displayed in the lazy list.
 * @param refreshing                        A boolean representing whether the pull-to-refresh operation is currently in progress.
 * @param listTopPaddingDp                  The top padding of the lazy list.
 * @param listBottomPaddingDp               The bottom padding of the lazy list.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the lazy list.
 * @param itemPaddingDp                     The padding around each item in the lazy list.
 * @param backgroundColor                   The background color of the lazy list.
 * @param swipeRefreshIndicatorColor        The color of the SwipeRefresh indicator.
 * @param onRefresh                         The callback to trigger the pull-to-refresh operation.
 * @param renderItem                        The composable function used to render each item in the lazy list.
 *
 * This Composable function creates a lazy list using [LazyColumn] and adds pull-to-refresh functionality using SwipeRefresh.
 * It allows you to customize various properties of the lazy list, including the padding, spacing between items, and background color.
 * The [onRefresh] callback is invoked when the user initiates a pull-to-refresh action.
 *
 * Example usage:
 * ```kotlin
 * val items = (1..100).toList()
 * val refreshing by remember { mutableStateOf(false) }
 * LazyListWithSwipeRefresh(
 *     items = items,
 *     refreshing = refreshing,
 *     listTopPaddingDp = 16,
 *     listBottomPaddingDp = 16,
 *     verticalSpaceBetweenItemsDp = 8,
 *     itemPaddingDp = 16,
 *     backgroundColor = Color.White,
 *     swipeRefreshIndicatorColor = Color.Gray,
 *     onRefresh = {
 *         // Perform refresh operation here
 *         refreshing = true // Set to false when refresh is complete
 *     }
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the list.
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
 * Composable function to display a lazy list of items with pull-to-refresh functionality using SwipeRefresh, including a fallback view when the list is empty.
 *
 * @param items                             The list of items to be displayed in the lazy list.
 * @param refreshing                        A boolean representing whether the pull-to-refresh operation is currently in progress.
 * @param listTopPaddingDp                  The top padding of the lazy list.
 * @param listBottomPaddingDp               The bottom padding of the lazy list.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the lazy list.
 * @param itemPaddingDp                     The padding around each item in the lazy list.
 * @param backgroundColor                   The background color of the lazy list.
 * @param swipeRefreshIndicatorColor        The color of the SwipeRefresh indicator.
 * @param onRefresh                         The callback to trigger the pull-to-refresh operation.
 * @param renderItem                        The composable function used to render each item in the lazy list.
 * @param fallbackMessage                   The message to be displayed in the fallback view when the list is empty.
 * @param fallbackImage                     The optional image to be displayed in the fallback view. Pass null if no image is needed.
 * @param fallbackImageSizeDp               The size of the fallback image.
 *
 * This Composable function creates a lazy list using [LazyColumn] and adds pull-to-refresh functionality using SwipeRefresh.
 * If the list is empty, a fallback view with the specified [fallbackMessage] and optional [fallbackImage] is displayed.
 * The [onRefresh] callback is invoked when the user initiates a pull-to-refresh action.
 *
 * Example usage:
 * ```kotlin
 * val items = (1..100).toList()
 * val refreshing by remember { mutableStateOf(false) }
 * LazyListWithSwipeRefreshAndFallback(
 *     items = items,
 *     refreshing = refreshing,
 *     listTopPaddingDp = 16,
 *     listBottomPaddingDp = 16,
 *     verticalSpaceBetweenItemsDp = 8,
 *     itemPaddingDp = 16,
 *     backgroundColor = Color.White,
 *     swipeRefreshIndicatorColor = Color.Gray,
 *     onRefresh = {
 *         // Perform refresh operation here
 *         refreshing = true // Set to false when refresh is complete
 *     }
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the list.
 */
@Composable
fun <T> LazyListWithSwipeRefreshAndFallback(
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