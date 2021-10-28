package com.vinks.iosflowtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseViewModel actual constructor() : ViewModel() {

    protected actual val vmScope = viewModelScope

    actual override fun onCleared() {}
}