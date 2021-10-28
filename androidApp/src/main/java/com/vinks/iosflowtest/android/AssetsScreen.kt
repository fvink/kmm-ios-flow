package com.vinks.iosflowtest.android

import android.util.Log
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
import com.vinks.iosflowtest.Asset
import kotlinx.coroutines.flow.StateFlow

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