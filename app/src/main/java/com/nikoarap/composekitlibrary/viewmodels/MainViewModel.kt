package com.nikoarap.composekitlibrary.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class MainViewModel(application: Application): AndroidViewModel(application) {

    val textValue: MutableState<String> = mutableStateOf("Original Text Field Value")

}