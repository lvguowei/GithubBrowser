package com.example.home

import com.example.appdeps.ApplicationDeps
import com.example.appdeps.applicationDeps
import com.example.di.component.getComponent
import com.example.di.scope.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = [ApplicationDeps::class], modules = [HomeModule::class])
interface HomeComponent {

  fun inject(homeFragment: HomeFragment)

  @Component.Factory
  interface Factory {
    fun create(applicationDeps: ApplicationDeps): HomeComponent
  }
}

fun HomeFragment.inject() {
  getComponent {
    DaggerHomeComponent.factory().create(requireContext().applicationDeps())
  }.inject(
    this
  )

}
