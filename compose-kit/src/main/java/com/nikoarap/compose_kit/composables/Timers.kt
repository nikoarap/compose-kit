package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nikoarap.compose_kit.styles.DP_4
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.MILLIS_IN_SECOND
import com.nikoarap.compose_kit.utils.Constants.Companion.MINUTES_IN_HOUR
import com.nikoarap.compose_kit.utils.Constants.Companion.ONE_S_DELAY
import com.nikoarap.compose_kit.utils.Constants.Companion.SECONDS_IN_DAY
import com.nikoarap.compose_kit.utils.Constants.Companion.SECONDS_IN_HOUR
import com.nikoarap.compose_kit.utils.Constants.Companion.SECONDS_IN_MINUTE
import com.nikoarap.compose_kit.utils.Constants.Companion.ZERO
import kotlinx.coroutines.delay

/**
 * A composable function that displays a countdown timer for an event. This composable takes the
 * time remaining until an event starts in milliseconds and shows a timer that counts down to
 * the event's start time.
 *
 * The countdown timer calculates the days, hours, minutes, and seconds remaining until the event,
 * and displays them in a formatted string like "Xd, Xh, Xm, Xs" for days, hours, minutes, and
 * seconds, respectively. When the event has started, it displays the text value that was passed in the countdownZeroTextValue param.
 *
 * @param timeUntilEventStartInMs       The time remaining until the event starts in milliseconds.
 * @param countdownZeroTextValue        The text value when the timer has reached 0.
 * @param textColor                     Text color of the timer, while its counting
 * @param countdownZeroTextColor        Text color of the timer, when 0.
 */
@Composable
fun CountdownTimer(
    timeUntilEventStartInMs: Long,
    countdownZeroTextValue: String,
    textColor: Color,
    countdownZeroTextColor: Color
) {
    var timeLeft by remember { mutableLongStateOf(timeUntilEventStartInMs) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > ZERO) {
            delay(ONE_S_DELAY)
            timeLeft -= MILLIS_IN_SECOND
        }
    }

    val seconds = (timeLeft / MILLIS_IN_SECOND).toInt()
    val daysLeft = seconds / SECONDS_IN_DAY
    val hoursLeft = (seconds % SECONDS_IN_DAY) / SECONDS_IN_HOUR
    val minutesLeft = (seconds % SECONDS_IN_HOUR) / MINUTES_IN_HOUR
    val secondsLeft = seconds % SECONDS_IN_MINUTE

    val countdownValue = if (timeLeft > ZERO) {
        val daysString = if (daysLeft > ZERO) "${daysLeft}d, " else EMPTY
        val hoursString = if (hoursLeft > ZERO) "${hoursLeft}h, " else EMPTY
        val minutesString = if (minutesLeft > ZERO) "${minutesLeft}m, " else EMPTY
        String.format("$daysString$hoursString$minutesString%02ds", secondsLeft)
    } else {
        countdownZeroTextValue
    }
    Text(
        modifier = Modifier.padding(DP_4),
        text = countdownValue,
        style = MaterialTheme.typography.bodySmall,
        color = if (timeLeft > ZERO) textColor else countdownZeroTextColor,
    )
}