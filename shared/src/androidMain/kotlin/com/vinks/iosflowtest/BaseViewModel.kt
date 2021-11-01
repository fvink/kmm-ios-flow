package com.vinks.iosflowtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

actual abstract class BaseViewModel actual constructor() : ViewModel() {

    actual abstract val state: StateFlow<ViewState>

//    actual abstract val effects: Flow<ViewEffect>

    protected actual val vmScope = viewModelScope

    actual override fun onCleared() {}
}