package com.omeram.kotlin.githubkmp.framework

import android.app.Application
import com.omeram.kotlin.githubkmp.UserSettings
import com.omeram.kotlin.githubkmp.data.MembersDataSourceImpl
import com.omeram.kotlin.githubkmp.data.MembersRepository
import com.omeram.kotlin.githubkmp.interactors.GetMembersUseCase
import com.omeram.kotlin.githubkmp.interactors.GetOrganizationUseCase
import com.omeram.kotlin.githubkmp.model.Member
import com.omeram.kotlin.githubkmp.presentation.MembersView

class GitHubKMPApplication : Application(), MembersView {

    override fun onCreate() {
        super.onCreate()
        val membersRepository = MembersRepository(
            MembersDataSourceImpl(UserSettings(this).settings), this
        )
        GitHubKMPViewModelFactory.inject(
            this,
            Interactors(
                GetOrganizationUseCase(membersRepository),
                GetMembersUseCase(membersRepository)
            )
        )
    }

    override var isUpdating: Boolean = false

    override fun showError(error: Throwable) {
        println(error.message)
    }

    override fun onUpdate(members: List<Member>, organization: String) {
        println(members)
    }
}