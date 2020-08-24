package com.example.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app.TestApplication
import com.example.githubapi.model.RepoApiModel
import com.example.githubapi.model.UserApiModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

  @Before
  fun setUp() {
    val githubApi = TestApplication.component.githubApi()
    githubApi.repos = listOf(
      RepoApiModel(
        id = 1L,
        name = "Home Fragment",
        description = "Mock Repo Description",
        owner = UserApiModel(id = 1L, login = "dagger"),
        stargazersCount = 1,
        forksCount = 1,
        contributorsUrl = "",
        createdDate = "1/1/2020",
        updatedDate = "1/1/2020"
      )
    )
  }

  @Test
  fun reposDisplayed() {
    launchFragmentInContainer<HomeFragment>()
    Espresso.onView(withId(R.id.repo_name)).check(matches(withText("Home Fragment")))
  }
}
