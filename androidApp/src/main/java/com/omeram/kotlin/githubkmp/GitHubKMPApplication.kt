package com.omeram.kotlin.githubkmp

import android.app.Application
import com.omeram.kotlin.githubkmp.api.GitHubApi
import com.omeram.kotlin.githubkmp.model.MembersDataRepository
import com.omeram.kotlin.githubkmp.presentation.DataRepository
import com.russhwolf.settings.Settings

class GitHubKMPApplication : Application() {

    val settings: Settings by lazy {
        UserSettings(this).settings
    }

    val repository: DataRepository by lazy {
        MembersDataRepository(GitHubApi(settings))
    }
}