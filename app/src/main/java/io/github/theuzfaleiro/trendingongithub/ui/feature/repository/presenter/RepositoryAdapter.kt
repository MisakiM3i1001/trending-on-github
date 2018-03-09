package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository

class RepositoryAdapter(private val repositories: List<Repository>, private val clickListener: (repository: Repository) -> Unit) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(repositories[position], clickListener)
    }

}

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(repository: Repository, clickListener: (repository: Repository) -> Unit) {

    }

}