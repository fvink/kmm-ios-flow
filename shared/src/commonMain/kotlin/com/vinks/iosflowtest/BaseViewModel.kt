package com.vinks.iosflowtest

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    protected val vmScope: CoroutineScope
    protected open fun onCleared()
}