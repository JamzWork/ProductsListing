package com.example.productlisting.utils.event

import androidx.navigation.NavDirections

sealed class UiEvent {
    class ShowToast(val message: String) : UiEvent()
    class NavigateByDirections(val navDirections: NavDirections) : UiEvent()
}