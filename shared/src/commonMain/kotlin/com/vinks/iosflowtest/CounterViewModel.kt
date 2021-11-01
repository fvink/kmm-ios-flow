package com.vinks.iosflowtest

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CounterViewModel : BaseViewModel() {

    private val counter = Counter()

    private val _state = MutableStateFlow(CounterViewState(count = null))
    override val state: StateFlow<CounterViewState> = _state.asStateFlow()

//    private val _effects = Channel<CounterViewEffect>()
//    override val effects: Flow<CounterViewEffect> = _effects.receiveAsFlow()

    fun onIncrementButtonPress() {
        _state.value = _state.value.copy(isIncrementButtonEnabled = false, isIncrementInProgress = true)

        vmScope.launch {
            val count = counter.incrementCount()
            _state.value = CounterViewState(count = count, isIncrementButtonEnabled = count < 5, isIncrementInProgress = false)
        }
    }

    fun onResetButtonPress() {
        vmScope.launch {
            _state.value = _state.value.copy(isConfirmResetDialogShown = true)
        }
    }

    fun onConfirmResetButtonPress() {
        _state.value = _state.value.copy(isIncrementButtonEnabled = false, isIncrementInProgress = true, isConfirmResetDialogShown = false)

        vmScope.launch {
            val count = counter.reset()
            _state.value = CounterViewState(count = count, isIncrementButtonEnabled = count < 5, isIncrementInProgress = false)
        }
    }

    fun onCancelResetButtonPress() {
        _state.value = _state.value.copy(isConfirmResetDialogShown = false)
    }
}

data class CounterViewState(
    val count: Int? = null,
    val isIncrementButtonEnabled: Boolean = true,
    val isIncrementInProgress: Boolean = false,
    val isConfirmResetDialogShown: Boolean = false
) : ViewState()

sealed class CounterViewEffect : ViewEffect {
    object ShowResetConfirmationDialog : CounterViewEffect()
}