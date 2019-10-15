package com.omeram.kotlin.githubkmp

import android.app.Application
import com.omeram.kotlin.githubkmp.api.GitHubApi
import com.omeram.kotlin.githubkmp.model.MembersDataRepository
import com.omeram.kotlin.githubkmp.presentation.DataRepository

class GitHubKMPApplication : Application() {
    val repository: DataRepository by lazy { MembersDataRepository(GitHubApi()) }
}