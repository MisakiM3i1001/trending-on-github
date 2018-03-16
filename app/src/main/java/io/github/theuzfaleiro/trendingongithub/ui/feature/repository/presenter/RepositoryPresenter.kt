package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.data.network.repository.repository.RepositoryRepository
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class RepositoryPresenter(private val repositoryView: RepositoryContract.View, private val repository: RepositoryRepository, private val rxSchedulers: RxSchedulers) : RepositoryContract.Presenter {

    override fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int) {

        repository.getRepositories(repositoryLanguage, sortOrder, page)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .flatMap {
                    Observable
                            .fromIterable(it.repositoryList)
                            .map {
                                Repository(it)
                            }
                            .toList()
                }
                .subscribeWith(object : SingleObserver<List<Repository>> {
                    override fun onSubscribe(d: Disposable) {
                        repositoryView.changeViewFlipperPosition(2)
                    }

                    override fun onSuccess(t: List<Repository>) {
                        repositoryView.changeViewFlipperPosition(0)
                        repositoryView.displayRepositories(t)
                    }

                    override fun onError(e: Throwable) {
                        repositoryView.changeViewFlipperPosition(1)
                    }
                })
    }
}