package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter

import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.data.network.repository.pullrequest.PullRequestRepository
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class PullRequestPresenter(private val pullRequestView: PullRequestContract.View, private val pullRequestRepository: PullRequestRepository, private val rxSchedulers: RxSchedulers) : PullRequestContract.Presenter {


    override fun initPresenter(hasRepositorySelected: Boolean) {
        if (hasRepositorySelected) {
            pullRequestView.getPullRequestInformation()
        } else {
            pullRequestView.changeViewFlipperPosition(1)
        }
    }

    override fun getDataFromApi(repositoryOwner: String, repositoryName: String) {
        pullRequestRepository.getPullRequestsFromSelectedRepository(repositoryOwner, repositoryName)
                .subscribeOn((rxSchedulers.io()))
                .observeOn(rxSchedulers.ui())
                .flatMap {
                    Observable
                            .fromIterable(it)
                            .map {
                                PullRequest(it)
                            }
                            .toList()
                }
                .subscribeWith(object : SingleObserver<List<PullRequest>> {
                    override fun onSubscribe(d: Disposable) {
                        pullRequestView.changeViewFlipperPosition(2)
                    }

                    override fun onSuccess(pullRequestList: List<PullRequest>) {
                        pullRequestView.showPullRequestsInformation(pullRequestList)
                        pullRequestView.changeViewFlipperPosition(0)
                    }

                    override fun onError(e: Throwable) {
                        pullRequestView.changeViewFlipperPosition(1)
                    }

                })
    }
}
