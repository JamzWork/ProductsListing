package com.example.productlisting.ui.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import com.example.productlisting.applicationEntry.ApplicationEntry
import com.example.productlisting.ui.base.viewModel.BaseViewModel
import com.example.productlisting.utils.event.EventUtilFunctions
import com.example.productlisting.utils.event.UiEvent
import com.squareup.otto.Bus

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected lateinit var application: ApplicationEntry
    protected var isBusRegistered: Boolean = false
    protected lateinit var bus: Bus
    protected lateinit var activity: Activity
    var hasInitializedRootView = false
    private var scrollView: ScrollView? = null

    // data binding
    private lateinit var dataBinding: DB
    protected val binding get() = dataBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = (context as Activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = activity.application as ApplicationEntry
        bus = application.bus
        bus.register(this)
        isBusRegistered = true
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

    override fun onDestroy() {
        super.onDestroy()
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }

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
        EventUtilFunctions.showToast(message, activity)
    }


    fun navigateByDirections(navDirections: NavDirections) {
        EventUtilFunctions.navigateByDirections(this, navDirections)
    }

    open fun back() {
        activity.onBackPressed()
    }

}