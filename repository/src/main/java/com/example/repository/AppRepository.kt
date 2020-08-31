package com.example.repository

import com.example.githubapi.GithubApi
import com.example.githubapi.model.ContributorApiModel
import com.example.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
  private val githubApi: GithubApi
) {

  private val cachedRepos = mutableListOf<RepoApiModel>()

  suspend fun getTopRepos(): List<RepoApiModel> {
    if (cachedRepos.isEmpty()) {
      cachedRepos.addAll(githubApi.getTopRepositories().items)
    }
    return cachedRepos
  }

  suspend fun getRepo(repoOwner: String, repoName: String): RepoApiModel {
    return getRepoFromCache(repoOwner, repoName) ?: githubApi.getRepo(repoOwner, repoName)
  }

  private fun getRepoFromCache(repoOwner: String, repoName: String): RepoApiModel? {
    return cachedRepos.firstOrNull { repo ->
      repo.owner.login == repoOwner && repo.name == repoName
    }
  }

  suspend fun getContributors(repoOwner: String, repoName: String): List<ContributorApiModel> {
    return githubApi.getContributors(repoOwner, repoName)
  }
}
