package com.simpleviralgamesassingment.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simpleviralgamesassingment.ui.viewmodel.MainViewModel

class MainViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  MainViewModel() as T
    }
}