package com.example.productlisting.utils.event

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.example.productlisting.utils.extensions.safeNavigateFromNavController

object EventUtilFunctions {
    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun navigateByDirections(fragment: Fragment, navDirections: NavDirections) {
        fragment.safeNavigateFromNavController(navDirections)
    }
}

