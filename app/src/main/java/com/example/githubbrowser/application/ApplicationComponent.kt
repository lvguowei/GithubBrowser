package com.example.githubbrowser.application

import android.content.Context
import com.example.githubapi.GithubApiModule
import com.example.repository.AppRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GithubApiModule::class])
interface ApplicationComponent {

  fun appRepository(): AppRepository

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): ApplicationComponent
  }

}
