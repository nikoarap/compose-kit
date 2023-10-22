package com.nikoarap.compose_kit.models

import com.nikoarap.compose_kit.utils.Constants

data class TabItem(
    var order: Int = 0,
    var label: String = Constants.EMPTY,
    var onClick: () -> Unit,
)