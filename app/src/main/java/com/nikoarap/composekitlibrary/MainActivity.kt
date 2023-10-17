package com.nikoarap.composekitlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.composables.StyledButtonWithTextRow
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

        StyledButtonWithTextRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            buttonModifier = Modifier.padding(8.dp),
                buttonText = "Click Me",
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
            backgroundColor = Color.Blue,
                contentColor = Color.White,
                textColor = Color.Black,
                typography = MaterialTheme.typography.bodyLarge,
            onClick = {
                toast.show()

            }
        )
    }




}




