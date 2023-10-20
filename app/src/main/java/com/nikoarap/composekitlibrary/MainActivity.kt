package com.nikoarap.composekitlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.nikoarap.compose_kit.composables.StyledEditTextField
import com.nikoarap.compose_kit.composables.StyledEditTextFieldRow
import com.nikoarap.compose_kit.composables.StyledTextFieldRow
import com.nikoarap.compose_kit.models.PieChartSegment
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
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
        val imagePainter = painterResource(id = R.drawable.cow)
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
            val textValueState by remember { mutableStateOf(mainViewModel.textValue) }


            StyledEditTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textValue = "This is an edit text field",
                label = "Edit Text Field",
                textColor = Color.Black,
                disabledTextColor = Color.Gray,
                backgroundColor = Color.White,
                cursorColor = Color.Blue,
                errorCursorColor = Color.Red,
                iconTintColor = Color.Gray,
                isReadOnly = false,
                isError = false,
                inputType = VisualTransformation.None,
                onClick = {
                 // Handle text field click
                 },
                onClear = {
                // Handle clear button click
                }
            )
        }
    }
}




