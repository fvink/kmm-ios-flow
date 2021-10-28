package com.vinks.iosflowtest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

actual open class BaseViewModel actual constructor() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    protected actual val vmScope: CoroutineScope = viewModelScope

    protected actual open fun onCleared() {
        viewModelJob.cancelChildren()
    }
}