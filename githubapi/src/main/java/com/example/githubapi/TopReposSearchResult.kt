package com.example.githubapi

import com.example.githubapi.model.RepoApiModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopReposSearchResult(
  val items: List<RepoApiModel>
)
