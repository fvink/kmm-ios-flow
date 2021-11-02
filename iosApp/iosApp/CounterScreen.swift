//
//  CounterScreen.swift
//  iosApp
//
//  Created by Filip Vinkovic on 28.10.2021..
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared


struct CounterScreen: View {
    
    @ObservedObject var observable = ObservableViewModel<CounterViewModel, CounterViewState>(viewModel: CounterViewModel())
    
    var body: some View {
        let state = observable.state
        
        VStack {
            if let count = state.count {
                Text("Count: \(count)")
            } else {
                Text("Press the button to start counting")
            }
            
            Spacer().frame(height: 20)
            
            ZStack {    
                Button(state.isIncrementInProgress ? "" : "Increment") {
                    observable.viewModel.onIncrementButtonPress()
                }
                .disabled(!state.isIncrementButtonEnabled)
                .frame(width: 120, height: 45)
                .background(state.isIncrementButtonEnabled ? Color.blue : Color.gray)
                .foregroundColor(Color.white)
                .cornerRadius(5)
                
                if state.isIncrementInProgress {
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

struct CounterScreen_Previews: PreviewProvider {
    static var previews: some View {
        CounterScreen()
    }
}
