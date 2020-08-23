package com.example.githubbrowser.application

import android.app.Application
import com.example.appdeps.ApplicationDeps
import com.example.appdeps.HasApplicationDeps

class GithubBrowserApp : Application(), HasApplicationDeps {

  private val appComponent by lazy {
    DaggerApplicationComponent.factory().create(this)
  }

  override fun getApplicationDeps(): ApplicationDeps {
    return appComponent
  }
}
