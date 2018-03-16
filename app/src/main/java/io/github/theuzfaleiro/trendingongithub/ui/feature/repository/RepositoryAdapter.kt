package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import kotlinx.android.synthetic.main.item_repository_layout.view.*

class RepositoryAdapter(private val repositoryList: List<Repository> = mutableListOf(), private val clickListener: (repository: Repository) -> Unit) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository_layout, parent, false))
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindItemsToView(repositoryList[position], clickListener)
    }

}

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val repositoryLogo = itemView.imageViewRepositoryLogo
    private val repositoryName = itemView.textViewRepositoryName
    private val repositoryDescription = itemView.textViewRepositoryDescription
    private val repositoryStarCount = itemView.textViewStarCount
    private val repositoryForkCount = itemView.textViewForkCount

    fun bindItemsToView(repository: Repository, clickListener: (repository: Repository) -> Unit) {

        itemView.apply {

            Glide.with(repositoryLogo.context)
                    .load(repository.owner.avatarUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(repositoryLogo)

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