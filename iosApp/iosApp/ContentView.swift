import SwiftUI
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

class CounterObservableObject: ObservableObject {
    @Published var count: Int = 0
    
    init() {
        Counter().count().collect(collector: Collector<Int> { count in
            self.count = count
        }) {(unit, error) in
            
        }
    }
}

struct ContentView: View {

    @ObservedObject var counter = CounterObservableObject()
    
	var body: some View {
        Text(String(counter.count))
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}