package com.nikoarap.compose_kit.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.nikoarap.compose_kit.styles.DP_12
import com.nikoarap.compose_kit.styles.DP_16
import com.nikoarap.compose_kit.styles.DP_24
import com.nikoarap.compose_kit.styles.DP_6
import com.nikoarap.compose_kit.styles.datePickerButtonColorDark
import com.nikoarap.compose_kit.styles.datePickerButtonColorLight
import com.nikoarap.compose_kit.styles.datePickerDarkOnPrimary
import com.nikoarap.compose_kit.styles.datePickerDarkOnSecondaryContainer
import com.nikoarap.compose_kit.styles.datePickerDarkOnSurface
import com.nikoarap.compose_kit.styles.datePickerDarkOnSurfaceVariant
import com.nikoarap.compose_kit.styles.datePickerDarkPrimary
import com.nikoarap.compose_kit.styles.datePickerDarkSecondaryContainer
import com.nikoarap.compose_kit.styles.datePickerDarkSurface
import com.nikoarap.compose_kit.styles.datePickerLightOnPrimary
import com.nikoarap.compose_kit.styles.datePickerLightOnSecondaryContainer
import com.nikoarap.compose_kit.styles.datePickerLightOnSurface
import com.nikoarap.compose_kit.styles.datePickerLightOnSurfaceVariant
import com.nikoarap.compose_kit.styles.datePickerLightPrimary
import com.nikoarap.compose_kit.styles.datePickerLightSecondaryContainer
import com.nikoarap.compose_kit.styles.datePickerLightSurface
import com.nikoarap.compose_kit.utils.Constants.Companion.DATE_PICKER_ALPHA
import com.nikoarap.compose_kit.utils.DateUtils
import kotlinx.coroutines.launch
import java.time.Instant

