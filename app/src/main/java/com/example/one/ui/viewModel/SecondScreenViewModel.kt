package com.example.one.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SecondScreenViewModel @Inject constructor() : ViewModel() {
    var selectedNameValue by mutableStateOf<String?>("Selected User Name")
}

