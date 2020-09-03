package com.example.cathay.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected val TAG = this.javaClass.simpleName

    private fun coroutineExceptionHandler(
        errorAction: (throwable: Throwable) -> Unit = {}
    ): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            errorAction.invoke(throwable)
        }
    }

    fun viewModelLaunch(
        action: suspend CoroutineScope.() -> Unit,
        errorAction: ((throwable: Throwable) -> Unit) = {}
    ) {
        viewModelScope.launch(coroutineExceptionHandler(errorAction)) { action() }
    }
}