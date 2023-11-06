package com.nikoarap.composekitlibrary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.composables.CheckboxWithText
import com.nikoarap.compose_kit.composables.DetailedPieChart
import com.nikoarap.compose_kit.composables.SwitchButtonWithIcon
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.composekitlibrary.ui.theme.ComposeKitLibraryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeKitLibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   ViewContent()
                }
            }
        }
    }

    @Preview
    @Composable
    fun ViewContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {

            val segmentItems = listOf(
                PieChartSegment(label = "Water", value = 70, color = Color(0xFF6495ED), upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
                PieChartSegment(label = "Malt", value = 8, color = Color.Yellow, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
                PieChartSegment(label = "Hops", value = 4, color = Color.Green, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
                PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray))

                DetailedPieChart(
                    segmentItems = segmentItems,
                    backgroundColor = Color.White,
                    cardPaddingDp = 8.dp,
                    cardElevationDp = 8.dp,
                    chartOuterRadius = 80,
                    chartBarWidth = 80,
                    animDurationMs = 1600
                )

        }
    }
}




