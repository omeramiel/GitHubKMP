package com.omeram.kotlin.githubkmp.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.Url

class GitHubApi {
    private val client = HttpClient()
    private val membersUrl = Url("https://api.github.com/orgs/raywenderlich/members")

    suspend fun getMembers(): String {
        return client.get(membersUrl)
    }
}