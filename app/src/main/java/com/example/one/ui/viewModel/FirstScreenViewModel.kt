package com.example.one.ui.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor() : ViewModel() {

    fun isPalindrome(input: String): Boolean {
        val processedInput = input.replace("\\s".toRegex(), "").toLowerCase()
        return processedInput == processedInput.reversed()
    }
}