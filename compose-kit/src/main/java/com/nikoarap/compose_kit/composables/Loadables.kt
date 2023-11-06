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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nikoarap.compose_kit.styles.DP_16
import com.nikoarap.compose_kit.styles.DP_20
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
 * ```
 * val items = (1..100).toList()
 * LazyList(
 *     items = items,
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
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
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .background(backgroundColor)
            .padding(top = listTopPaddingDp, bottom = listBottomPaddingDp),
        verticalArrangement = Arrangement.spacedBy(verticalSpaceBetweenItemsDp),
        contentPadding = PaddingValues(horizontal = itemPaddingDp, vertical = itemPaddingDp)
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
 * @param fallbackImageSizeDp               The size of the fallback image.
 *
 * This Composable function creates a lazy list using [LazyColumn]. It allows you to customize various properties of the
 * lazy list, including the padding, spacing between items, and the background color. You can define the rendering of each
 * item using the [renderItem] composable function. If the list is empty, a fallback message and an optional image can be
 * displayed as a placeholder.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * LazyList(
 *     items = items,
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White,
 *     fallbackMessage = "No items available",
 *     fallbackImage = myImageBitmap,
 *     fallbackImageSizeDp = 100.dp
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the list.
 */
@Composable
fun <T> ConditionalLazyList(
    items: List<T>,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Dp
) {
    if (items.isNotEmpty()) {
        LazyList(
            items = items,
            listTopPaddingDp = listTopPaddingDp,
            listBottomPaddingDp = listBottomPaddingDp,
            verticalSpaceBetweenItemsDp = verticalSpaceBetweenItemsDp,
            itemPaddingDp = itemPaddingDp,
            backgroundColor = backgroundColor,
            renderItem = renderItem
        )
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
 * ```
 * val items = (1..100).toList()
 * val refreshing by remember { mutableStateOf(false) }
 * LazyListWithSwipeRefresh(
 *     items = items,
 *     refreshing = refreshing,
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
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
fun <T> SwipeRefreshLazyList(
    items: List<T>,
    refreshing: Boolean,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listTopPaddingDp, bottom = listBottomPaddingDp)
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
            LazyList(
                items = items,
                listTopPaddingDp = listTopPaddingDp,
                listBottomPaddingDp = listBottomPaddingDp,
                verticalSpaceBetweenItemsDp = verticalSpaceBetweenItemsDp,
                itemPaddingDp = itemPaddingDp,
                backgroundColor = backgroundColor,
                renderItem = renderItem
            )
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
 * ```
 * val items = (1..100).toList()
 * val refreshing by remember { mutableStateOf(false) }
 * LazyListWithSwipeRefreshAndFallback(
 *     items = items,
 *     refreshing = refreshing,
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
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
fun <T> ConditionalSwipeRefreshLazyList(
    items: List<T>,
    refreshing: Boolean,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Dp
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listTopPaddingDp, bottom = listBottomPaddingDp)
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
                LazyList(
                    items = items,
                    listTopPaddingDp = listTopPaddingDp,
                    listBottomPaddingDp = listBottomPaddingDp,
                    verticalSpaceBetweenItemsDp = verticalSpaceBetweenItemsDp,
                    itemPaddingDp = itemPaddingDp,
                    backgroundColor = backgroundColor,
                    renderItem = renderItem
                )
            } else {
                EmptyListFallback(fallbackMessage, backgroundColor, fallbackImage, fallbackImageSizeDp)
            }
        }
    )
}

/**
 * Composable function to display a lazy vertical grid of items with customizable properties.
 *
 * @param items                             The list of items to be displayed in the lazy grid.
 * @param gridCells                         The layout of the grid, specifying the number of columns and rows.
 * @param listTopPaddingDp                  The top padding of the lazy grid.
 * @param listBottomPaddingDp               The bottom padding of the lazy grid.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the lazy grid.
 * @param itemPaddingDp                     The padding around each item in the lazy grid.
 * @param backgroundColor                   The background color of the lazy grid.
 * @param renderItem                        The composable function used to render each item in the lazy grid.
 *
 * This Composable function creates a lazy grid using [LazyVerticalGrid]. It allows you to customize various properties of
 * the lazy grid, including the layout, padding, spacing between items, and the background color. You can define the
 * rendering of each item using the [renderItem] composable function.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * CustomGrid(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the grid.
 */
@Composable
fun <T> VerticalGrid(
    items: List<T>,
    gridCells: GridCells,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit
) {
    val scrollState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = gridCells,
        state = scrollState,
        modifier = Modifier
            .background(backgroundColor)
            .padding(top = listTopPaddingDp, bottom = listBottomPaddingDp),
        verticalArrangement = Arrangement.spacedBy(verticalSpaceBetweenItemsDp),
        contentPadding = PaddingValues(horizontal = itemPaddingDp, vertical = itemPaddingDp)
    ) {
        items(items) { item ->
            renderItem(item)
        }
    }
}

/**
 * A Jetpack Compose composable that displays a vertical grid of items with customizable properties and a fallback view
 * when the grid is empty.
 *
 * @param items                             The list of items to be displayed in the vertical grid.
 * @param gridCells                         The layout of the grid, specifying the number of columns and rows.
 * @param listTopPaddingDp                  The top padding of the vertical grid.
 * @param listBottomPaddingDp               The bottom padding of the vertical grid.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the vertical grid.
 * @param itemPaddingDp                     The padding around each item in the vertical grid.
 * @param backgroundColor                   The background color of the vertical grid.
 * @param renderItem                        The composable function used to render each item in the vertical grid.
 * @param fallbackMessage                   The message to be displayed when the grid is empty.
 * @param fallbackImage                     The optional image to be displayed in the fallback view.
 * @param fallbackImageSizeDp               The size of the optional fallback image.
 *
 * This Composable function creates a vertical grid using [LazyVerticalGrid]. It allows you to customize various properties
 * of the vertical grid, including the layout, padding, spacing between items, and the background color. You can define the
 * rendering of each item using the [renderItem] composable function. When the grid is empty, a fallback view with a message
 * and an optional image can be displayed.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * VerticalGridWithFallback(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the vertical grid.
 */
@Composable
fun <T> ConditionalVerticalGrid(
    items: List<T>,
    gridCells: GridCells,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Dp
) {
    if (items.isNotEmpty()) {
        VerticalGrid(
            items = items,
            gridCells = gridCells,
            listTopPaddingDp = listTopPaddingDp,
            listBottomPaddingDp = listBottomPaddingDp,
            verticalSpaceBetweenItemsDp = verticalSpaceBetweenItemsDp,
            itemPaddingDp = itemPaddingDp,
            backgroundColor = backgroundColor,
            renderItem = renderItem
        )
    } else {
        EmptyListFallback(fallbackMessage, backgroundColor, fallbackImage, fallbackImageSizeDp)
    }
}

/**
 * A Jetpack Compose composable that displays a vertical grid of items with customizable properties and supports swipe-to-refresh.
 *
 * @param items                             The list of items to be displayed in the vertical grid.
 * @param gridCells                         The layout of the grid, specifying the number of columns and rows.
 * @param listTopPaddingDp                  The top padding of the vertical grid.
 * @param listBottomPaddingDp               The bottom padding of the vertical grid.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the vertical grid.
 * @param itemPaddingDp                     The padding around each item in the vertical grid.
 * @param backgroundColor                   The background color of the vertical grid.
 * @param swipeRefreshIndicatorColor        The color of the swipe-to-refresh indicator.
 * @param refreshing                        A boolean indicating whether the swipe-to-refresh is currently in progress.
 * @param onRefresh                         A callback function to trigger the refresh action.
 * @param renderItem                        The composable function used to render each item in the vertical grid.
 *
 * This Composable function creates a vertical grid using [VerticalGrid]. It allows you to customize various properties of the
 * vertical grid, including the layout, padding, spacing between items, and the background color. Swipe-to-refresh functionality
 * is supported, allowing users to refresh the grid by swiping down, with the [onRefresh] callback being invoked.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * VerticalGridWithSwipeRefresh(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White,
 *     swipeRefreshIndicatorColor = Color.Blue,
 *     refreshing = false
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the vertical grid.
 */
@Composable
fun <T> SwipeRefreshVerticalGrid(
    items: List<T>,
    gridCells: GridCells,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listTopPaddingDp, bottom = listBottomPaddingDp)
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
            VerticalGrid(
                items = items,
                gridCells = gridCells,
                listTopPaddingDp = listTopPaddingDp,
                listBottomPaddingDp = listBottomPaddingDp,
                verticalSpaceBetweenItemsDp = verticalSpaceBetweenItemsDp,
                itemPaddingDp = itemPaddingDp,
                backgroundColor = backgroundColor,
                renderItem = renderItem
            )
        }
    )
}

/**
 * Composable function that displays a swipe-refreshable vertical grid with conditional rendering of items and fallback content.
 *
 * @param items                             The list of items to be displayed in the vertical grid.
 * @param gridCells                         The grid layout specification for the vertical grid.
 * @param refreshing                        A boolean indicating whether the swipe-refresh indicator is in a refreshing state.
 * @param listTopPaddingDp                  The top padding of the vertical grid.
 * @param listBottomPaddingDp               The bottom padding of the vertical grid.
 * @param verticalSpaceBetweenItemsDp       The vertical space between items in the vertical grid.
 * @param itemPaddingDp                     The padding around each item in the vertical grid.
 * @param backgroundColor                   The background color of the vertical grid.
 * @param swipeRefreshIndicatorColor        The color of the swipe-refresh indicator.
 * @param onRefresh                         Lambda function to handle the swipe-refresh action.
 * @param renderItem                        The composable function used to render each item in the vertical grid.
 * @param fallbackMessage                   The message to be displayed in the fallback content when there are no items.
 * @param fallbackImage                     The optional image to be displayed in the fallback content.
 * @param fallbackImageSizeDp               The size of the fallback image in density-independent pixels (dp).
 *
 * This composable creates a swipe-refreshable vertical grid. It allows conditional rendering of items based on the presence
 * of data in the [items] list. When items are present, the grid is displayed with the ability to perform swipe-refresh. If
 * no items are present, a fallback message and an optional image can be displayed.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * ConditionalSwipeRefreshVerticalGrid(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     refreshing = false,
 *     listTopPaddingDp = 16.dp,
 *     listBottomPaddingDp = 16.dp,
 *     verticalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White,
 *     swipeRefreshIndicatorColor = Color.Gray,
 *     onRefresh = { /* Handle refresh action */ },
 *     renderItem = { item -> /* Render each item */ },
 *     fallbackMessage = "No items available",
 *     fallbackImage = bitmap,
 *     fallbackImageSizeDp = 48.dp
 * )
 * ```
 *
 * @param T The type of items in the list.
 */
@Composable
fun <T> ConditionalSwipeRefreshVerticalGrid(
    items: List<T>,
    gridCells: GridCells,
    refreshing: Boolean,
    listTopPaddingDp: Dp,
    listBottomPaddingDp: Dp,
    verticalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Dp
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listTopPaddingDp, bottom = listBottomPaddingDp)
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
            if (items.isNotEmpty()) {
                VerticalGrid(
                    items = items,
                    gridCells = gridCells,
                    listTopPaddingDp = listTopPaddingDp,
                    listBottomPaddingDp = listBottomPaddingDp,
                    verticalSpaceBetweenItemsDp = verticalSpaceBetweenItemsDp,
                    itemPaddingDp = itemPaddingDp,
                    backgroundColor = backgroundColor,
                    renderItem = renderItem
                )
            } else {
                EmptyListFallback(fallbackMessage, backgroundColor, fallbackImage, fallbackImageSizeDp)
            }
        }
    )
}

