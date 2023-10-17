package com.nikoarap.composekitlibrary.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class MainViewModel(application: Application): AndroidViewModel(application) {

    val triggerOnClickEvent: MutableLiveData<Boolean> = MutableLiveData()

}