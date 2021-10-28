import SwiftUI
import shared


class AssetObservableObject: ObservableObject {
    @Published var asset: Asset = Asset(name: "asset0", faultCodeCount: 0)
    
    init() {
        AssetRepository().getAsset().collect(collector: Collector<Asset> { asset in
            self.asset = asset
        }) {(unit, error) in
            
        }
    }
}

struct ContentView: View {

    @ObservedObject var assetObservable = AssetObservableObject()
    
	var body: some View {
        AssetNameView(name: assetObservable.asset.name)
        FaultCodeCount(count: Int(assetObservable.asset.faultCodeCount))
	}
}

struct AssetNameView: View {

    var name: String
    
    var body: some View {
        print("redrawing asset name view: \(name)")
        return Text("Asset name: \(name)")
    }
}

struct FaultCodeCount: View {

    var count: Int
    
    var body: some View {
        print("redrawing fault code count view: \(count)")
        return Text("Fault code count: \(count)")
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
