package com.example.home

import com.example.home.list.RepoItem

sealed class HomeViewState

object HomeViewStateLoading : HomeViewState()

data class HomeViewStateLoaded(val repos: List<RepoItem>) : HomeViewState()

data class HomeViewStateError(val message: String) : HomeViewState()
