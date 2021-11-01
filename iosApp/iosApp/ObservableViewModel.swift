//
//  ObservableViewModel.swift
//  iosApp
//
//  Created by Filip Vinkovic on 01.11.2021..
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared


class ObservableViewModel<
    ViewModel: BaseViewModel,
    State: ViewState
> : ObservableObject {
    
    @Published private(set) var state: State
    
    let viewModel: ViewModel
    
    init(viewModel: ViewModel) {
        self.viewModel = viewModel
        self.state = State()
        
        // the flow extension function (defined in iosMain/BaseViewModel) way of observing flow, no need for the Collector class.
        // seems to be working fine.
        viewModel.observe(viewModel.state, onChange: { state in
            self.state = state as! State
        })
        
        // the Collector way of observing flow
        //viewModel.state.collect(collector: Collector<State> { state in
          //  self.state = state
        //}) {(unit, error) in}
    }
    
    deinit {
        viewModel.onCleared()
    }
}
