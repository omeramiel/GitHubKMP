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
    private val presenter by lazy { MembersPresenter(this, repository = repository) }

    override var isUpdating = false

    private lateinit var adapter: MemberAdapter

    override fun onUpdate(members: List<Member>) {
        adapter.members = members
        runOnUiThread {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greeting.text = Greeting().greeting()

        setupRecyclerView()
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setupRecyclerView() {
        membersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(listOf())
        membersRecyclerView.adapter = adapter
    }
}