/**
 * Composable function to display a horizontal grid of items with customizable properties.
 *
 * @param items                             The list of items to be displayed in the horizontal grid.
 * @param gridCells                         The specification of the grid cells layout.
 * @param listStartPaddingDp                The start padding of the horizontal grid.
 * @param listEndPaddingDp                  The end padding of the horizontal grid.
 * @param horizontalSpaceBetweenItemsDp     The horizontal space between items in the grid.
 * @param itemPaddingDp                     The padding around each item in the grid.
 * @param backgroundColor                   The background color of the horizontal grid.
 * @param renderItem                        The composable function used to render each item in the grid.
 *
 * This Composable function creates a horizontal grid using [LazyHorizontalGrid]. It allows you to customize various
 * properties of the grid, including the start and end padding, spacing between items, and the background color. You can
 * define the rendering of each item using the [renderItem] composable function.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * HorizontalGrid(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     listStartPaddingDp = 16.dp,
 *     listEndPaddingDp = 16.dp,
 *     horizontalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the grid.
 */
@Composable
fun <T> HorizontalGrid(
    items: List<T>,
    gridCells: GridCells,
    listStartPaddingDp: Dp,
    listEndPaddingDp: Dp,
    horizontalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit
) {
    val scrollState = rememberLazyGridState()

    LazyHorizontalGrid(
        rows = gridCells,
        state = scrollState,
        modifier = Modifier
            .background(backgroundColor)
            .padding(start = listStartPaddingDp, end = listEndPaddingDp),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpaceBetweenItemsDp),
        contentPadding = PaddingValues(horizontal = itemPaddingDp, vertical = itemPaddingDp)
    ) {
        items(items) { item ->
            renderItem(item)
        }
    }
}

