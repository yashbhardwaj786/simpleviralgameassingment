package com.simpleviralgamesassingment.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.simpleviralgamesassingment.common.BaseActivity
import com.simpleviralgamesassingment.common.BaseViewModel
import com.simpleviralgamesassingment.ui.viewmodelfactory.MainViewModelFactory
import com.simpleviralgamesassingment.R
import com.simpleviralgamesassingment.common.ModuleMaster.navigateToGenerateDogImageActivity
import com.simpleviralgamesassingment.common.ModuleMaster.navigateToViewGeneratedImageActivity
import com.simpleviralgamesassingment.data.preferences.PreferenceProvider
import com.simpleviralgamesassingment.databinding.ActivityMainBinding
import com.simpleviralgamesassingment.notifiers.Notify
import com.simpleviralgamesassingment.ui.viewmodel.MainViewModel
import com.simpleviralgamesassingment.ui.viewmodel.MainViewModel.Companion.GENERATE_DOG_CLICK
import com.simpleviralgamesassingment.ui.viewmodel.MainViewModel.Companion.VIEW_RECENT_DOG_CLICK
import com.simpleviralgamesassingment.utils.initializeList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : BaseActivity() {

    override val kodein by kodein()
    private lateinit var mainViewModel: MainViewModel
    private val factory by instance<MainViewModelFactory>()
    private val preferenceProvider by instance<PreferenceProvider>()
    private val binding: ActivityMainBinding by lazyBinding()
    override val dataBinding: Boolean = true
    override val layoutResource: Int = R.layout.activity_main
    override fun getViewModel(): BaseViewModel {
        return mainViewModel
    }

    override fun initializeViewModel() {
        mainViewModel =
            ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun setBindings() {
        binding.viewModel = mainViewModel
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onNotificationReceived(data: Notify) {
        when(data.identifier){
            GENERATE_DOG_CLICK -> {
                navigateToGenerateDogImageActivity(this)
            }
            VIEW_RECENT_DOG_CLICK -> {
                navigateToViewGeneratedImageActivity(this)
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeList(preferenceProvider)
    }
}