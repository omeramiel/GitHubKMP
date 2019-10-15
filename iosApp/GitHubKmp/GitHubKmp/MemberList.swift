//
//  MemberList.swift
//  GitHubKmp
//
//  Created by Admin on 15/10/2019.
//  Copyright © 2019 Admin. All rights reserved.
//

import Foundation
import shared

class MemberList {
    var members: [Member] = []
    
    func updateMembers(newMembers:[Member]) {
        members.removeAll()
        members.append(contentsOf: newMembers)
    }
}
