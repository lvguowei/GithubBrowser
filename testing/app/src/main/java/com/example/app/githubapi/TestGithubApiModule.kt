package com.example.app.githubapi

import com.example.githubapi.GithubApi
import dagger.Binds
import dagger.Module

@Module
interface TestGithubApiModule {

  @Binds
  fun bindGithubApi(fakeGithubApi: FakeGithubApi): GithubApi
}
