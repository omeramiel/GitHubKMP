package com.omeram.kotlin.githubkmp.interactors

import com.omeram.kotlin.githubkmp.data.MembersRepository

class GetMembersUseCase(private val membersRepository: MembersRepository) {
    suspend operator fun invoke(organization: String) = membersRepository.getMembers(organization)
}