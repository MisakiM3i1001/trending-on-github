package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PullRequestDetailActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.presenter.PullRequestDetailContract
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.presenter.PullRequestDetailPresenter


@Module
abstract class PullRequestDetailModule {

    @Binds
    abstract fun bindsPullRequestView(pullRequestDetailActivity: PullRequestDetailActivity): PullRequestDetailContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providesPullRequestPresenter(pullRequestDetailView: PullRequestDetailContract.View): PullRequestDetailContract.Presenter {
            return PullRequestDetailPresenter(pullRequestDetailView)
        }
    }
}