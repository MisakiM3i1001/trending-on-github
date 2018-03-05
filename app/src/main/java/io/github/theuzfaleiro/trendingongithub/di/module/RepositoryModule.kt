package io.github.theuzfaleiro.trendingongithub.di.module

import dagger.Module
import dagger.Provides
import io.github.theuzfaleiro.trendingongithub.data.network.GitHubEndPoint
import io.github.theuzfaleiro.trendingongithub.data.network.repository.repository.RepositoryRepository
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.RepositoryContract
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.RepositoryPresenter


@Module
class RepositoryModule {

    @Provides
    fun providesRepositoryPresenter(repositoryView: RepositoryContract.RepositoryView, repository: RepositoryRepository): RepositoryContract.RepositoryPresenter {
        return RepositoryPresenter(repositoryView, repository)
    }

    @Provides
    fun providesWeatherRepository(openWeatherEndPoint: GitHubEndPoint): RepositoryRepository {
        return RepositoryRepository(openWeatherEndPoint)
    }

}