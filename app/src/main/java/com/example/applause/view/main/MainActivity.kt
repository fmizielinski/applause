package com.example.applause.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.applause.R
import com.example.applause.common.subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    //region lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loggedIn.subscribe(this, ::showReposScreen)
    }

    override fun onResume() {
        super.onResume()
        intent.data?.let(viewModel::handleRedirectData)
    }

    //endregion lifecycle

    private fun showReposScreen() {
        findNavController(R.id.fragmentMain).navigate(R.id.action_global_reposFragment)
    }
}