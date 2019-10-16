package com.omeram.kotlin.githubkmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.omeram.kotlin.githubkmp.api.UpdateProblem
import com.omeram.kotlin.githubkmp.model.Member
import com.omeram.kotlin.githubkmp.presentation.MembersPresenter
import com.omeram.kotlin.githubkmp.presentation.MembersView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MembersView {

    private val repository by lazy { (application as GitHubKMPApplication).repository }
    private val settings by lazy { (application as GitHubKMPApplication).settings }
    private val presenter by lazy { MembersPresenter(this, repository = repository, settings = settings) }

    override var isUpdating = false

    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greeting.text = Greeting().greeting()

        setupRecyclerView()
        searchButton.setOnClickListener {
//            searchButton.keyboa
            presenter.update(organizationField.text.toString())
        }
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onUpdate(members: List<Member>, organization: String) {
        adapter.members = members
        runOnUiThread {
            organizationField.setText(organization)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showError(error: Throwable) {
        val errorMessage = when(error) {
            is UpdateProblem -> getString(R.string.connection_error)
            else -> getString(R.string.unknown_error)
        }
        runOnUiThread {
            toast(errorMessage)
        }
    }

    private fun setupRecyclerView() {
        membersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(listOf())
        membersRecyclerView.adapter = adapter
    }
}
