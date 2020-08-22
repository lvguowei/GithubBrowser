package com.example.repository

import com.example.githubapi.GithubApi
import com.example.githubapi.model.RepoApiModel
import com.example.githubapi.model.UserApiModel
import com.google.common.truth.Truth.assertThat
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
  private val fakeGithubApi = FakeGithubApi()

  @Before
  fun setUp() {
    appRepository = AppRepository(fakeGithubApi)
  }

  @Test
  fun successfulQuery() {
    val topRepos = appRepository.getTopRepos()
    assertThat(topRepos.size).isEqualTo(1)
    assertThat(topRepos[0]).isEqualTo(fakeRepoApiModel)
  }
}

private class FakeGithubApi : GithubApi {
  override fun getTopRepositories(): List<RepoApiModel> {
    return listOf(
      fakeRepoApiModel
    )
  }

}
