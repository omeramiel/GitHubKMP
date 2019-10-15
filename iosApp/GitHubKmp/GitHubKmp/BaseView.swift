//
//  File.swift
//  GitHubKmp
//
//  Created by Admin on 15/10/2019.
//  Copyright © 2019 Admin. All rights reserved.
//

import Foundation
import shared
import UIKit

extension UIViewController: BaseView {
    
    public func showError(error: KotlinThrowable) {
        let title: String
        var errorMessage: String? = nil
        
        switch error {
        case is UpdateProblem:
            title = "Connection error"
            errorMessage = "Faild to load data from server, please check your internet connection"
        default:
            title = "Unknown error"
            errorMessage = "Try again"
        }
        
        if let message = errorMessage {
            showError(title: title, message: message)
        }
    }
    
    func showError(title: String, message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.alert)
        alertController.addAction(UIAlertAction(title: "Dismiss", style: UIAlertAction.Style.default, handler: nil))
        self.present(alertController, animated: true, completion: nil)
    }
}
