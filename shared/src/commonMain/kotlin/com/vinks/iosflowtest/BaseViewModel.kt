package com.vinks.iosflowtest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

expect abstract class BaseViewModel() {

    abstract val state: StateFlow<ViewState>

//    abstract val effects: Flow<ViewEffect>

    protected val vmScope: CoroutineScope

    protected open fun onCleared()
}