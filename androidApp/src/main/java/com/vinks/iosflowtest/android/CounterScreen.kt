package com.vinks.iosflowtest.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vinks.iosflowtest.CounterViewState
import com.vinks.iosflowtest.CounterViewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val uiState = viewModel.getViewState().collectAsState()

    CounterScreenContent(
        viewState = uiState.value,
        onIncrementButtonPress = viewModel::onIncrementButtonPress,
        onResetButtonPress = viewModel::onResetButtonPress
    )
}

@Composable
fun CounterScreenContent(
    viewState: CounterViewState,
    onIncrementButtonPress: () -> Unit,
    onResetButtonPress: () -> Unit
) {
    Box {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            when (viewState.count) {
                null -> Text(text = "Press the button to start counting")
                else -> Text(text = "Count: ${viewState.count}")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = onIncrementButtonPress,
                modifier = Modifier.size(width = 120.dp, height = 45.dp),
                enabled = viewState.isIncrementButtonEnabled
            ) {
                when (viewState.isIncrementInProgress) {
                    true -> CircularProgressIndicator(
                        modifier = Modifier.size(30.dp),
                        color = Color.White
                    )
                    false -> Text(text = "Increment")
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Button(
                onClick = onResetButtonPress,
                modifier = Modifier.size(width = 120.dp, height = 45.dp)
            ) {
                Text(text = "Reset")
            }
        }
    }
}

@Preview
@Composable
fun CounterScreenContentPreview() {
    CounterScreenContent(
        viewState = CounterViewState(
            count = 5,
            isIncrementButtonEnabled = true,
            isIncrementInProgress = false
        ),
        onIncrementButtonPress = {},
        onResetButtonPress = {}
    )
}