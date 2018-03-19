package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewType
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewTypeAdapter
import kotlinx.android.synthetic.main.item_repository_layout.view.*

class RepositoryAdapter(private val clickListener: (repository: Repository) -> Unit) : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RepositoryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        (holder as RepositoryViewHolder).bindItemsToView(item as Repository, clickListener)
    }

    inner class RepositoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository_layout, parent, false)) {

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
}