/**
 * A composable function that displays a styled date picker dialog for selecting a date. This dialog allows users
 * to pick a date from the calendar with customizable title, date validation, and theme.
 *
 * @param toShowPicker              A boolean value indicating whether to display the date picker.
 * @param title                     The title to display at the top of the date picker dialog.
 * @param title                     Typography to be set to the title.
 * @param datePattern               String pattern to determine the final output of the date string display value. (e.g. datePattern = "dd MMM yyyy" -> 19 Oct 2023)
 * @param dateTextTypography        Typography to be set to the date string text.
 * @param minimumYear               Integer to determine the minimum year value in the year selector.
 * @param maximumYear               Integer to determine the maximum year value in the year selector.
 * @param positiveButtonText        The text for the positive (confirm) button.
 * @param negativeButtonText        The text for the negative (dismiss) button.
 * @param onDateConfirm             Callback function to execute when the user confirms the selected date.
 * @param onDismiss                 Callback function to execute when the dialog is dismissed.
 * @param dismissOnBackPress        Boolean to determine if the dialog should be dismissed when the back button is pressed.
 * @param dismissOnClickOutside     Boolean to determine if the dialog should be dismissed when clicked outside.
 * @param buttonTextAllCaps         Whether to convert button text to all uppercase.
 * @param darkTheme                 A boolean indicating whether to use a dark theme for the date picker. True for dark theme, False for the light one.
 *
 * Usage:
 * ```
 * // Example usage of the StyledDatePickerDialog
 * StyledDatePickerDialog(
 *     toShowPicker = true,
 *     title = "Select a Date",
 *     titleTypography = MaterialTheme.typography.bodyMedium,
 *     datePattern = "dd MMM yyyy",
 *     dateTextTypography = MaterialTheme.typography.headlineMedium,
 *     minimumYear = 1950,
 *     maximumYear = 2050,
 *     positiveButtonText = "set",
 *     negativeButtonText = "dismiss",
 *     onDateConfirm = { /* Handle date confirmation here */ },
 *     onDismiss = { /* Handle dismissal here */ },
 *     dismissOnBackPress = true,
 *     dismissOnClickOutside = false,
 *     buttonTextAllCaps = true,
 *     darkTheme = false
 * )
 * ```
 *
 * @see [DatePickerDialog]
 * @see [DatePicker]
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StyledDatePickerDialog(
    toShowPicker: Boolean,
    title: String,
    titleTypography: TextStyle,
    datePattern: String,
    dateTextTypography: TextStyle,
    minimumYear: Int,
    maximumYear: Int,
    positiveButtonText: String,
    negativeButtonText: String,
    onDateConfirm: () -> Unit,
    onDismiss: () -> Unit,
    dismissOnBackPress: Boolean,
    dismissOnClickOutside: Boolean,
    buttonTextAllCaps: Boolean,
    darkTheme: Boolean
) {
    var showPicker by remember { mutableStateOf(toShowPicker) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli(), yearRange = IntRange(minimumYear, maximumYear))
    val dateString = datePickerState.selectedDateMillis?.let { DateUtils.longToDateStr(it, datePattern) }

    if (toShowPicker) {
        DatePickerDialog(
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            ),
            shape = RoundedCornerShape(DP_6),
            onDismissRequest = onDismiss,
            confirmButton = {
                Text(
                    modifier = Modifier
                        .padding(DP_12)
                        .clickable {
                            showPicker = false
                            onDateConfirm()
                         },
                    text = if (buttonTextAllCaps) positiveButtonText.uppercase() else positiveButtonText,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (darkTheme) datePickerButtonColorDark else datePickerButtonColorLight
                )
            },
            dismissButton = {
                Text(
                    modifier = Modifier
                        .padding(DP_12)
                        .clickable {
                            showPicker = false
                            onDismiss()
                        },
                    text = if (buttonTextAllCaps) negativeButtonText.uppercase() else negativeButtonText,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (darkTheme) datePickerButtonColorDark else datePickerButtonColorLight
                )
            },
            colors = if (darkTheme) datePickerDarkTheme() else datePickerLightTheme()
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        modifier = Modifier.padding(PaddingValues(start = DP_24, end = DP_12, top = DP_16)),
                        text = title,
                        style = titleTypography
                    )
                },
                headline = {
                    dateString?.let {
                        Text(
                            modifier = Modifier.padding(PaddingValues(start = DP_24, end = DP_12, bottom = DP_12)),
                            style = dateTextTypography,
                            text = it
                        )
                    }
                },
                colors = if (darkTheme) datePickerDarkTheme() else datePickerLightTheme()
            )
        }
    }
}

/**
 * Composable function to generate a light theme for a date picker based on the Material Design guidelines.
 *
 * @return A [DatePickerColors] object defining the color attributes for the light-themed date picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun datePickerLightTheme(): DatePickerColors {
    return DatePickerDefaults.colors(
        containerColor = datePickerLightSurface,
        titleContentColor = datePickerLightOnSurfaceVariant,
        headlineContentColor = datePickerLightOnSurfaceVariant,
        weekdayContentColor = datePickerLightOnSurface,
        subheadContentColor = datePickerLightOnSurfaceVariant,
        yearContentColor = datePickerLightOnSurfaceVariant,
        currentYearContentColor = datePickerLightPrimary,
        selectedYearContentColor = datePickerLightOnPrimary,
        selectedYearContainerColor = datePickerLightPrimary,
        dayContentColor = datePickerLightOnSurface,
        disabledDayContentColor = datePickerLightOnSurface.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContentColor = datePickerLightOnPrimary,
        disabledSelectedDayContentColor = datePickerLightOnPrimary.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContainerColor = datePickerLightPrimary,
        disabledSelectedDayContainerColor = datePickerLightPrimary.copy(alpha = DATE_PICKER_ALPHA),
        todayContentColor = datePickerLightPrimary,
        todayDateBorderColor = datePickerLightPrimary,
        dayInSelectionRangeContentColor = datePickerLightOnSecondaryContainer,
        dayInSelectionRangeContainerColor = datePickerLightSecondaryContainer
    )
}

/**
 * Composable function to generate a dark theme for a date picker based on the Material Design guidelines.
 *
 * @return A [DatePickerColors] object defining the color attributes for the dark-themed date picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun datePickerDarkTheme(): DatePickerColors {
    return DatePickerDefaults.colors(
        containerColor = datePickerDarkSurface,
        titleContentColor = datePickerDarkOnSurfaceVariant,
        headlineContentColor = datePickerDarkOnSurfaceVariant,
        weekdayContentColor = datePickerDarkOnSurface,
        subheadContentColor = datePickerDarkOnSurfaceVariant,
        yearContentColor = datePickerDarkOnSurfaceVariant,
        currentYearContentColor = datePickerDarkPrimary,
        selectedYearContentColor = datePickerDarkOnPrimary,
        selectedYearContainerColor = datePickerDarkPrimary,
        dayContentColor = datePickerDarkOnSurface,
        disabledDayContentColor = datePickerDarkOnSurface.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContentColor = datePickerDarkOnPrimary,
        disabledSelectedDayContentColor = datePickerDarkOnPrimary.copy(alpha = DATE_PICKER_ALPHA),
        selectedDayContainerColor = datePickerDarkPrimary,
        disabledSelectedDayContainerColor = datePickerDarkPrimary.copy(alpha = DATE_PICKER_ALPHA),
        todayContentColor = datePickerDarkPrimary,
        todayDateBorderColor = datePickerDarkPrimary,
        dayInSelectionRangeContentColor = datePickerDarkOnSecondaryContainer,
        dayInSelectionRangeContainerColor = datePickerDarkSecondaryContainer
    )
}

/**
 * A composable function that displays a styled time picker dialog for selecting a time.
 * This dialog allows users to pick a time and offers various customization options.
 *
 * @param toShowPicker              A boolean value indicating whether to display the time picker.
 * @param dialogBackgroundColor     The background color of the dialog.
 * @param timePickerPaddingDp       The padding in DP to apply to the time picker.
 * @param onTimeConfirm             A callback function to execute when the user confirms the selected time.
 * @param onDismiss                 A callback function to execute when the dialog is dismissed.
 * @param darkTheme                 A boolean indicating whether to use a dark or light theme for the time picker.
 *
 * // Example usage of the StyledTimePickerDialog
 * ```
 * StyledTimePickerDialog(
 *     toShowPicker = true,
 *     dialogBackgroundColor = Color.White,
 *     timePickerPaddingDp = 16.dp,
 *     onTimeConfirm = {
 *         // Handle time confirmation here
 *     },
 *     onDismiss = {
 *         // Handle dismissal here
 *     },
 *     darkTheme = false
 * )
 * ```
 *
 * @see AlertDialog
 * @see TimePicker
 * @see TimeInput
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledTimePickerDialog(
    toShowPicker: Boolean,
    dialogBackgroundColor: Color,
    timePickerPaddingDp: Dp,
    onTimeConfirm: () -> Unit,
    onDismiss: () -> Unit,
    darkTheme: Boolean
) {
    var showPicker by remember { mutableStateOf(toShowPicker) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    var selectedHour by remember { mutableIntStateOf(0) }
    var selectedMinute by remember { mutableIntStateOf(0) }
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    if (showPicker) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = dialogBackgroundColor,
                    shape = RoundedCornerShape(size = 12.dp)
                ),
            onDismissRequest = { onDismiss() }
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray.copy(alpha = 0.3f))
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (configuration.screenHeightDp > 400) {
                    TimePicker(
                        state = timePickerState,
                        modifier = Modifier.padding(timePickerPaddingDp),
                        colors = if (darkTheme) timePickerDarkTheme() else timePickerLightTheme()
                    )
                } else {
                    TimeInput(
                        state = timePickerState,
                        modifier = Modifier.padding(timePickerPaddingDp),
                        colors = if (darkTheme) timePickerDarkTheme() else timePickerLightTheme(),
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        showPicker = false
                        onDismiss()
                    }) {
                        Text(text = "Dismiss")
                    }
                    TextButton(
                        onClick = {
                            showPicker = false
                            selectedHour = timePickerState.hour
                            selectedMinute = timePickerState.minute
                            snackScope.launch {
                                snackState.showSnackbar("Entered time: Hours:" + timePickerState.hour +", Minutes: " + timePickerState.minute)
                            }
                            onTimeConfirm()
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}

/**
 * Composable function to generate a light theme for a time picker based on the Material Design guidelines.
 *
 * @return A [TimePickerColors] object defining the color attributes for the light-themed time picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun timePickerLightTheme(): TimePickerColors {
    val primary = Color(android.graphics.Color.parseColor("#6750A4"))
    val primaryContainer = Color(android.graphics.Color.parseColor("#EADDFF"))
    val onPrimaryContainer = Color(android.graphics.Color.parseColor("#21005D"))
    val onPrimary = Color(android.graphics.Color.parseColor("#FFFFFF"))
    val surface = Color(android.graphics.Color.parseColor("#ECE6F0"))
    val surfaceContainer = Color(android.graphics.Color.parseColor("#E6E0E9"))
    val onSurface = Color(android.graphics.Color.parseColor("#1D1B20"))
    val onSurfaceVariant = Color(android.graphics.Color.parseColor("#49454F"))
    val tertiaryContainer = Color(android.graphics.Color.parseColor("#FFD8E4"))
    val onTertiaryContainer = Color(android.graphics.Color.parseColor("#31111D"))
    val outline = Color(android.graphics.Color.parseColor("#79747E"))

    return TimePickerDefaults.colors(
        clockDialColor = surfaceContainer,
        clockDialSelectedContentColor = onPrimary,
        clockDialUnselectedContentColor = onSurface,
        selectorColor = primary,
        containerColor = surface,
        periodSelectorBorderColor = outline,
        periodSelectorSelectedContainerColor = tertiaryContainer,
        periodSelectorUnselectedContainerColor = onSurface,
        periodSelectorSelectedContentColor = onTertiaryContainer,
        periodSelectorUnselectedContentColor = onSurfaceVariant,
        timeSelectorSelectedContainerColor = primaryContainer,
        timeSelectorUnselectedContainerColor = surfaceContainer,
        timeSelectorSelectedContentColor = onPrimaryContainer,
        timeSelectorUnselectedContentColor = onSurface
    )
}

/**
 * Composable function to generate a dark theme for a time picker based on the Material Design guidelines.
 *
 * @return A [TimePickerColors] object defining the color attributes for the dark-themed time picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun timePickerDarkTheme(): TimePickerColors {
    val primary = Color(android.graphics.Color.parseColor("#D0BCFF"))
    val primaryContainer = Color(android.graphics.Color.parseColor("#4F378B"))
    val onPrimaryContainer = Color(android.graphics.Color.parseColor("#EADDFF"))
    val onPrimary = Color(android.graphics.Color.parseColor("#381E72"))
    val surface = Color(android.graphics.Color.parseColor("#2B2930"))
    val surfaceContainer = Color(android.graphics.Color.parseColor("#36343B"))
    val onSurface = Color(android.graphics.Color.parseColor("#E6E0E9"))
    val onSurfaceVariant = Color(android.graphics.Color.parseColor("#CAC4D0"))
    val tertiaryContainer = Color(android.graphics.Color.parseColor("#633B48"))
    val onTertiaryContainer = Color(android.graphics.Color.parseColor("#FFD8E4"))
    val outline = Color(android.graphics.Color.parseColor("#938F99"))

    return TimePickerDefaults.colors(
        clockDialColor = surfaceContainer,
        clockDialSelectedContentColor = onPrimary,
        clockDialUnselectedContentColor = onSurface,
        selectorColor = primary,
        containerColor = surface,
        periodSelectorBorderColor = outline,
        periodSelectorSelectedContainerColor = tertiaryContainer,
        periodSelectorUnselectedContainerColor = onSurface,
        periodSelectorSelectedContentColor = onTertiaryContainer,
        periodSelectorUnselectedContentColor = onSurfaceVariant,
        timeSelectorSelectedContainerColor = primaryContainer,
        timeSelectorUnselectedContainerColor = surfaceContainer,
        timeSelectorSelectedContentColor = onPrimaryContainer,
        timeSelectorUnselectedContentColor = onSurface
    )
}