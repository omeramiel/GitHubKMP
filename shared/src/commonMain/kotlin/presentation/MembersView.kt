package com.omeram.kotlin.githubkmp.presentation

import com.omeram.kotlin.githubkmp.model.Member

interface MembersView : BaseView {
    var isUpdating : Boolean
    fun onUpdate(members: List<Member>, organization: String)
}