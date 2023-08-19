package com.simpleviralgamesassingment.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.simpleviralgamesassingment.common.BaseViewModel
import com.simpleviralgamesassingment.data.model.DogBreedResult
import com.simpleviralgamesassingment.notifiers.Notify
import com.simpleviralgamesassingment.repository.GenerateDogRepository
import com.simpleviralgamesassingment.utils.ApiException
import com.simpleviralgamesassingment.utils.Constant
import com.simpleviralgamesassingment.utils.EWException
import com.simpleviralgamesassingment.utils.isInternetAvailable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class GeneratedDogImageViewModel(
    private val repository: GenerateDogRepository
    ): BaseViewModel() {

    fun generateDogImageClick(){
        notifier.notify(Notify(GENERATE_DOG_IMAGES_CLICK, ""))
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    fun generateImage(context: Context) {
        notifier.notify(Notify(Constant.ON_STARTED, ""))
        if (isInternetAvailable(context)) {
            viewModelScope.launch {
                try {
                    val response = repository.generateDogImages()
                    response.let {
                        if (it is DogBreedResult) {
                            notifier.notify(Notify(DOG_IMAGES_DATA, it))
                            return@launch
                        } else if (it is EWException) {
                            it.printStackTrace()
                            notifier.notify(Notify(Constant.ON_FAILURE, it.localizedMessage))
                        }
                    }
                }catch (ex: ApiException) {
                    hideProgress()
                    ex.printStackTrace()
                    notifier.notify(Notify(Constant.ON_FAILURE, ex.localizedMessage))
                }
            }
        } else {
            notifier.notify(Notify(Constant.ON_FAILURE, Constant.INTERNET_ISSUE_DESCRIPTION))
        }
    }

    companion object {
        const val DOG_IMAGES_DATA = "DOG_IMAGES_DATA"
        const val GENERATE_DOG_IMAGES_CLICK = "GENERATE_DOG_IMAGES_CLICK"
    }

}