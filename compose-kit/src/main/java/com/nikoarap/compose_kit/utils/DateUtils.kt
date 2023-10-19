package com.nikoarap.compose_kit.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

class DateUtils {

    companion object {

        /**
         * Converts a timestamp to a formatted date string using a specified pattern and the device's default locale.
         *
         * @param timestamp             The timestamp to be converted to a date string.
         * @param pattern               The pattern to format the date string (e.g., "dd MMM yyyy").
         * @return                      A formatted date string representing the given timestamp in the specified pattern.
         */
        fun longToDateStr(timestamp: Long, pattern: String): String {
            val date: Date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Date(Instant.ofEpochMilli(timestamp).toEpochMilli())
            } else {
                Date(timestamp)
            }
            val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            return dateFormat.format(date)
        }
    }

}