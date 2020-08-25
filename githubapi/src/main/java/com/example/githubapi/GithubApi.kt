package com.example.githubapi

import com.example.githubapi.model.RepoApiModel
import com.example.githubapi.model.UserApiModel
import javax.inject.Inject
import javax.inject.Singleton

interface GithubApi {
  suspend fun getTopRepositories(): List<RepoApiModel>
}

@Singleton
class MockGithubApi @Inject constructor() : GithubApi {
  override suspend fun getTopRepositories(): List<RepoApiModel> {
    return listOf(
      RepoApiModel(
        id = 1L,
        name = "Mock Repo",
        description = "Mock Repo Description",
        owner = UserApiModel(id = 1L, login = "dagger"),
        stargazersCount = 1,
        forksCount = 1,
        contributorsUrl = "",
        createdDate = "1/1/2020",
        updatedDate = "1/1/2020"
      ),
      RepoApiModel(
        id = 1L,
        name = "Mock Repo",
        description = "Mock Repo Description",
        owner = UserApiModel(id = 1L, login = "dagger"),
        stargazersCount = 1,
        forksCount = 1,
        contributorsUrl = "",
        createdDate = "1/1/2020",
        updatedDate = "1/1/2020"
      ),
      RepoApiModel(
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
    )
  }

}
