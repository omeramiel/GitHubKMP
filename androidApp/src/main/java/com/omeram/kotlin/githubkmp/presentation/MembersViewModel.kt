package com.omeram.kotlin.githubkmp.presentation

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.omeram.kotlin.githubkmp.api.Errors
import com.omeram.kotlin.githubkmp.framework.GitHubKMPViewModel
import com.omeram.kotlin.githubkmp.framework.Interactors
import com.omeram.kotlin.githubkmp.model.Member
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MembersViewModel(application: Application, interactors: Interactors) :
    GitHubKMPViewModel(application, interactors) {

    val organization = MutableLiveData<String>().apply {
        postValue(interactors.getOrganizationUseCase())
    }

    val members = MediatorLiveData<List<Member>>().apply {
        addSource(organization) {
            update(it)
        }
    }

    val error = MutableLiveData<Errors>()

    fun update(organization: String) {
        GlobalScope.launch {
            try {
                members.postValue(interactors.getMembersUseCase(organization))
            } catch (cause: Errors) {
                error.postValue(cause)
            }
        }.invokeOnCompletion {
            //                view.isUpdating = false
        }
    }

}