package com.example.githubapi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserApiModel(
  val id: Long, val login: String
)
