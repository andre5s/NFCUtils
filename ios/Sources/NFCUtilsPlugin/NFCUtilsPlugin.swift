/**************************/
/*****NOT IMPLEMENTED******/
/**************************/

import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(NFCUtilsPlugin)
public class NFCUtilsPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "NFCUtilsPlugin"
    public let jsName = "NFCUtils"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = NFCUtils()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