/**
 * Composable function to display a conditional horizontal grid of items with fallback content.
 *
 * @param items                             The list of items to be displayed in the horizontal grid.
 * @param gridCells                         The specification of the grid cells layout.
 * @param listStartPaddingDp                The start padding of the horizontal grid.
 * @param listEndPaddingDp                  The end padding of the horizontal grid.
 * @param horizontalSpaceBetweenItemsDp     The horizontal space between items in the grid.
 * @param itemPaddingDp                     The padding around each item in the grid.
 * @param backgroundColor                   The background color of the horizontal grid.
 * @param renderItem                        The composable function used to render each item in the grid.
 * @param fallbackMessage                   The message to be displayed when the item list is empty.
 * @param fallbackImage                     The fallback image to be displayed when the item list is empty.
 * @param fallbackImageSizeDp               The size of the fallback image.
 *
 * This Composable function conditionally displays a horizontal grid using [HorizontalGrid] when the item list is not
 * empty. When the item list is empty, it shows fallback content provided through [EmptyListFallback].
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * ConditionalHorizontalGrid(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     listStartPaddingDp = 16.dp,
 *     listEndPaddingDp = 16.dp,
 *     horizontalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the grid.
 */
@Composable
fun <T> ConditionalHorizontalGrid(
    items: List<T>,
    gridCells: GridCells,
    listStartPaddingDp: Dp,
    listEndPaddingDp: Dp,
    horizontalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Dp
) {
    if (items.isNotEmpty()) {
        HorizontalGrid(
            items = items,
            gridCells = gridCells,
            listStartPaddingDp = listStartPaddingDp,
            listEndPaddingDp = listEndPaddingDp,
            horizontalSpaceBetweenItemsDp = horizontalSpaceBetweenItemsDp,
            itemPaddingDp = itemPaddingDp,
            backgroundColor = backgroundColor,
            renderItem = renderItem
        )
    } else {
        EmptyListFallback(fallbackMessage, backgroundColor, fallbackImage, fallbackImageSizeDp)
    }
}

