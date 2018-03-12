package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import kotlinx.android.synthetic.main.item_pull_request_layout.view.*


class PullRequestAdapter(private val pullRequestList: List<PullRequest>, private val clickListener: (pullRequest: PullRequest) -> Unit) : RecyclerView.Adapter<PullRequestViewHolder>() {
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

    fun bindItemsToView(pullRequest: PullRequest, clickListener: (pullRequest: PullRequest) -> Unit) {

        itemView.apply {

            pullRequestTitle.text = pullRequest.title
            pullRequestBody.text = pullRequest.body

            setOnClickListener {
                clickListener(pullRequest)
            }
        }
    }
}
