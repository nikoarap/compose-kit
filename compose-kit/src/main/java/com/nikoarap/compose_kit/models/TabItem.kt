package com.nikoarap.compose_kit.models

import com.nikoarap.compose_kit.utils.Constants

/**
 * Data class representing an item in a tab row.
 *
 * @property order          The order in which this tab item should appear.
 * @property label          The label or text associated with the tab item.
 * @property onClick        The lambda to be invoked when this tab item is clicked.
 */
data class TabItem(
    val order: Int = 0,
    val label: String = Constants.EMPTY,
    val onClick: () -> Unit,
)