/**
 * Composable function to display a swipe refreshable horizontal grid of items.
 *
 * @param items                             The list of items to be displayed in the horizontal grid.
 * @param gridCells                         The specification of the grid cells layout.
 * @param listStartPaddingDp                The start padding of the horizontal grid.
 * @param listEndPaddingDp                  The end padding of the horizontal grid.
 * @param horizontalSpaceBetweenItemsDp     The horizontal space between items in the grid.
 * @param itemPaddingDp                     The padding around each item in the grid.
 * @param backgroundColor                   The background color of the horizontal grid.
 * @param swipeRefreshIndicatorColor        The color of the swipe refresh indicator.
 * @param refreshing                        Whether the swipe refresh is currently in progress.
 * @param onRefresh                         The callback to handle the refresh action.
 * @param renderItem                        The composable function used to render each item in the grid.
 *
 * This Composable function creates a swipe refreshable horizontal grid using [SwipeRefresh] and [HorizontalGrid]. It allows
 * you to customize the appearance and behavior of the horizontal grid, including the padding, spacing between items,
 * background color, swipe refresh indicator color, and refresh action callback.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * SwipeRefreshHorizontalGrid(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     listStartPaddingDp = 16.dp,
 *     listEndPaddingDp = 16.dp,
 *     horizontalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White,
 *     swipeRefreshIndicatorColor = Color.Blue,
 *     refreshing = isRefreshing,
 *     onRefresh = { /* Handle refresh action */ }
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the grid.
 */
@Composable
fun <T> SwipeRefreshHorizontalGrid(
    items: List<T>,
    gridCells: GridCells,
    listStartPaddingDp: Dp,
    listEndPaddingDp: Dp,
    horizontalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listStartPaddingDp, bottom = listEndPaddingDp)
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
            HorizontalGrid(
                items = items,
                gridCells = gridCells,
                listStartPaddingDp = listStartPaddingDp,
                listEndPaddingDp = listEndPaddingDp,
                horizontalSpaceBetweenItemsDp = horizontalSpaceBetweenItemsDp,
                itemPaddingDp = itemPaddingDp,
                backgroundColor = backgroundColor,
                renderItem = renderItem
            )
        }
    )
}

