package com.nikoarap.composekitlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.composables.ClickableContactCard
import com.nikoarap.compose_kit.composables.DetailedPieChart
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.compose_kit.utils.Constants
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
        val toast = Toast.makeText(this, "OnClick works", Toast.LENGTH_SHORT)


//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//
//                .background(Color.White),
//        ) {
//            val imagePainter = painterResource(id = R.drawable.cow)
//
//
//            ClickableContactCard(
//                    modifier = Modifier.fillMaxWidth(),
//                borderStrokeWidthDp = 1,
//                    borderStrokeColor = Color.Gray,
//                    backgroundColor = Color.White,
//                elevationDp = 4,
//                contentPaddingDp = 16,
//                    title = "John Doe",
//                    subtitle = "Front-End Developer",
//                    titleTypography = MaterialTheme.typography.bodyMedium,
//                subtitleTypography = MaterialTheme.typography.bodySmall,
//
//                painterResource = painterResource(id = R.drawable.cow),
//                    onClick = { /* Handle card click */ }
//            )
//        }

        val items = listOf(
            PieChartSegment(label = "Water", value = 70, color = Color(0xFF6495ED), upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Malt", value = 8, color = Color.Yellow, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Hops", value = 4, color = Color.Green, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),

            )





        DetailedPieChart(
            segmentItems = items,
            backGroundColor = Color.White,
            topPaddingDp = 0,
            bottomPaddingDp = 0,
            chartOuterRadius = 80,
            chartBarWidth = 80,
            animDurationMs = 1600
        )


    }




}




