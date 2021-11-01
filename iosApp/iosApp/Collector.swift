//
//  Collector.swift
//  iosApp
//
//  Created by Filip Vinkovic on 28.10.2021..
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

// https://stackoverflow.com/a/66030092
class Collector<T>: Kotlinx_coroutines_coreFlowCollector {

    let callback:(T) -> Void
    
    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        // do whatever you what with the emitted value
        callback(value as! T)

        // after you finished your work you need to call completionHandler to
        // tell that you consumed the value and the next value can be consumed,
        // otherwise you will not receive the next value
        //
        // i think first parameter can be always nil or KotlinUnit()
        // second parameter is for an error which occurred while consuming the value
        // passing an error object will throw a NSGenericException in kotlin code, which can be handled or your app will crash
        completionHandler(KotlinUnit(), nil)
    }
}
