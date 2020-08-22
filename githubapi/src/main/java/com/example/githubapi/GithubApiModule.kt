package com.example.githubapi

import dagger.Binds
import dagger.Module

@Module
interface GithubApiModule {

  @Binds
  fun bindGithubApi(mockGithubApi: MockGithubApi): GithubApi

}
