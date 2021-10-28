package com.vinks.iosflowtest.android

import android.os.Bundle
import android.util.Log
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

        val counterViewModel = CounterViewModel()

        setContent {
            CounterScreen(viewModel = counterViewModel)
//            AssetData(assetFlow = assetFlow)
        }
    }
}