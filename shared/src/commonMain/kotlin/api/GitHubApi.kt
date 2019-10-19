package com.omeram.kotlin.githubkmp.api

import com.omeram.kotlin.githubkmp.DEFAULT_ORGANIZATION
import com.omeram.kotlin.githubkmp.ORGANIZATION_KEY
import com.omeram.kotlin.githubkmp.model.Member
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GitHubApi(private val settings: Settings) {
    private val client = HttpClient()
    private val baseUrl = Url("https://api.github.com/orgs")

    suspend fun getMembers(): List<Member> {
        val membersUrl = Url("$baseUrl/${settings[ORGANIZATION_KEY, DEFAULT_ORGANIZATION]}/members")
        val result: String =  client.get {
            url( membersUrl.toString())
        }
        return Json.nonstrict.parse(Member.serializer().list, result)
    }
}