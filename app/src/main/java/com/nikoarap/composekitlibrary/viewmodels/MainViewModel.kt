package com.nikoarap.composekitlibrary.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY

open class MainViewModel(application: Application): AndroidViewModel(application) {

    val textValue: MutableState<String> = mutableStateOf(EMPTY)

}