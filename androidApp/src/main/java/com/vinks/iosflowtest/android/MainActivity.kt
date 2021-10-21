package com.vinks.iosflowtest.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.vinks.iosflowtest.Counter
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val counterStateFlow = Counter().count()
            .stateIn(
                scope = lifecycleScope,
                started = WhileSubscribed(5000),
                initialValue = 0
            )

        setContent {
            CounterText(counterStateFlow)
        }
    }
}

@Composable
fun CounterText(counterFlow: StateFlow<Int>) {
    val counterValue by counterFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = counterValue.toString(),
            fontSize = 30.sp
        )
    }
}