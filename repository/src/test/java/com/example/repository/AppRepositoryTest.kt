package com.example.repository

import com.example.app.githubapi.FakeGithubApi
import com.example.githubapi.model.ContributorApiModel
import com.example.githubapi.model.RepoApiModel
import com.example.githubapi.model.UserApiModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
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

class AppRepositoryTest {
  private lateinit var appRepository: AppRepository
  private val fakeGithubApi = FakeGithubApi().apply {
    topRepos = listOf(fakeRepoApiModel)
  }

  @Before
  fun setUp() {
    appRepository = AppRepository(fakeGithubApi)
  }

  @Test
  fun `getTopRepos returns result from GithubApi`() {
    val topRepos = runBlocking { appRepository.getTopRepos() }
    assertThat(topRepos.size).isEqualTo(1)
    assertThat(topRepos[0]).isEqualTo(fakeRepoApiModel)
  }

  @Test
  fun `getTopRepos returns cached result`() {
    val initialRequest = runBlocking { appRepository.getTopRepos() }

    // Change API return value
    fakeGithubApi.topRepos = listOf(fakeRepoApiModel, fakeRepoApiModel)

    val secondRequest = runBlocking { appRepository.getTopRepos() }

    assertThat(initialRequest).isEqualTo(secondRequest)
  }

  @Test
  fun `getRepo returns cached value`() {

    // Seed cache
    runBlocking { appRepository.getTopRepos() }

    // Set API to return different model on single api repo fetch
    fakeGithubApi.singleRepoResult = fakeRepoApiModel.copy(name = "Changed Name")

    val singleRepoFetchResult = runBlocking {
      appRepository.getRepo(
        fakeRepoApiModel.owner.login,
        fakeRepoApiModel.name
      )
    }

    assertThat(singleRepoFetchResult).isEqualTo(fakeRepoApiModel)
  }


  @Test
  fun `getRepo returns API value if not in cache`() {
    // Seed cache
    runBlocking { appRepository.getTopRepos() }

    val expectedModel = fakeRepoApiModel.copy(name = "Updated Name")
    fakeGithubApi.singleRepoResult = expectedModel

    val singleRepoFetchResult = runBlocking {
      appRepository.getRepo(
        repoOwner = expectedModel.owner.login,
        repoName = expectedModel.name
      )
    }

    assertThat(singleRepoFetchResult).isEqualTo(expectedModel)
  }

  @Test
  fun `getContributors returns API value`() {
    val expectedContributors = listOf(
      ContributorApiModel(
        id = 1L,
        login = "contributor",
        avatarUrl = "avatar.png"
      )
    )

    fakeGithubApi.contributorsResult = expectedContributors

    val contributors = runBlocking {
      appRepository.getContributors(fakeRepoApiModel.owner.login, fakeRepoApiModel.name)
    }

    assertThat(contributors).isEqualTo(expectedContributors)
  }
}
