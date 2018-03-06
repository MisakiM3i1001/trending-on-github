package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.repository.repository.RepositoryRepository
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class RepositoryPresenter(private var repositoryView: RepositoryContract.RepositoryView, private var repository: RepositoryRepository) : RepositoryContract.RepositoryPresenter {

    override fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int) {
        repository.getRepositories(repositoryLanguage, sortOrder, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { repositoryView.dismisLoading() }
                .subscribeWith(object : SingleObserver<RepositoryList> {
                    override fun onSuccess(t: RepositoryList) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onSubscribe(d: Disposable) {
                        repositoryView.showLoading()
                    }

                    override fun onError(e: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
    }
}
