package com.example.applause.view.main.repos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.applause.common.setOnClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: RepoItemViewModel) {
        containerView.textRepoTitle.text = item.name
        containerView.textRepoLanguage.text = item.language
        containerView.textRepoStarsCount.text = item.stars.toString()

        containerView.setOnClickListener(item::click)
    }
}