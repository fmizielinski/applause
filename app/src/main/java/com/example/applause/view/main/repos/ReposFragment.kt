package com.example.applause.view.main.repos

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applause.R
import com.example.applause.common.SimpleTextWatcher
import com.example.applause.common.subscribe
import com.example.applause.domain.entity.RepoUI
import com.example.applause.view.main.repos.adapter.RepoAdapter
import kotlinx.android.synthetic.main.fragment_repos.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment() {

    private val viewModel: ReposViewModel by viewModel()

    //region lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_repos, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpSearch()
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadRepos()
        viewModel.repos.subscribe(this, ::setUpList)
    }

    //endregion lifecycle

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(context)

        val adapter = RepoAdapter()
        adapter.onClickSubject
            .subscribe(this, ::openDetails)

        recyclerRepos.layoutManager = layoutManager
        recyclerRepos.adapter = adapter

        layoutReposList.setOnRefreshListener { viewModel.loadRepos(true) }
    }

    private fun setUpList(list: List<RepoUI>) {
        (recyclerRepos.adapter as? RepoAdapter)?.list = list

        layoutReposList.isRefreshing = false
    }

    private fun setUpSearch() {
        textReposSearch.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (s == null)
                    return

                viewModel.search(s.toString())
            }
        })
    }

    private fun openDetails(repo: RepoUI) {
        val action = ReposFragmentDirections.actionReposFragmentToDetailsFragment(repo)
        findNavController().navigate(action)
    }
}