package com.simpleviralgamesassingment.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simpleviralgamesassingment.data.preferences.PreferenceProvider
import com.simpleviralgamesassingment.ui.viewmodel.ViewGeneratedImageViewModel

class ViewGeneratedImageModelFactory(
    private val preferenceProvider: PreferenceProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewGeneratedImageViewModel(preferenceProvider) as T
    }
}