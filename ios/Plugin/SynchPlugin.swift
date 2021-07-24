import Foundation

@objc public class SynchPlugin: NSObject {
    @objc public func echo(_ value: String) -> String {
        return value
    }
}
