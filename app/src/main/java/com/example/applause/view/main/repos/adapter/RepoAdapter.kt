package com.example.applause.view.main.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applause.R
import com.example.applause.domain.entity.RepoUI
import io.reactivex.subjects.PublishSubject

class RepoAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    var list: List<RepoUI> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val onClickSubject: PublishSubject<RepoUI> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item) { onClickSubject.onNext(item) }
    }
}