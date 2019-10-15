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
    
    @IBOutlet weak var greeting: UILabel!
    
    @IBOutlet weak var membersTableView: UITableView!
    
    let memberList =  MemberList()
    
    lazy var presenter: MembersPresenter = {
        MembersPresenter(view: self, repository: AppDelegate.appDelegate.dataRepository)
    }()
    
    var isUpdating = false

    override func viewDidLoad() {
        super.viewDidLoad()
        greeting.text = Greeting().greeting()
        membersTableView.register(UINib(nibName: "MemberCellTableViewCell", bundle: nil), forCellReuseIdentifier: "MemberCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        presenter.onCreate()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        presenter.onDestroy()
    }

    func onUpdate(members: [Member]) {
        memberList.updateMembers(newMembers: members)
        membersTableView.reloadData()
    }
}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.memberList.members.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MemberCell", for: indexPath) as! MemberCellTableViewCell
        let member = self.memberList.members[indexPath.row]
        cell.memberName.text = member.login
        cell.memberAvatar.load(url: URL(string: member.avatar_url)!)
        return cell
    }
}

extension UIImageView {
    func load(url: URL) {
        DispatchQueue.global().async {
            [weak self] in
            if let data = try? Data(contentsOf: url) {
                if let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self?.image = image
                    }
                }
            }
        }
    }
}

