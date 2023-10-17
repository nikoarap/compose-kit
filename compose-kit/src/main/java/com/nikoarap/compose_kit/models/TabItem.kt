package com.nikoarap.compose_kit.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.nikoarap.compose_kit.utils.Constants

data class TabItem(
    var order: Int = 0,
    var label: String = Constants.EMPTY,
    var labelTypography: TextStyle = TextStyle.Default,
    var selectedColor: Color = Color.Blue,
    var unselectedColor: Color = Color.Black,
    var onClick: () -> Unit,
)