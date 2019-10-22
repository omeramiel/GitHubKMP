package com.omeram.kotlin.githubkmp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val login: String,
    val id: Int,
    @SerialName("avatar_url") val avatar_url: String
)