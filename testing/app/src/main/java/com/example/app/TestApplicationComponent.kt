package com.example.app

import android.content.Context
import com.example.app.githubapi.FakeGithubApi
import com.example.app.githubapi.TestGithubApiModule
import com.example.appcomponent.ApplicationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestGithubApiModule::class])
interface TestApplicationComponent : ApplicationComponent {

  fun fakeGithubApi(): FakeGithubApi

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): TestApplicationComponent
  }

}
