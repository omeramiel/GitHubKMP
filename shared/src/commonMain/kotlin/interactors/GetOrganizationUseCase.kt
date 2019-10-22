package com.omeram.kotlin.githubkmp.interactors

import com.omeram.kotlin.githubkmp.data.MembersRepository

class GetOrganizationUseCase(private val membersRepository: MembersRepository) {
    operator fun invoke() = membersRepository.getOrganization()
}