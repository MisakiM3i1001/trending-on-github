package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.repository.repository.RepositoryRepository
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class RepositoryPresenter(private val repositoryView: RepositoryContract.View, private val repository: RepositoryRepository, private val rxSchedulers: RxSchedulers) : RepositoryContract.Presenter {

    override fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int) {
        repository.getRepositories(repositoryLanguage, sortOrder, page)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .subscribeWith(object : SingleObserver<RepositoryList> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(t: RepositoryList) {
                        repositoryView.displayRepositories(repositoryResponseList = t.repositoryList)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }
}
