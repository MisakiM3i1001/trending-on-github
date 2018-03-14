package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import kotlinx.android.synthetic.main.item_pull_request_layout.view.*


class PullRequestAdapter(private val pullRequestList: List<PullRequest> = listOf(), private val clickListener: (pullRequest: PullRequest) -> Unit) : RecyclerView.Adapter<PullRequestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request_layout, parent, false))
    }

    override fun getItemCount(): Int = pullRequestList.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bindItemsToView(pullRequestList[position], clickListener)
    }
}

class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pullRequestTitle = itemView.textViewPullRequestTitle
    private val pullRequestBody = itemView.textViewPullRequestBody
    private val pullRequestOwnerPhoto = itemView.imageViewPullRequestOwner
    private val pullRequestFullName = itemView.textViewPullRequestFullName
    private val pullRequestUsername = itemView.textViewPullRequestUsername

    fun bindItemsToView(pullRequest: PullRequest, clickListener: (pullRequest: PullRequest) -> Unit) {

        itemView.apply {

            pullRequestTitle.text = pullRequest.title
            pullRequestBody.text = pullRequest.body

            Glide.with(pullRequestOwnerPhoto.context)
                    .load(pullRequest.user.profilePhoto)
                    .into(pullRequestOwnerPhoto)

            //pullRequestFullName.text = pullRequest.user.username
            pullRequestUsername.text = pullRequest.user.username

            setOnClickListener {
                clickListener(pullRequest)
            }
        }
    }
}
