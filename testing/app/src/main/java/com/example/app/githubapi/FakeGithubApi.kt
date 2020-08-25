package com.example.app.githubapi

import com.example.githubapi.GithubApi
import com.example.githubapi.TopReposSearchResult
import com.example.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FakeGithubApi @Inject constructor() : GithubApi {

  var repos = listOf<RepoApiModel>()

  override suspend fun getTopRepositories(): TopReposSearchResult {
    return TopReposSearchResult(repos)
  }

}
