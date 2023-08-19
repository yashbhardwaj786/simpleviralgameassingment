package com.simpleviralgamesassingment.ui.viewmodel

import com.simpleviralgamesassingment.common.BaseViewModel
import com.simpleviralgamesassingment.notifiers.Notify

class MainViewModel(): BaseViewModel() {

    fun onGenerateDogButtonClick() {
        notifier.notify(Notify(GENERATE_DOG_CLICK, ""))
    }

    fun onMyGenerateDogsButtonClick() {
        notifier.notify(Notify(VIEW_RECENT_DOG_CLICK, ""))
    }

    companion object {
        const val GENERATE_DOG_CLICK = "GENERATE_DOG_CLICK"
        const val VIEW_RECENT_DOG_CLICK = "VIEW_RECENT_DOG_CLICK"
    }

}