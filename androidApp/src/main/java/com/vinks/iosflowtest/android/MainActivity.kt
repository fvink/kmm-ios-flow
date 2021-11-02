package com.vinks.iosflowtest.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vinks.iosflowtest.CounterViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val counterViewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        setContent {
            CounterScreen(viewModel = counterViewModel)
        }
    }
}