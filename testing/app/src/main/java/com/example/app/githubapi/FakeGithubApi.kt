package com.example.app.githubapi

import com.example.githubapi.GithubApi
import com.example.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FakeGithubApi @Inject constructor() : GithubApi {

  var repos = mutableListOf<RepoApiModel>()

  override fun getTopRepositories(): List<RepoApiModel> {
    return repos
  }

}
