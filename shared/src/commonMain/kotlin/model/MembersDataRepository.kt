package com.omeram.kotlin.githubkmp.model

import com.omeram.kotlin.githubkmp.api.GitHubApi
import com.omeram.kotlin.githubkmp.api.UpdateProblem
import com.omeram.kotlin.githubkmp.presentation.BaseView
import com.omeram.kotlin.githubkmp.presentation.DataRepository

class MembersDataRepository(
    private val api: GitHubApi
) : DataRepository {
    override var members: List<Member>? = null

    override var onRefreshListeners: List<() -> Unit> = emptyList()

    override suspend fun update() {
        val newMembers = try {
            api.getMembers()
        } catch (error: Throwable) {
            throw UpdateProblem()
        }

        if (newMembers != members) {
            members = newMembers
            callRefreshListeners()
        }
    }

    private fun callRefreshListeners() {
        onRefreshListeners.forEach { it() }
    }
}