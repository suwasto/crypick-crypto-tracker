import SwiftUI
import shared
import UIKit

struct ContentView: View {
	var body: some View {
        ComposeView()
	}
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
