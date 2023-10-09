package com.nikoarap.compose_kit.utils

import android.content.Context
import androidx.compose.ui.graphics.Color

class LayoutUtils {

    companion object {

        /**
         * Retrieves the resource identifier (ID) of a drawable resource based on its resource name.
         *
         * This method dynamically looks up the resource ID for the specified drawable resource name
         * within the given Android application context's package.
         *
         * @param context               The Android application context in which the resource lookup will be performed.
         * @param resourceName          The name of the drawable resource for which to obtain the resource ID.
         * @return                      The resource ID of the specified drawable resource, or 0 if the resource was not found.
         *
         * @throws SecurityException            If the caller does not have the necessary permissions to access resources.
         * @throws ClassNotFoundException       If the Android R class for the application package name cannot be found.
         * @throws NoSuchFieldException         If the specified resource name does not exist in the R.drawable class.
         * @throws IllegalAccessException       If access to the resource's ID field is not allowed.
         * @throws IllegalArgumentException     If the specified resource name is null or empty.
         * @throws Exception                    Any other exception that may occur during the resource lookup process.
         */
        fun getDrawableResourceId(context: Context, resourceName: String?): Int? {
            val packageName = context.packageName
            try {
                val clazz = Class.forName("$packageName.R\$drawable")
                return resourceName?.let { clazz.getField(it).getInt(null) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return 0
        }

        /**
         * Retrieves the appropriate color based on the checked state.
         *
         * This function returns the [checkedColor] if [isChecked] is `true`, indicating that the item
         * is in a checked or selected state. Otherwise, it returns the [uncheckedColor] to represent
         * the unchecked or unselected state.
         *
         * @param isChecked             A boolean flag indicating whether the item is in a checked state.
         * @param checkedColor          The color to use when the item is checked or selected.
         * @param uncheckedColor        The color to use when the item is unchecked or unselected.
         *
         * @return The color corresponding to the checked or unchecked state.
         */
        fun getCheckedColor(
            isChecked: Boolean,
            checkedColor: Color,
            uncheckedColor: Color
        ): Color {
            return if (isChecked) checkedColor else uncheckedColor
        }

    }

}