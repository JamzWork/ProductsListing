package com.example.productlisting.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import timber.log.Timber

fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
    val navController = findNavController()
    val destination = navController.currentDestination as FragmentNavigator.Destination
    //current visible fragment == fragment that is firing navigation
    if (javaClass.name == destination.className) {
        navController.navigate(directions)
    } else {
        Timber.e("Invalid navigation detected")
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.show(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun View.hide() {
    this.visibility = View.GONE
}


