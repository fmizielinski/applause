package com.example.applause.view.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.applause.R
import com.example.applause.common.subscribe
import com.example.applause.domain.entity.RepoUI
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel {
        parametersOf(DetailsFragmentArgs.fromBundle(arguments!!).repo)
    }

    //region lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.subscribe(this, ::bindData)
    }

    //endregion lifecycle

    private fun bindData(repo: RepoUI) {
        textDetailsTitle.text = repo.name
        textDetailsLanguage.text = repo.language
        textDetailsStarsCount.text = repo.stars.toString()
        textDetailsOwner.text = repo.ownerName
        textDetailsDescription.text = repo.description
        textDetailsLink.text = repo.url
    }
}