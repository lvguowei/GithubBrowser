package com.example.githubapi

import com.example.githubapi.model.ContributorApiModel
import com.example.githubapi.model.RepoApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
  @GET("search/repositories?q=language:kotlin&order=desc&sort=starts")
  suspend fun getTopRepositories(): TopReposSearchResult

  @GET("repos/{owner}/{name}")
  suspend fun getRepo(
    @Path("owner") repoOwner: String,
    @Path("name") repoName: String
  ): RepoApiModel

  @GET("repos/{owner}/{name}/contributors")
  suspend fun getContributors(
    @Path("owner") repoOwner: String,
    @Path("name") repoName: String
  ): List<ContributorApiModel>
}
