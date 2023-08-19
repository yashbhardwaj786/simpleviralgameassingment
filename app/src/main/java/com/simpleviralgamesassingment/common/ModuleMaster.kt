package com.simpleviralgamesassingment.common

import android.content.Context
import android.content.Intent
import com.simpleviralgamesassingment.R
import com.simpleviralgamesassingment.ui.activity.GenerateDogImageActivity
import com.simpleviralgamesassingment.ui.activity.MainActivity
import com.simpleviralgamesassingment.ui.activity.ViewGeneratedImageActivity

object ModuleMaster {

    fun navigateToGenerateDogImageActivity(context: Context) {
        Intent(context, GenerateDogImageActivity::class.java).also {
            context.startActivity(it)
            (context as MainActivity).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }

    fun navigateToViewGeneratedImageActivity(context: Context) {
        Intent(context, ViewGeneratedImageActivity::class.java).also {
            context.startActivity(it)
            (context as MainActivity).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }
}
