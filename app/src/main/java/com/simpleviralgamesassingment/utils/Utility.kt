package com.simpleviralgamesassingment.utils

import android.app.Activity
import android.app.Dialog

class Utility {
    companion object {
        private var progressInstance: Dialog? = null

        fun showProgressDialog(activity: Activity, isCancelable: Boolean = false) {
            progressInstance?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
            try {
                progressInstance = CustomProgressBar.show(activity, isCancelable)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun hideProgressDialog() {
            try {
                progressInstance?.let {
                    if (it.isShowing) {
                        it.dismiss()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
