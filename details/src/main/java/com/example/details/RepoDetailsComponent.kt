package com.example.details

import com.example.appdeps.ApplicationDeps
import com.example.appdeps.applicationDeps
import com.example.di.component.getComponent
import com.example.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@ScreenScope
@Component(dependencies = [ApplicationDeps::class], modules = [RepoDetailsModule::class])
interface RepoDetailsComponent {

  fun inject(repoDetailsFragment: RepoDetailsFragment)

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance @Named("repo_owner") repoOwner: String,
      @BindsInstance @Named("repo_name") repoName: String,
      applicationDeps: ApplicationDeps
    ): RepoDetailsComponent
  }
}

fun RepoDetailsFragment.inject() {
  getComponent {
    val repoOwner = arguments?.getString("repo_owner")
      ?: throw NullPointerException("repo_owner must not be null")
    val repoName = arguments?.getString("repo_name")
      ?: throw NullPointerException("repo_name must not be null")

    return@getComponent DaggerRepoDetailsComponent.factory().create(
      repoOwner,
      repoName,
      requireContext().applicationDeps()
    )
  }.inject(this)
}
