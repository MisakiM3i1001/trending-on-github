package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.theuzfaleiro.trendingongithub.data.network.GitHubEndPoint
import io.github.theuzfaleiro.trendingongithub.data.network.repository.pullrequest.PullRequestRepository
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter.PullRequestContract
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter.PullRequestPresenter
import io.github.theuzfaleiro.trendingongithub.utils.RxSchedulers


@Module
abstract class PullRequestModule {

    @Binds
    abstract fun bindsPullRequestView(pullRequestActivity: PullRequestActivity): PullRequestContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providesPullRequestRepository(openWeatherEndPoint: GitHubEndPoint): PullRequestRepository {
            return PullRequestRepository(openWeatherEndPoint)
        }

        @JvmStatic
        @Provides
        fun providesPullRequestPresenter(view: PullRequestContract.View, pullRequestRepository: PullRequestRepository, rxSchedulers: RxSchedulers): PullRequestContract.Presenter {
            return PullRequestPresenter(view, pullRequestRepository, rxSchedulers)
        }
    }
}