package com.simpleviralgamesassingment.ui.viewmodel

import com.simpleviralgamesassingment.common.BaseViewModel
import com.simpleviralgamesassingment.data.preferences.PreferenceProvider
import com.simpleviralgamesassingment.notifiers.Notify
import com.simpleviralgamesassingment.utils.clearList

class ViewGeneratedImageViewModel(
    private val preferenceProvider: PreferenceProvider
): BaseViewModel() {

    fun clearDogClick() {
        clearList(preferenceProvider)
        notifier.notify(Notify(CLEAR_DOGS, ""))
    }

    companion object {
        const val CLEAR_DOGS = "CLEAR_DOGS"
    }
}