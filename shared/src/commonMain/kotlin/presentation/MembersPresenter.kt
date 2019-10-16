package com.omeram.kotlin.githubkmp.presentation

import com.omeram.kotlin.githubkmp.ApplicationDispatcher
import com.omeram.kotlin.githubkmp.DEFAULT_ORGANIZATION
import com.omeram.kotlin.githubkmp.ORGANIZATION_KEY
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.launch

class MembersPresenter(
    private val view: MembersView,
    private val repository: DataRepository,
    private val settings: Settings
) : CoroutinePresenter(ApplicationDispatcher, view) {

    private val onRefreshListener: () -> Unit = this::showData

    override fun onCreate() {
        view.isUpdating = isFirstDataLoading()
        repository.onRefreshListeners += onRefreshListener
        showData()
        updateData()
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.onRefreshListeners -= onRefreshListener
    }

    fun update(organization: String) {
        settings[ORGANIZATION_KEY] = organization
        view.isUpdating = true
        updateData()
    }

    private fun showData() {
        view.onUpdate(
            repository.members.orEmpty(),
            settings[ORGANIZATION_KEY, DEFAULT_ORGANIZATION]
        )
    }

    private fun updateData() {
        launch {
            repository.update()
            showData()
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }

    private fun isFirstDataLoading() = repository.members == null
}