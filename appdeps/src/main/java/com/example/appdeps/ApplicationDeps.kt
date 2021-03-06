package com.example.appdeps

import android.content.Context
import com.example.repository.AppRepository

interface ApplicationDeps {
  fun appRepository(): AppRepository
}

fun Context.applicationDeps(): ApplicationDeps {
  return (applicationContext as? HasApplicationDeps)?.getApplicationDeps()
    ?: throw IllegalArgumentException("Application must implement HasApplicationDeps")
}
