package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.presenter

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestDetailPresenterTest {

    @Mock
    lateinit var pullRequestDetailView: PullRequestDetailContract.View

    lateinit var pullRequestDetailPresenter: PullRequestDetailPresenter

    @Before
    fun setUp() {
        pullRequestDetailPresenter = PullRequestDetailPresenter(pullRequestDetailView)
    }

    @Test
    fun shouldShowWebView_WhenPullRequestDetailHasAnUrl() {
        pullRequestDetailPresenter.hasPullRequestUrl(true)

        verify(pullRequestDetailView, times(1)).displayPullRequestDetailOnWebView()
    }

    @Test
    fun shouldShowErrorImage_WhenPullRequestDetailHasNoUrl() {
        pullRequestDetailPresenter.hasPullRequestUrl(false)

        verify(pullRequestDetailView, times(1)).changeViewFlipperPosition(1)
    }

}