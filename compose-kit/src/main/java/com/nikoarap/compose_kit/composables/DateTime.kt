package com.nikoarap.compose_kit.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants.Companion.DATE_PICKER_ALPHA
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_12
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_24
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_6
import java.time.Instant

/**
 * A composable function that displays a styled date picker dialog for selecting a date. This dialog allows users
 * to pick a date from the calendar with customizable title, date validation, and theme.
 *
 * @param title                     The title to display at the top of the date picker dialog.
 * @param datePickerPaddingDp       The padding value in DP to apply to the date picker within the dialog.
 * @param showModeToggle            A flag indicating whether to show a mode toggle action in the date picker, allowing users to switch between date and year modes.
 * @param onDateConfirm             Callback function to execute when the user confirms the selected date.
 * @param onDismiss                 Callback function to execute when the dialog is dismissed.
 * @param darkTheme                 A boolean indicating whether to use a dark theme for the date picker. True for dark theme, False for the light one.
 *
 * Usage:
 *
 * // Example usage of the StyledDatePickerDialog
 * StyledDatePickerDialog(
 *     title = "Select a Date",
 *     datePickerPaddingDp = 16,
 *     showModeToggle = true,
 *     onDateConfirm = { /* Handle date confirmation here */ },
 *     onDismiss = { /* Handle dismissal here */ },
 *     darkTheme = false
 * )
 *
 * @see [DatePickerDialog]
 * @see [DatePicker]
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun StyledDatePickerDialog(
    title: String,
    datePickerPaddingDp: Int,
    showModeToggle: Boolean,
    onDateConfirm: () -> Unit,
    onDismiss: () -> Unit,
    darkTheme: Boolean
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    DatePickerDialog(
        shape = RoundedCornerShape(DP_6.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
            onDateConfirm()
        },
    ) {
        DatePicker(
            modifier = Modifier.padding(datePickerPaddingDp.dp),
            state = datePickerState,
            dateValidator = { timestamp ->
                // Disable all the days before today
                timestamp > Instant.now().toEpochMilli()
            },
            title = {
                Text(
                    modifier = Modifier.padding(PaddingValues(start = DP_24.dp, end = DP_12.dp, top = DP_16.dp)),
                    text = title
                )
            },
            headline = {
                // You need to look the datePickerState value
                Text(
                    modifier = Modifier.padding(PaddingValues(start = DP_24.dp, end = DP_12.dp, bottom = DP_12.dp)),
                    text = datePickerState.displayMode.toString()
                )
            },
            showModeToggle = showModeToggle, //  indicates if this DatePicker should show a mode toggle action that transforms it into a date input
            colors = if (darkTheme) {
                DatePickerDarkTheme()
            } else {
                DatePickerLightTheme()
            }
        )
    }
}

/**
 * Composable function to generate a light theme for a date picker based on the Material Design guidelines.
 *
 * @return A [DatePickerColors] object defining the color attributes for the light-themed date picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerLightTheme(): DatePickerColors {
    val primary = Color(android.graphics.Color.parseColor("#6750A4"))
    val onPrimary = Color(android.graphics.Color.parseColor("#FFFFFF"))
    val surface = Color(android.graphics.Color.parseColor("#ECE6F0"))
    val onSurface = Color(android.graphics.Color.parseColor("#1D1B20"))
    val onSurfaceVariant = Color(android.graphics.Color.parseColor("#49454F"))
    val secondaryContainer = Color(android.graphics.Color.parseColor("#E8DEF8"))
    val onSecondaryContainer = Color(android.graphics.Color.parseColor("#1D192B"))

    return DatePickerDefaults.colors(
        containerColor = surface,
        titleContentColor = onSurfaceVariant,
        headlineContentColor = onSurfaceVariant,
        weekdayContentColor = onSurface,
        subheadContentColor = onSurfaceVariant,
        yearContentColor = onSurfaceVariant,
        currentYearContentColor = primary,
        selectedYearContentColor = onPrimary,
        selectedYearContainerColor = primary,
        dayContentColor = onSurface,
        disabledDayContentColor = onSurface.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContentColor = onPrimary,
        disabledSelectedDayContentColor = onPrimary.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContainerColor = primary,
        disabledSelectedDayContainerColor = primary.copy(alpha = DATE_PICKER_ALPHA),
        todayContentColor = primary,
        todayDateBorderColor = primary,
        dayInSelectionRangeContentColor = onSecondaryContainer,
        dayInSelectionRangeContainerColor = secondaryContainer
    )
}

/**
 * Composable function to generate a dark theme for a date picker based on the Material Design guidelines.
 *
 * @return A [DatePickerColors] object defining the color attributes for the dark-themed date picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDarkTheme(): DatePickerColors {
    val primary = Color(android.graphics.Color.parseColor("#D0BCFF"))
    val onPrimary = Color(android.graphics.Color.parseColor("#381E72"))
    val surface = Color(android.graphics.Color.parseColor("#2B2930"))
    val onSurface = Color(android.graphics.Color.parseColor("#E6E0E9"))
    val onSurfaceVariant = Color(android.graphics.Color.parseColor("#CAC4D0"))
    val secondaryContainer = Color(android.graphics.Color.parseColor("#4A4458"))
    val onSecondaryContainer = Color(android.graphics.Color.parseColor("#E8DEF8"))

    return DatePickerDefaults.colors(
        containerColor = surface,
        titleContentColor = onSurfaceVariant,
        headlineContentColor = onSurfaceVariant,
        weekdayContentColor = onSurface,
        subheadContentColor = onSurfaceVariant,
        yearContentColor = onSurfaceVariant,
        currentYearContentColor = primary,
        selectedYearContentColor = onPrimary,
        selectedYearContainerColor = primary,
        dayContentColor = onSurface,
        disabledDayContentColor = onSurface.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContentColor = onPrimary,
        disabledSelectedDayContentColor = onPrimary.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContainerColor = primary,
        disabledSelectedDayContainerColor = primary.copy(alpha = DATE_PICKER_ALPHA),
        todayContentColor = primary,
        todayDateBorderColor = primary,
        dayInSelectionRangeContentColor = onSecondaryContainer,
        dayInSelectionRangeContainerColor = secondaryContainer
    )
}