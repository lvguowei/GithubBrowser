package com.example.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.app.githubapi.FakeGithubApi
import com.example.githubapi.model.RepoApiModel
import com.example.githubapi.model.UserApiModel
import com.example.home.list.RepoItem
import com.example.repository.AppRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test


val fakeRepoApiModel = RepoApiModel(
  id = 1L,
  name = "Mock Repo",
  description = "Mock Repo Description",
  owner = UserApiModel(id = 1L, login = "dagger"),
  stargazersCount = 1,
  forksCount = 1,
  contributorsUrl = "",
  createdDate = "1/1/2020",
  updatedDate = "1/1/2020"
)


class HomeViewModelTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  private lateinit var viewModel: HomeViewModel
  private lateinit var viewStateValues: MutableList<HomeViewState>

  @Before
  fun setUp() {
    Dispatchers.setMain(Dispatchers.Unconfined)
    val appRepository = AppRepository(FakeGithubApi().apply { topRepos = listOf(fakeRepoApiModel) })
    viewStateValues = mutableListOf()
    viewModel = HomeViewModel(appRepository)
    viewModel.viewStateUpdates.observeForever { viewStateValues.add(it) }
  }

  @Test
  fun `loaded state contains repo models`() {
    assertThat(viewStateValues.size).isEqualTo(1)
    val expectedState = HomeViewStateLoaded(
      repos = listOf(
        RepoItem(
          name = fakeRepoApiModel.name,
          description = fakeRepoApiModel.description ?: "",
          starsCount = fakeRepoApiModel.stargazersCount,
          forkCount = fakeRepoApiModel.forksCount
        )
      )
    )

    assertThat(viewStateValues[0]).isEqualTo(expectedState)
  }
}
