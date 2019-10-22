package com.omeram.kotlin.githubkmp.data

import com.omeram.kotlin.githubkmp.model.Member

interface MembersDataSource {
    fun saveOrganization(organization: String)
    fun loadOrganization(): String
    suspend fun update(): List<Member>?
}