package com.nikoarap.compose_kit.utils

import android.content.Context
import com.nikoarap.compose_kit.utils.Constants.Companion.PLACEHOLDER

class LayoutUtils {

    companion object {

        /**
         * Retrieves the resource identifier (ID) of a drawable resource based on its resource name.
         * This method dynamically looks up the resource ID based on the specified drawable resource name.
         *
         * @param resourceName                  The name of the drawable resource for which to obtain the resource ID.
         * @return                              The resource ID of the specified drawable resource, or 0 if the resource was not found.
         *
         * @throws SecurityException            If the caller does not have the necessary permissions to access resources.
         * @throws ClassNotFoundException       If the Android R class for the application package name cannot be found.
         * @throws NoSuchFieldException         If the specified resource name does not exist in the R.drawable class.
         * @throws IllegalAccessException       If access to the resource's ID field is not allowed.
         * @throws IllegalArgumentException     If the specified resource name is null or empty.
         * @throws Exception                    Any other exception that may occur during the resource lookup process.
         */
        fun getDrawableResourceId(
            context: Context,
            resourceName: String?
        ): Int? {
            val packageName = context.packageName
            return try {
                val clazz = Class.forName("$packageName.R\$drawable")
                resourceName?.let { clazz.getField(it).getInt(null) }
            } catch (e: Exception) {
                e.printStackTrace()
                val clazz = Class.forName("$packageName.R\$drawable")
                resourceName?.let { clazz.getField(PLACEHOLDER).getInt(null) }
            }
        }
    }

}