package com.example.details


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.details.databinding.ScreenDetailsBinding
import com.example.di.viewmodel.ViewModelFactory
import javax.inject.Inject

class RepoDetailsFragment : Fragment() {

  companion object {
    fun newInstance(repoOwner: String, repoName: String): RepoDetailsFragment {
      val args = Bundle().apply {
        putString("repo_name", repoName)
        putString("repo_owner", repoOwner)
      }
      return RepoDetailsFragment().apply { arguments = args }
    }
  }

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val viewModel by lazy {
    ViewModelProvider(this, viewModelFactory)[RepoDetailsViewModel::class.java]
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    inject()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = ScreenDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }
}
