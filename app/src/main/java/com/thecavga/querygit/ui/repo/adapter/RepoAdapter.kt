package com.thecavga.querygit.ui.repo.adapter

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thecavga.querygit.R
import com.thecavga.querygit.ui.repo.model.Repo
import kotlinx.android.synthetic.main.repo_item_layout.view.*
import kotlin.collections.ArrayList

class RepoAdapter (var isClikable: Boolean) :  RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private var repos: List<Repo> = emptyList()
    private var itemClickListener: ItemClickListener? = null

    init {
        repos = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item_layout, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        bind(holder, repo)

        holder.itemView.setOnClickListener { v ->
            if (itemClickListener != null && isClikable) {
                itemClickListener!!.onItemClick(repo, position)
            }
        }
    }

    private fun bind(holder: RepoViewHolder, repo: Repo) {
        holder.tvRepoName.text = repo.name

    }


    override fun getItemCount(): Int {
        return repos.size
    }

    fun setRepos(repos: List<Repo>) {
        this.repos = repos
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(repo: Repo, position: Int)
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRepoName: TextView = itemView.tvRepoName

    }
}

