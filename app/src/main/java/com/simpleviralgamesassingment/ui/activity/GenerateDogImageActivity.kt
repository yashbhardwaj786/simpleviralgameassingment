package com.simpleviralgamesassingment.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.simpleviralgamesassingment.R
import com.simpleviralgamesassingment.common.BaseActivity
import com.simpleviralgamesassingment.common.BaseViewModel
import com.simpleviralgamesassingment.data.model.DogBreedResult
import com.simpleviralgamesassingment.data.preferences.PreferenceProvider
import com.simpleviralgamesassingment.databinding.ActivityGenerateDogImageBinding
import com.simpleviralgamesassingment.notifiers.Notify
import com.simpleviralgamesassingment.ui.viewmodel.GeneratedDogImageViewModel
import com.simpleviralgamesassingment.ui.viewmodel.GeneratedDogImageViewModel.Companion.DOG_IMAGES_DATA
import com.simpleviralgamesassingment.ui.viewmodel.GeneratedDogImageViewModel.Companion.GENERATE_DOG_IMAGES_CLICK
import com.simpleviralgamesassingment.ui.viewmodelfactory.GenerateDogImageModelFactory
import com.simpleviralgamesassingment.utils.Constant.Companion.ON_FAILURE
import com.simpleviralgamesassingment.utils.showToast
import com.simpleviralgamesassingment.utils.updateList
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class GenerateDogImageActivity : BaseActivity() {
    override val kodein by kodein()
    private lateinit var generatedDogImageViewModel: GeneratedDogImageViewModel
    private val factory by instance<GenerateDogImageModelFactory>()
    private val preferenceProvider by instance<PreferenceProvider>()
    private val binding: ActivityGenerateDogImageBinding by lazyBinding()
    override val dataBinding: Boolean = true
    override val layoutResource: Int = R.layout.activity_generate_dog_image
    override fun getViewModel(): BaseViewModel {
        return generatedDogImageViewModel
    }

    override fun initializeViewModel() {
        generatedDogImageViewModel =
            ViewModelProvider(this, factory)[GeneratedDogImageViewModel::class.java]
    }

    override fun setBindings() {
        binding.viewModel = generatedDogImageViewModel
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onNotificationReceived(data: Notify) {
        when(data.identifier){
            GENERATE_DOG_IMAGES_CLICK -> {
                generatedDogImageViewModel.generateImage(this)
            }

            ON_FAILURE -> {
                val errorMessage  = data.arguments[0] as String
                showToast(this, errorMessage)
            }

            DOG_IMAGES_DATA -> {
                val dogBreed = data.arguments[0] as DogBreedResult
                dogBreed.let {
                    if (it.status.lowercase(Locale.ENGLISH) == "success") {
                        updateList(it.message, preferenceProvider)
                        binding.data = it
                    } else {
                        showToast(this, "Something went wrong!")
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBar(resources.getString(R.string.generate_dog))
    }
}