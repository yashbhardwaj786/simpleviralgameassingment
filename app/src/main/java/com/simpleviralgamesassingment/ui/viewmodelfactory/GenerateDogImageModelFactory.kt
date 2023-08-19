package com.simpleviralgamesassingment.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simpleviralgamesassingment.repository.GenerateDogRepository
import com.simpleviralgamesassingment.ui.viewmodel.GeneratedDogImageViewModel

class GenerateDogImageModelFactory(
    private val repository: GenerateDogRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GeneratedDogImageViewModel(repository) as T
    }
}