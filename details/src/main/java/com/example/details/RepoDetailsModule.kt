package com.example.details

import androidx.lifecycle.ViewModel
import com.example.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RepoDetailsModule {

  @Binds
  @IntoMap
  @ViewModelKey(RepoDetailsViewModel::class)
  fun bindViewDetailsViewModel(repoDetailsViewModel: RepoDetailsViewModel): ViewModel

}
