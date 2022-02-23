package com.arsi.fixbywallpapers.utils

import android.app.Activity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    requireActivity().toast(message, length)
}

fun Activity.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(applicationContext, message, length).show()
}

/**
 * Get a [ViewModel] in an [ComponentActivity].
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModelBuilder(
    noinline viewModelInitializer: () -> VM,
): Lazy<VM> {
    return ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            return@ViewModelLazy object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")// Casting T as ViewModel
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return viewModelInitializer.invoke() as T
                }
            }
        }
    )
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.activityViewModelBuilder(
    noinline viewModelInitializer: () -> VM,
): Lazy<VM> {
    return ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { requireActivity().viewModelStore },
        factoryProducer = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return viewModelInitializer.invoke() as T
                }
            }
        }
    )
}
