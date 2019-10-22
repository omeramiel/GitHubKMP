package com.omeram.kotlin.githubkmp.data

import com.omeram.kotlin.githubkmp.DEFAULT_ORGANIZATION
import com.omeram.kotlin.githubkmp.ORGANIZATION_KEY
import com.omeram.kotlin.githubkmp.api.Errors
import com.omeram.kotlin.githubkmp.api.GitHubApi
import com.omeram.kotlin.githubkmp.model.Member
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.ktor.client.features.ClientRequestException

class MembersDataSourceImpl(private val settings: Settings) : MembersDataSource {

    private val api = GitHubApi()

    override fun saveOrganization(organization: String) {
        settings[ORGANIZATION_KEY] = organization
    }

    override fun loadOrganization(): String = settings[ORGANIZATION_KEY, DEFAULT_ORGANIZATION]

    override suspend fun update(): List<Member>? {
        val organization = loadOrganization()
//        view.isUpdating = true
        try {
            return api.getMembers(organization)
        } catch (cause: Throwable) {
            throw when (cause) {
                is ClientRequestException -> Errors.UpdateProblem()
                else -> Errors.ConnectionProblem()
            }
        }
    }
}