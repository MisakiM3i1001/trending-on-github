package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest
import kotlinx.android.synthetic.main.activity_pull_request_detail.*

class PullRequestDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_detail)

        webViewPullRequestDetail.loadUrl(intent.extras.getParcelable<PullRequest>("PULL_REQUEST_SELECTED").htmlUrl)
    }
}
