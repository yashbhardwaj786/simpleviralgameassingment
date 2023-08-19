package com.simpleviralgamesassingment.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.simpleviralgamesassingment.R
import com.simpleviralgamesassingment.common.BaseActivity
import com.simpleviralgamesassingment.common.BaseViewModel
import com.simpleviralgamesassingment.databinding.ActivityViewGeneratedImageBinding
import com.simpleviralgamesassingment.notifiers.Notify
import com.simpleviralgamesassingment.ui.viewmodel.ViewGeneratedImageViewModel
import com.simpleviralgamesassingment.ui.viewmodel.ViewGeneratedImageViewModel.Companion.CLEAR_DOGS
import com.simpleviralgamesassingment.ui.viewmodelfactory.ViewGeneratedImageModelFactory
import com.simpleviralgamesassingment.utils.BindingUtil
import com.simpleviralgamesassingment.utils.displayList
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ViewGeneratedImageActivity : BaseActivity() {

    override val kodein by kodein()
    private lateinit var viewGeneratedImageViewModel: ViewGeneratedImageViewModel
    private val factory by instance<ViewGeneratedImageModelFactory>()
    private val binding: ActivityViewGeneratedImageBinding by lazyBinding()
    override val dataBinding: Boolean = true
    override val layoutResource: Int = R.layout.activity_view_generated_image
    override fun getViewModel(): BaseViewModel {
        return viewGeneratedImageViewModel
    }

    override fun initializeViewModel() {
        viewGeneratedImageViewModel =
            ViewModelProvider(this, factory)[ViewGeneratedImageViewModel::class.java]
    }

    override fun setBindings() {
        binding.viewModel = viewGeneratedImageViewModel
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onNotificationReceived(data: Notify) {
        when(data.identifier){
            CLEAR_DOGS -> {
                binding.linearLayout.removeAllViews()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBar(resources.getString(R.string.recently_generated))
        showCachedData()
    }
    private fun showCachedData(){
        val dogList = displayList()
        for(i in dogList.size-1 downTo 0) {
            val imageViewLayout: View = layoutInflater.inflate(R.layout.layout_image_view, null)
            val imageView: ImageView = imageViewLayout.findViewById(R.id.image)
            BindingUtil.setImage(imageView, dogList[i], null)
            binding.linearLayout.addView(imageViewLayout)
        }
    }

}