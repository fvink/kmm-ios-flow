package com.vinks.iosflowtest.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vinks.iosflowtest.Asset
import com.vinks.iosflowtest.AssetRepository
import com.vinks.iosflowtest.CounterViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val assetFlow: StateFlow<Asset> = AssetRepository().getAsset()
            .stateIn(
                scope = lifecycleScope,
                started = WhileSubscribed(5000),
                initialValue = Asset("asset0", 0)
            )

        val counterViewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        setContent {
            CounterScreen(viewModel = counterViewModel)
        }
    }
}