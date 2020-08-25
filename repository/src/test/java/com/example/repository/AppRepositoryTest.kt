package com.example.repository

import com.example.app.githubapi.FakeGithubApi
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
    repos = listOf(fakeRepoApiModel)
  }

  @Before
  fun setUp() {
    appRepository = AppRepository(fakeGithubApi)
  }

  @Test
  fun successfulQuery() {
    val topRepos = runBlocking { appRepository.getTopRepos() }
    assertThat(topRepos.size).isEqualTo(1)
    assertThat(topRepos[0]).isEqualTo(fakeRepoApiModel)
  }
}
