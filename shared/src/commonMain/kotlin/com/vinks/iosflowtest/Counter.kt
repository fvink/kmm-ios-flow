package com.vinks.iosflowtest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Counter {

    fun count(): Flow<Int> = flow {
        var count = 0
        while (true) {
            emit(count++)
            delay(500)
        }
    }.flowOn(Dispatchers.Default)
}