package com.example.app.githubapi

import com.example.githubapi.GithubApi
import com.example.githubapi.TopReposSearchResult
import com.example.githubapi.model.ContributorApiModel
import com.example.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FakeGithubApi @Inject constructor() : GithubApi {

  var topRepos = listOf<RepoApiModel>()
  var singleRepoResult: RepoApiModel? = null
  var contributorsResult = listOf<ContributorApiModel>()

  override suspend fun getTopRepositories(): TopReposSearchResult {
    return TopReposSearchResult(topRepos)
  }

  override suspend fun getRepo(repoOwner: String, repoName: String): RepoApiModel {
    return singleRepoResult ?: throw NullPointerException("SingleRepoResult was not set")
  }

  override suspend fun getContributors(
    repoOwner: String,
    repoName: String
  ): List<ContributorApiModel> {
    return contributorsResult
  }

}
