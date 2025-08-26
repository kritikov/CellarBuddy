package com.example.cellarbuddy.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // this variable is used to guarantee that the method executes only once
    private var hasLoaded = false

    fun loadOnce(){
        if (!hasLoaded){
            hasLoaded = true
        }
    }
}