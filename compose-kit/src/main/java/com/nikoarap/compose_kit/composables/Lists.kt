package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * A generic Composable that displays a scrollable list of items with a swipe-to-refresh functionality.
 *
 * This Composable provides a lazy-loading list of items that can be refreshed by swiping down. It accepts
 * a list of [items] to display, and a [renderItem] lambda to define how each item is rendered.
 *
 * @param items                 The list of items to display in the lazy-loading list.
 * @param refreshing            A boolean indicating whether the refresh indicator is in a refreshing state.
 * @param topPaddingDp          The top padding in DP for the content.
 * @param backgroundColor       The background color for the content.
 * @param contentColor          The content color for the swipe-to-refresh indicator.
 * @param onRefresh             A callback function to execute when the user triggers a refresh.
 * @param renderItem            A lambda that defines how each item in the list is rendered using a Composable.
 *
 * @param <T> The type of items in the list.
 */
@Composable
fun <T> LazyListWithSwipeRefresh(
    items: List<T>,
    refreshing: Boolean,
    topPaddingDp: Int,
    backgroundColor: Color,
    contentColor: Color,
    onRefresh: () -> Unit,
    renderItem: @Composable (T) -> Unit
) {
    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPaddingDp.dp)
            .background(backgroundColor),
        state = rememberSwipeRefreshState(refreshing),
        onRefresh = onRefresh,
        indicator = { state, trigger ->  SwipeRefreshIndicator(
            state = state,
            refreshTriggerDistance = trigger,
            contentColor = contentColor
        )},
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(backgroundColor)) {
                Card(Modifier.fillMaxSize()) {}
            }
            if (items.isNotEmpty()) {
                items.forEach { item ->
                    renderItem(item)
                }
            }
        }
    )
}