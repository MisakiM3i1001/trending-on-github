package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest
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
    private val pullRequestUsername = itemView.textViewPullRequestUsername

    fun bindItemsToView(pullRequest: PullRequest, clickListener: (pullRequest: PullRequest) -> Unit) {

        itemView.apply {

            pullRequestTitle.text = pullRequest.title

            pullRequestBody.text = getPullRequestAvailableBody(pullRequest)

            Glide.with(pullRequestOwnerPhoto.context)
                    .load(pullRequest.user.profilePhoto)
                    .apply(RequestOptions().circleCrop())
                    .into(pullRequestOwnerPhoto)

            pullRequestUsername.text = pullRequest.user.username

            setOnClickListener {
                clickListener(pullRequest)
            }
        }
    }

    private fun View.getPullRequestAvailableBody(pullRequest: PullRequest): CharSequence {
        return if (!pullRequest.body.isEmpty()) {
            pullRequest.body
        } else {
            resources.getText(R.string.activity_pull_requests_repository_has_no_description)
        }
    }
}
