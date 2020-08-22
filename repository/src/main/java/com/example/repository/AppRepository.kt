package com.example.repository

import com.example.githubapi.GithubApi
import com.example.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
  private val githubApi: GithubApi
) {
  fun getTopRepos(): List<RepoApiModel> {
    return githubApi.getTopRepositories()
  }
}
