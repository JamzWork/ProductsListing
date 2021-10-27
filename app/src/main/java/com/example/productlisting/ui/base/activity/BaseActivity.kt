package com.example.productlisting.ui.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productlisting.ui.base.viewModel.BaseViewModel
import com.example.productlisting.utils.event.EventUtilFunctions
import com.example.productlisting.utils.event.UiEvent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun subscribeUiEvents(baseViewModel: BaseViewModel) {
        baseViewModel.uiEvents.observe(this) {
            it.getContentIfNotHandled()
                ?.let { event ->
                    when (event) {
                        is UiEvent.ShowToast -> {
                            EventUtilFunctions.showToast(event.message, this)
                        }
                    }
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}