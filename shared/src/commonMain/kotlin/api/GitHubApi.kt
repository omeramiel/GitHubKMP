package com.omeram.kotlin.githubkmp.api

import com.omeram.kotlin.githubkmp.model.Member
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GitHubApi {
    private val client = HttpClient()
    private val membersUrl = Url("https://api.github.com/orgs/raywenderlich/members")

    suspend fun getMembers(): List<Member> {
        val result: String =  client.get {
            url( membersUrl.toString())
        }
        return Json.nonstrict.parse(Member.serializer().list, result)
    }
}