package com.nikoarap.composekitlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikoarap.compose_kit.composables.StyledTabRowWithIndicator
import com.nikoarap.compose_kit.models.NavBottomItem
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.compose_kit.models.TabItem
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
        val toast1 = Toast.makeText(this, "go back", Toast.LENGTH_SHORT)
        val toast2 = Toast.makeText(this, "open settings", Toast.LENGTH_SHORT)
        val imagePainter = painterResource(id = R.drawable.cow)
        val segmentItems = listOf(
            PieChartSegment(label = "Water", value = 70, color = Color(0xFF6495ED), upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Malt", value = 8, color = Color.Yellow, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Hops", value = 4, color = Color.Green, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
        )

        val navItems = listOf(
            NavBottomItem(0,"Home", "ic_add", Color.Gray, Color.Blue) { toast1.show() },
            NavBottomItem(1,"Search", "ic_search", Color.Gray, Color.Blue) { toast2.show() },
            NavBottomItem(2, "Settings", "ic_settings", Color.Gray, Color.Blue) { toast1.show() }
        )

        val tabItems = listOf(
            TabItem(0, "Tab One") { toast1.show() },
            TabItem(1, "Tab Two") { toast1.show() },
            TabItem(2, "Tab Three") { toast1.show() },
            TabItem(3, "Tab Four") { toast1.show() },
        )






        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),

        ) {
            StyledTabRowWithIndicator(
                items = tabItems,
                selectedTabIndex = 0,
                tabRowContainerColor = Color(0xffD0BCFF),
                selectedTabColor = Color(0xff381E72),
                unselectedTabColor = Color.White,
                labelTypography = MaterialTheme.typography.labelMedium
            )
        }
    }
}




