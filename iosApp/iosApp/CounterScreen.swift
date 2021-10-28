//
//  CounterScreen.swift
//  iosApp
//
//  Created by Filip Vinkovic on 28.10.2021..
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared


class CounterScreenObservableObject: ObservableObject {
    @Published var viewState = CounterViewState(count: nil, isIncrementButtonEnabled: true, isIncrementInProgress: false)
    
    let viewModel = CounterViewModel()
    
    init() {
        viewModel.getViewState().collect(collector: Collector<CounterViewState> { viewState in
            self.viewState = viewState
        }) {(unit, error) in
            
        }
    }
    
    deinit {
        viewModel.onCleared()
    }
}

struct CounterScreen: View {
    
    @ObservedObject var observable = CounterScreenObservableObject()
    
    var body: some View {
        let viewState = observable.viewState
        
        VStack {
            if let count = viewState.count {
                Text("Count: \(count)")
            } else {
                Text("Press the button to start counting")
            }
            
            Spacer().frame(height: 20)
            
            ZStack {    
                Button(viewState.isIncrementInProgress ? "" : "Increment") {
                    observable.viewModel.onIncrementButtonPress()
                }
                .disabled(!viewState.isIncrementButtonEnabled)
                .frame(width: 120, height: 45)
                .background(viewState.isIncrementButtonEnabled ? Color.blue : Color.gray)
                .foregroundColor(Color.white)
                .cornerRadius(5)
                
                if viewState.isIncrementInProgress {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: (Color.white)))
                }
            }
            
            Spacer().frame(height: 20)
            
            Button("Reset") {
                observable.viewModel.onResetButtonPress()
            }
            .frame(width: 120, height: 45)
            .background(Color.blue)
            .foregroundColor(Color.white)
            .cornerRadius(5)
        }
    }
}

struct CounterContent: View {
    
    var body: some View {
        Text("")
    }
}

struct CounterScreen_Previews: PreviewProvider {
    static var previews: some View {
        CounterScreen()
    }
}
