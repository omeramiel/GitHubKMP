//
//  AppDelegate.swift
//  GitHubKmp
//
//  Created by Admin on 15/10/2019.
//  Copyright © 2019 Admin. All rights reserved.
//

import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    public lazy var settings = UserSettings().settings
    public lazy var dataRepository = {MembersDataRepository(api: GitHubApi(settings: settings))}()
    
    var window: UIWindow?
    
    static var appDelegate: AppDelegate {
        return UIApplication.shared.delegate as! AppDelegate
    }

}

