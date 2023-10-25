package com.nikoarap.composekitlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.composables.CircularProgressBarWithText
import com.nikoarap.compose_kit.composables.DynamicSliderWithLabel
import com.nikoarap.compose_kit.composables.LinearDeterminateProgressBar
import com.nikoarap.compose_kit.composables.SliderWithLabel
import com.nikoarap.compose_kit.models.BottomAppBarAction
import com.nikoarap.compose_kit.models.NavBottomItem
import com.nikoarap.compose_kit.models.NavDrawerItem
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.compose_kit.models.TabItem
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_8
import com.nikoarap.composekitlibrary.ui.theme.ComposeKitLibraryTheme
import com.nikoarap.composekitlibrary.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeKitLibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   TestFunctions()
                }
            }
        }
    }






    @Preview
    @Composable
    fun TestFunctions() {
        val primaryColor = Color(0xFF6495ED)
        val secondaryColor = Color(0xff381E72)
        val toast1 = Toast.makeText(this, "Toast 1 Test", Toast.LENGTH_SHORT)
        val toast2 = Toast.makeText(this, "Toast 2 Test", Toast.LENGTH_SHORT)
        val toast3 = Toast.makeText(this, "Toast 3 Test", Toast.LENGTH_SHORT)
        val toast4 = Toast.makeText(this, "Toast 4 Test", Toast.LENGTH_SHORT)
        val imagePainter = painterResource(id = R.drawable.cow)
        val segmentItems = listOf(
            PieChartSegment(label = "Water", value = 70, color = Color(0xFF6495ED), upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Malt", value = 8, color = Color.Yellow, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Hops", value = 4, color = Color.Green, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
        )

        val navBottomAppBarActions = listOf(
            BottomAppBarAction(order = 0, iconResName = "ic_home", iconTintColor = Color.White, onClick = {toast1.show()}),
            BottomAppBarAction(order = 1, iconResName = "ic_settings", iconTintColor = Color.White, onClick = {toast2.show()}),
            BottomAppBarAction(order = 2, iconResName = "ic_search", iconTintColor = Color.White, onClick = {toast3.show()}),
        )

        val navBottomItems = listOf(
            NavBottomItem(order = 0, label = "Home", iconResName = "ic_add", tintColor = Color.White, selectedTintColor = secondaryColor, onSelected = { toast1.show() }),
            NavBottomItem(order = 1, label = "Search", iconResName = "ic_search", tintColor = Color.White, selectedTintColor = secondaryColor, onSelected = { toast2.show() }),
            NavBottomItem(order = 2, label = "Settings", iconResName = "ic_settings", tintColor = Color.White, selectedTintColor = secondaryColor, onSelected = {toast3.show()})
        )

        val navDrawerItems = listOf(
            NavDrawerItem(label = "Home", iconResName = "ic_home", onClick = {toast1.show()}),
            NavDrawerItem(label = "Settings", iconResName = "ic_settings", onClick = {toast2.show()}),
            NavDrawerItem(label = "Search", iconResName = "ic_search", onClick = {toast3.show()}),
            NavDrawerItem(label = "Add", iconResName = "ic_add", onClick = {toast4.show()}),
        )

        val tabItems = listOf(
            TabItem(0, "Tab One") { toast1.show() },
            TabItem(1, "Tab Two") { toast1.show() },
            TabItem(2, "Tab Three") { toast1.show() },
            TabItem(3, "Tab Four") { toast1.show() },
        )

        //            NavigationPage(
//                topBarTitle = "My app",
//                topBarTitleToCenter = false,
//                topBarTitleTypography = MaterialTheme.typography.titleMedium,
//                topBarColor = primaryColor,
//                topBarTitleColor = Color.White,
//                drawerOpenIconResName = "ic_menu",
//                drawerOpenIconTintColor = Color.White,
//                topBarEndIconResName = "ic_search",
//                topBarEndIconTintColor = Color.White,
//                drawerContainerColor = secondaryColor,
//                drawerTitle = "Navigation Drawer",
//                drawerTitleColor = Color.LightGray,
//                bottomAppBarContainerColor = primaryColor.copy(0.9f),
//                drawerTitleTypography = MaterialTheme.typography.labelLarge,
//                navDrawerItems = navDrawerItems,
//                bottomBarActions = navBottomAppBarActions,
//                fabIconResName = "ic_add",
//                fabBackgroundColor = secondaryColor,
//                fabIconTintColor = Color.White,
//                onTopBarEndIconClicked = { /TODO/ },
//                onFabClicked = { /TODO/ })
//                {
//                    //screen content (compose stuff here based on what should be displayed after taking an action)
//                }

//        NavigationPage(
//            topBarTitle = "My app",
//            topBarTitleToCenter = false,
//            topBarTitleTypography = MaterialTheme.typography.titleMedium,
//            topBarColor = primaryColor,
//            topBarTitleColor = Color.White,
//            drawerOpenIconResName = "ic_menu",
//            drawerOpenIconTintColor = Color.White,
//            topBarEndIconResName = "ic_search",
//            topBarEndIconTintColor = Color.White,
//            drawerContainerColor = secondaryColor,
//            drawerTitle = "Navigation Drawer",
//            drawerTitleColor = Color.LightGray,
//            drawerTitleTypography = MaterialTheme.typography.labelLarge,
//            navDrawerItems = navDrawerItems,
//            navBottomItems = navBottomItems,
//            bottomBarContainerColor = primaryColor,
//            selectedBottomBarItemIndex = 0,
//            fabIconResName = "ic_add",
//            fabBackgroundColor = secondaryColor,
//            fabIconTintColor = Color.White,
//            onTopBarEndIconClicked = { /TODO/ },
//            onFabClicked = { /TODO/ })
//        {
//            //screen content (compose stuff here based on what should be displayed after taking an action)
//        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {

//                 SliderWithLabel(
//                     sliderStartValue = 0f,
//                     sliderEndValue = 100f,
//                     sliderPosition = 5f,
//                     steps = 0,
//                     thumbColor = Color.Blue,
//                     disabledThumbColor = Color.Gray,
//                     activeTrackColor = Color.Green,
//                     inactiveTrackColor = Color.Gray,
//                     disabledActiveTrackColor = Color.LightGray,
//                     disabledInactiveTrackColor = Color.LightGray,
//                     activeTickColor = Color.Red,
//                     inactiveTickColor = Color.LightGray,
//                     disabledActiveTickColor = Color.Gray,
//                     disabledInactiveTickColor = Color.Gray,
//                     onSliderValueChange = { newValue ->
//                             // Handle the new value, e.g., update a ViewModel
//                         }
//                 )

                 DynamicSliderWithLabel(
                     sliderStartValue = 0f,
                     sliderEndValue = 100f,
                     sliderPosition = 50f,
                     thresholdOne = 30f,
                     thresholdTwo = 70f,
                     startColor = Color.Gray,
                     middleColor = Color.Yellow,
                     endColor = Color.Green,
                     steps = 0,
                     onSliderValueChange = { newValue ->
                             // Handle the new value, e.g., update a ViewModel
                         }
                         )

            LinearDeterminateProgressBar(
                      modifier = Modifier.fillMaxWidth().padding(DP_8.dp),
                     currentOperationsCount = 6,
                      totalOperations = 5,
                     isLoading = true
                         )

        }
    }
}




