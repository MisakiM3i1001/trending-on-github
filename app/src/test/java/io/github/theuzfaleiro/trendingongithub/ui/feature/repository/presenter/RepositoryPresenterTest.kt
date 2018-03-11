package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.repository.repository.RepositoryRepository
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Owner
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.github.theuzfaleiro.trendingongithub.utils.RxTestSchedulers
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryPresenterTest {

    private val testScheduler = TestScheduler()

    private val schedulerProvider = RxTestSchedulers(testScheduler)

    @Mock
    private lateinit var repositoryView: RepositoryContract.View

    @Mock
    private lateinit var repositoryRepository: RepositoryRepository

    private lateinit var repositoryPresenter: RepositoryPresenter

    @Before
    fun setUp() {
        repositoryPresenter = RepositoryPresenter(repositoryView, repositoryRepository, schedulerProvider)
    }

    @Test
    fun shouldDisplayRepositoryInformation_WhenDataWasFetchedFromApi() {
        `when`(repositoryRepository.getRepositories("android", "stars")).thenReturn(getMockedRepositories())

        repositoryPresenter.getRepositoriesFromApi("android", "stars")

        testScheduler.triggerActions()

        verify(repositoryView, times(1)).changeViewFlipperPosition(2)

        verify(repositoryView, times(1)).displayRepositories(getMockedRepositoriesMock())

        verify(repositoryView, times(1)).changeViewFlipperPosition(0)

    }

    private fun getMockedRepositories(): Single<RepositoryList> =
            Single.just(RepositoryList(listOf(Repository("trending-on-github", "Most Awesome Repository In GitHub",
                    Owner("theuzfaleiro", "theuzfaleiro.svg"), 789, 4123))))

    private fun getMockedError(): Single<RepositoryList> = Single.error(Throwable())

    //TODO Search Why Do I Need This!
    private fun getMockedRepositoriesMock(): RepositoryList = RepositoryList(listOf(Repository("trending-on-github", "Most Awesome Repository In GitHub",
            Owner("theuzfaleiro", "theuzfaleiro.svg"), 789, 4123)))

}