/**
 * Composable function to display a swipe refreshable horizontal grid of items with a conditional fallback view.
 *
 * @param items                             The list of items to be displayed in the horizontal grid.
 * @param gridCells                         The specification of the grid cells layout.
 * @param refreshing                        Whether the swipe refresh is currently in progress.
 * @param listStartPaddingDp                The start padding of the horizontal grid.
 * @param listEndPaddingDp                  The end padding of the horizontal grid.
 * @param horizontalSpaceBetweenItemsDp     The horizontal space between items in the grid.
 * @param itemPaddingDp                     The padding around each item in the grid.
 * @param backgroundColor                   The background color of the horizontal grid.
 * @param swipeRefreshIndicatorColor        The color of the swipe refresh indicator.
 * @param onRefresh                         The callback to handle the refresh action.
 * @param renderItem                        The composable function used to render each item in the grid.
 * @param fallbackMessage                   The message to display when there are no items.
 * @param fallbackImage                     The image to display when there are no items.
 * @param fallbackImageSizeDp               The size of the fallback image in density-independent pixels (dp).
 *
 * This Composable function creates a swipe refreshable horizontal grid using [SwipeRefresh] and [HorizontalGrid]. It allows
 * you to customize the appearance and behavior of the horizontal grid, including the padding, spacing between items,
 * background color, swipe refresh indicator color, and refresh action callback. If the list of items is empty, it displays
 * a fallback view with the specified message and image.
 *
 * Example usage:
 * ```
 * val items = (1..100).toList()
 * ConditionalSwipeRefreshHorizontalGrid(
 *     items = items,
 *     gridCells = GridCells.Fixed(2),
 *     refreshing = isRefreshing,
 *     listStartPaddingDp = 16.dp,
 *     listEndPaddingDp = 16.dp,
 *     horizontalSpaceBetweenItemsDp = 8.dp,
 *     itemPaddingDp = 16.dp,
 *     backgroundColor = Color.White,
 *     swipeRefreshIndicatorColor = Color.Blue,
 *     onRefresh = { /* Handle refresh action */ }
 * ) { item ->
 *     Text(text = "Item $item")
 * }
 * ```
 *
 * @param T The type of items in the grid.
 */
@Composable
fun <T> ConditionalSwipeRefreshHorizontalGrid(
    items: List<T>,
    gridCells: GridCells,
    refreshing: Boolean,
    listStartPaddingDp: Dp,
    listEndPaddingDp: Dp,
    horizontalSpaceBetweenItemsDp: Dp,
    itemPaddingDp: Dp,
    backgroundColor: Color,
    swipeRefreshIndicatorColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit,
    fallbackMessage: String,
    fallbackImage: Bitmap?,
    fallbackImageSizeDp: Dp
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = listStartPaddingDp, bottom = listEndPaddingDp)
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
            if (items.isNotEmpty()) {
                HorizontalGrid(
                    items = items,
                    gridCells = gridCells,
                    listStartPaddingDp = listStartPaddingDp,
                    listEndPaddingDp = listEndPaddingDp,
                    horizontalSpaceBetweenItemsDp = horizontalSpaceBetweenItemsDp,
                    itemPaddingDp = itemPaddingDp,
                    backgroundColor = backgroundColor,
                    renderItem = renderItem
                )
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
    imgSizeDp: Dp
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
                .padding(vertical = DP_16, horizontal = DP_20)
        ) {
            when {
                bitmap != null -> {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = IMAGE,
                        modifier = Modifier.size(imgSizeDp)
                    )
                }
                else -> {
                    LayoutUtils.getDrawableResourceId(LocalContext.current, PLACEHOLDER)
                        ?.let { painterResource(it) }?.let {
                            Image(
                                contentDescription = IMAGE,
                                painter = it,
                                modifier = Modifier.size(imgSizeDp)
                            )
                        }
                }
            }
            Spacer(Modifier.height(DP_16))
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
private fun ScrollableCard(backgroundColor: Color) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(backgroundColor)
    ) {
        Card(Modifier.fillMaxSize()) {}
    }
}