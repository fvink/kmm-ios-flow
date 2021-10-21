package com.vinks.iosflowtest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AssetRepository {

    fun getAsset(): Flow<Asset> = flow {
        var count = 0
        var asset = Asset(name = "asset$count", count)
        while (true) {
            asset = if (count % 2 == 0) {
                asset.copy(name = "asset$count")
            } else {
                asset.copy(faultCodeCount = count)
            }
            count++
            emit(asset)
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)
}