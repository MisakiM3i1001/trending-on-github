package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository
import kotlinx.android.synthetic.main.item_repository_layout.view.*

class RepositoryAdapter(private val repositories: List<Repository>, private val clickListener: (repository: Repository) -> Unit) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository_layout, parent, false))
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(repositories[position], clickListener)
    }

}

open class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val respositoryLogo = itemView.imageViewRepositoryLogo
    private val repositoryName = itemView.textViewRepositoryName
    private val repositoryDescription = itemView.textViewRepositoryDescription
    private val repositoryStarCount = itemView.textViewStarCount
    private val repositoryForkCount = itemView.textViewForkCount

    fun bindView(repository: Repository, clickListener: (repository: Repository) -> Unit) {

        itemView.apply {
            repositoryName.text = repository.name
            repositoryDescription.text = repository.description
            repositoryStarCount.text = repository.starCount.toString()
            repositoryForkCount.text = repository.forkCount.toString()

            setOnClickListener {
                clickListener(repository)
            }
        }

    }

}