//
//  ViewController.swift
//  GitHubKmp
//
//  Created by Admin on 15/10/2019.
//  Copyright © 2019 Admin. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController, MembersView {

    lazy var presenter: MembersPresenter = {
        MembersPresenter(view: self, repository: AppDelegate.appDelegate.dataRepository)
    }()
    
    var isUpdating = false
    
    @IBOutlet weak var greeting: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        greeting.text = Greeting().greeting()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        presenter.onCreate()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        presenter.onDestroy()
    }

    func onUpdate(members: String) {
        print(members)
    }
}

