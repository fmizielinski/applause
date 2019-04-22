package com.example.applause.view.main.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applause.R
import com.example.applause.common.subscribe
import com.example.applause.view.main.repos.adapter.RepoAdapter
import com.example.applause.view.main.repos.adapter.RepoItemViewModel
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

        recyclerRepos.layoutManager = layoutManager
        recyclerRepos.adapter = adapter
    }

    private fun setUpList(list: List<RepoItemViewModel>) {
        (recyclerRepos.adapter as? RepoAdapter)?.list = list
    }
}