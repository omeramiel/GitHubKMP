package com.omeram.kotlin.githubkmp.framework

import com.omeram.kotlin.githubkmp.interactors.GetMembersUseCase
import com.omeram.kotlin.githubkmp.interactors.GetOrganizationUseCase

data class Interactors(
    val getOrganizationUseCase: GetOrganizationUseCase,
    val getMembersUseCase: GetMembersUseCase
)