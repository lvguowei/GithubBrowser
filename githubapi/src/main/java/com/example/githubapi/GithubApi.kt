package com.example.githubapi

import retrofit2.http.GET

interface GithubApi {
  @GET("search/repositories?q=language:kotlin&order=desc&sort=starts")
  suspend fun getTopRepositories(): TopReposSearchResult
}
