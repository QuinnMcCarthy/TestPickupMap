package com.example.testpickupmap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val data: LiveData<Int> = MutableLiveData()
}