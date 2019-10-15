package com.omeram.kotlin.githubkmp.presentation

import com.omeram.kotlin.githubkmp.model.Member

interface DataRepository {
    val members: List<Member>?
    var onRefreshListeners: List<() -> Unit>

    suspend fun update()
}