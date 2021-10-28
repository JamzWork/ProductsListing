package com.example.productlisting.ui.base.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.productlisting.utils.event.Event
import com.example.productlisting.utils.event.UiEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiEventsLiveData = MutableLiveData<Event<UiEvent>>()
    val uiEvents: LiveData<Event<UiEvent>> = _uiEventsLiveData

    fun showToast(message: String) {
        _uiEventsLiveData.value = Event(UiEvent.ShowToast(message))
    }

}