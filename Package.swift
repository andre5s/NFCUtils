// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Nfcutils",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "Nfcutils",
            targets: ["NFCUtilsPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "NFCUtilsPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/NFCUtilsPlugin"),
        .testTarget(
            name: "NFCUtilsPluginTests",
            dependencies: ["NFCUtilsPlugin"],
            path: "ios/Tests/NFCUtilsPluginTests")
    ]
)