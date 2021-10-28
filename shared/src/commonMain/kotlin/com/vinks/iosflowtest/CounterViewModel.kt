package com.vinks.iosflowtest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel : BaseViewModel() {

    private val counter = Counter()

    private val viewState = MutableStateFlow(CounterViewState(count = null))
    fun getViewState(): StateFlow<CounterViewState> = viewState.asStateFlow()

    fun onIncrementButtonPress() {
        viewState.value = viewState.value.copy(isIncrementButtonEnabled = false, isIncrementInProgress = true)

        vmScope.launch {
            val count = counter.incrementCount()
            viewState.value = CounterViewState(count = count, isIncrementButtonEnabled = count < 5, isIncrementInProgress = false)
        }
    }

    fun onResetButtonPress() {
        viewState.value = viewState.value.copy(isIncrementButtonEnabled = false, isIncrementInProgress = true)

        vmScope.launch {
            val count = counter.reset()
            viewState.value = CounterViewState(count = count, isIncrementButtonEnabled = count < 5, isIncrementInProgress = false)
        }
    }
}

data class CounterViewState(
    val count: Int? = null,
    val isIncrementButtonEnabled: Boolean = true,
    val isIncrementInProgress: Boolean = false
) : ViewState
