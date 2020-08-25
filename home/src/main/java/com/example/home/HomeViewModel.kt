package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.list.RepoItem
import com.example.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val appRepository: AppRepository
) : ViewModel() {

  private val _viewState = MutableLiveData<HomeViewState>(HomeViewStateLoading)
  val viewStateUpdates: LiveData<HomeViewState> = _viewState

  init {
    viewModelScope.launch {
      val topRepos = appRepository.getTopRepos()
      _viewState.value = HomeViewStateLoaded(topRepos.map {
        RepoItem(
          name = it.name,
          description = it.description,
          starsCount = it.stargazersCount,
          forkCount = it.forksCount
        )
      })
    }

  }

}
