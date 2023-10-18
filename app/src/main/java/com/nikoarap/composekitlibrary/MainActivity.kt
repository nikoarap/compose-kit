package com.nikoarap.composekitlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nikoarap.compose_kit.composables.ClickableProfileCard
import com.nikoarap.compose_kit.composables.ExtendedFABWithIcon
import com.nikoarap.compose_kit.composables.ImageFromPainterResource
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {
            val imagePainter = painterResource(id = R.drawable.cow)



            ClickableProfileCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.White,
                elevationDp = 4,
                contentPaddingDp = 16,
                title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac sem vel orci congue vehicula. Integer et nisl eu odio tincidunt fringilla. Praesent sed libero eget ligula bibendum tincidunt. Fusce in eros non odio ultricies suscipit. Vestibulum ut tristique erat. Aenean id dui nec elit pellentesque iaculis. Nunc nec risus id purus ullamcorper laoreet. Suspendisse potenti.",
                    subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac sem vel orci congue vehicula. Integer et nisl eu odio tincidunt fringilla. Praesent sed libero eget ligula bibendum tincidunt. Fusce in eros non odio ultricies suscipit. Vestibulum ut tristique erat. Aenean id dui nec elit pellentesque iaculis. Nunc nec risus id purus ullamcorper laoreet. Suspendisse potenti. Vivamus euismod, est sit amet laoreet fringilla, libero tortor volutpat quam, eu posuere sem quam vel libero.",
                    titleTypography = MaterialTheme.typography.headlineLarge,
                subtitleTypography = MaterialTheme.typography.bodyMedium,
                    painterResource = imagePainter,
                onClick = { toast.show() }
                        )
        }


    }




}




