package com.omeram.kotlin.githubkmp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class GitHubKMPViewModel(application: Application, protected val interactors: Interactors) :
    AndroidViewModel(application) {

    protected val application: GitHubKMPApplication = getApplication()

}
