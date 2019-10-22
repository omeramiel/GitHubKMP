package com.omeram.kotlin.githubkmp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.omeram.kotlin.githubkmp.Greeting
import com.omeram.kotlin.githubkmp.R
import com.omeram.kotlin.githubkmp.api.Errors
import com.omeram.kotlin.githubkmp.framework.GitHubKMPViewModelFactory
import com.omeram.kotlin.githubkmp.framework.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MembersActivity : AppCompatActivity() {

//    override var isUpdating = false

    private lateinit var adapter: MemberAdapter

    private lateinit var viewModel: MembersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this, GitHubKMPViewModelFactory).get(MembersViewModel::class.java)
        greeting.text = Greeting().greeting()

        setupRecyclerView()
        searchButton.setOnClickListener {
            organizationField.text.let {
                if (it.isNotBlank()) {
                    searchButton.hideKeyboard()
                    viewModel.update(it.toString())
                }
            }
        }
        viewModel.organization.observe(this, Observer { organizationField.setText(it) })
        viewModel.members.observe(this, Observer { members ->
            adapter.members = members
            adapter.notifyDataSetChanged()
        })
        viewModel.error.observe(this, Observer { error ->
            toast(getErrorMessage(error))
        })
    }

    private fun setupRecyclerView() {
        membersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(listOf())
        membersRecyclerView.adapter = adapter
    }

    private fun getErrorMessage(error: Errors) =
        when (error) {
            is Errors.UpdateProblem -> getString(R.string.update_members_error)
            is Errors.ConnectionProblem -> getString(R.string.connection_error)
        }
}
