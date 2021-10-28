package com.vinks.iosflowtest

import kotlinx.coroutines.delay

class Counter {

    private var count = 0

    suspend fun incrementCount(): Int {
        delay(500)
        return ++count
    }

    suspend fun reset(): Int {
        delay(500)
        count = 0
        return count
    }
}