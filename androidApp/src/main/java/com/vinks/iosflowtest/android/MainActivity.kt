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

        setContent {
            AssetData(assetFlow = assetFlow)
        }
    }
}

@Composable
fun AssetData(assetFlow: StateFlow<Asset>) {
    val asset by assetFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AssetName(name = asset.name)
        FaultCodeCount(count = asset.faultCodeCount)
    }
}

@Composable
fun AssetName(name: String) {
    Log.d("aaa", "redrawing asset name")
    Text(
        text = "Asset name $name",
        fontSize = 30.sp
    )
}

@Composable
fun FaultCodeCount(count: Int) {
    Log.d("aaa", "redrawing fault code count")
    Text(
        text = "Fault code count: $count",
        fontSize = 30.sp
    )
}