package com.omeram.kotlin.githubkmp.data

import com.omeram.kotlin.githubkmp.ApplicationDispatcher
import com.omeram.kotlin.githubkmp.model.Member
import com.omeram.kotlin.githubkmp.presentation.CoroutinePresenter
import com.omeram.kotlin.githubkmp.presentation.MembersView
import kotlinx.coroutines.launch

class MembersRepository(private val dataSource: MembersDataSource,
                        private val view: MembersView
) : CoroutinePresenter(ApplicationDispatcher, view) {

    fun getOrganization() = dataSource.loadOrganization()

    suspend fun getMembers(organization: String) :List<Member>? {
        dataSource.saveOrganization(organization)
        return dataSource.update()
    }

    fun getMembersDelegate(organization: String) {
        launch {
            val members = getMembers(organization)
            view.onUpdate(members ?: emptyList(), organization)
        }
    }
}