package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.repository.pullrequest.PullRequestRepository
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class PullRequestPresenter(private val pullRequestView: PullRequestContract.View, private val pullRequestRepository: PullRequestRepository, private val rxSchedulers: RxSchedulers) : PullRequestContract.Presenter {

    override fun getDataFromApi() {
        pullRequestRepository.getPullRequestsFromSelectedRepository("JakeWharton", "butterknife")
                .subscribeOn((rxSchedulers.io()))
                .observeOn(rxSchedulers.ui())
                .subscribeWith(object : SingleObserver<List<PullRequest>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(pullRequestList: List<PullRequest>) {
                        pullRequestView.showPullRequestsInformation(pullRequestList)
                    }

                    override fun onError(e: Throwable) {
                        //pullRequestView.showPullRequestsInformation("Deu Ruim")
                    }

                })
    }
}
