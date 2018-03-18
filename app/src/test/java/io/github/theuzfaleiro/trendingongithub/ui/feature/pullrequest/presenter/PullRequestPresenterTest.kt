package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.theuzfaleiro.trendingongithub.data.network.repository.pullrequest.PullRequestRepository
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.User
import io.github.theuzfaleiro.trendingongithub.utils.RxTestSchedulers
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestPresenterTest {

    private val testSchedulers = TestScheduler()

    private val schedulerProvider = RxTestSchedulers(testSchedulers)

    @Mock
    private lateinit var pullRequestView: PullRequestContract.View

    @Mock
    private lateinit var pullRequestRepository: PullRequestRepository

    private lateinit var pullRequestPresenter: PullRequestPresenter

    @Before
    fun setUp() {
        pullRequestPresenter = PullRequestPresenter(pullRequestView, pullRequestRepository, schedulerProvider)
    }

    @Test
    fun shouldFetchDataFromApi_WhenRepositoryInformationIsValid() {
        pullRequestPresenter.initPresenter(true)

        verify(pullRequestView, times(1)).getPullRequestInformation()
    }

    @Test
    fun shouldShowErrorMessage_WhenRepositoryInformationIsEmpty() {
        pullRequestPresenter.initPresenter(false)

        verify(pullRequestView, times(1)).changeViewFlipperPosition(1)
    }

    @Test
    fun shouldDisplayPullRequestInformation_WhenDataWasFetchedFromApi() {
        whenever(pullRequestRepository.getPullRequestsFromSelectedRepository("theuzfaleiro",
                "trending-on-github")).thenReturn(getMockedPullRequests())

        pullRequestPresenter.getDataFromApi("theuzfaleiro", "trending-on-github")

        testSchedulers.triggerActions()

        verify(pullRequestView, times(1)).changeViewFlipperPosition(2)

        verify(pullRequestView, times(1)).showPullRequestsInformation(any())

        verify(pullRequestView, times(1)).changeViewFlipperPosition(0)

    }


    @Test
    fun shouldDisplayErrorMessage_WhenNoneRepositoryWasFetchedFromApi() {
        whenever(pullRequestRepository.getPullRequestsFromSelectedRepository("theuzfaleiro", "trending-on-github")).thenReturn(getMockedError())

        pullRequestPresenter.getDataFromApi("theuzfaleiro", "trending-on-github")

        testSchedulers.triggerActions()

        verify(pullRequestView, times(1)).changeViewFlipperPosition(2)

        verify(pullRequestView, times(1)).changeViewFlipperPosition(1)
    }

    private fun getMockedPullRequests(): Single<List<PullRequest>> =
            Single.just(listOf(PullRequest("https://github.com/JakeWharton/butterknife/pull/1107",
                    "Fix Butterknife Annotations", User("theuzfaleiro",
                    "https://avatars1.githubusercontent.com/u/3526915?v=4"), "Fix All Annotations",
                    "17/03/2018")))


    private fun getMockedError(): Single<List<PullRequest>> = Single.error(Throwable())

}