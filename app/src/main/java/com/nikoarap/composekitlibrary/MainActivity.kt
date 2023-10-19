package com.nikoarap.composekitlibrary

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikoarap.compose_kit.composables.ClickableContactCard
import com.nikoarap.compose_kit.composables.DetailedPieChart
import com.nikoarap.compose_kit.composables.StyledDatePickerDialog
import com.nikoarap.compose_kit.models.PieChartSegment
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




        val segmentItems = listOf(
            PieChartSegment(label = "Water", value = 70, color = Color(0xFF6495ED), upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Malt", value = 8, color = Color.Yellow, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Hops", value = 4, color = Color.Green, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
            PieChartSegment(label = "Yeast", value = 18, color = Color.Gray, upperTextTypography = MaterialTheme.typography.bodyMedium, MaterialTheme.typography.bodySmall, Color.Black, Color.LightGray),
        )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            val imagePainter = painterResource(id = R.drawable.cow)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                StyledDatePickerDialog(
                       title = "Select a Date",
                         titleTypography = MaterialTheme.typography.bodyMedium,
                        datePattern = "dd MMM yyyy",
                        dateTextTypography = MaterialTheme.typography.headlineMedium,
                         minimumYear = 1950,
                         maximumYear = 2050,
                         onDateConfirm = { /* Handle date confirmation here */ },
                         onDismiss = { /* Handle dismissal here */ },
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false,
                         darkTheme = false
                             )
            }


        }


    }




}




