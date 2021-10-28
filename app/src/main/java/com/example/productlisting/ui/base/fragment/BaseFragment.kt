package com.example.productlisting.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import com.example.productlisting.applicationEntry.ApplicationEntry
import com.example.productlisting.ui.base.viewModel.BaseViewModel
import com.example.productlisting.utils.event.EventUtilFunctions
import com.example.productlisting.utils.event.UiEvent

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected lateinit var application: ApplicationEntry

    // data binding
    private lateinit var dataBinding: DB
    protected val binding get() = dataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = requireActivity().application as ApplicationEntry
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = getFragmentBinding(inflater, container)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.apply { lifecycleOwner = viewLifecycleOwner }
    }

    protected abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): DB

    fun subscribeUiEvents(baseViewModel: BaseViewModel) {
        baseViewModel.uiEvents.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()
                ?.let { event ->
                    when (event) {
                        is UiEvent.ShowToast -> {
                            showToast(event.message)
                        }

                        is UiEvent.NavigateByDirections -> {
                            navigateByDirections(event.navDirections)
                        }
                    }
                }
        }
    }

    fun showToast(message: String) {
        EventUtilFunctions.showToast(message, requireContext())
    }


    fun navigateByDirections(navDirections: NavDirections) {
        EventUtilFunctions.navigateByDirections(this, navDirections)
    }

    open fun back() {
        requireActivity().onBackPressed()
    }

}