package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.theuzfaleiro.trendingongithub.data.network.GitHubEndPoint
import io.github.theuzfaleiro.trendingongithub.data.network.repository.repository.RepositoryRepository
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.RepositoryActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryPresenter
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun repositoryView(weatherRepositoryActivity: RepositoryActivity): RepositoryContract.View

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesRepositoryRepository(openWeatherEndPoint: GitHubEndPoint): RepositoryRepository {
            return RepositoryRepository(openWeatherEndPoint)
        }

        @Provides
        @JvmStatic
        fun providesRepositoryPresenter(view: RepositoryContract.View, repositoryRepository: RepositoryRepository, rxSchedulers: RxSchedulers): RepositoryContract.Presenter {
            return RepositoryPresenter(view, repositoryRepository, rxSchedulers)
        }
    }
}