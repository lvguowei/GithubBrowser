package com.example.details


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.details.databinding.ScreenDetailsBinding
import com.example.details.list.ContributorsAdapter
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

    binding.contributorList.apply {
      adapter = ContributorsAdapter()
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      )
    }

    viewModel.repoInfoUpdates.observe(viewLifecycleOwner, Observer {
      when (it) {
        is RepoInfoViewStateLoading -> handleInfoLoadingState(binding)
        is RepoInfoViewStateLoaded -> handleInfoLoadedState(binding, it)
      }
    })

    viewModel.contributorsUpdates.observe(viewLifecycleOwner, Observer {
      when (it) {
        is RepoContributorsViewStateLoading -> handleContributorsLoadingState(binding)
        is RepoContributorsViewStateLoaded -> handleContributorsLoadedState(binding, it)
      }
    })

    return binding.root
  }

  private fun handleContributorsLoadedState(
    binding: ScreenDetailsBinding,
    state: RepoContributorsViewStateLoaded
  ) {
    binding.contributorLoadingIndicator.visibility = View.GONE
    binding.contributorList.visibility = View.VISIBLE

    (binding.contributorList.adapter as ContributorsAdapter).setContributors(state.contributors)
  }

  private fun handleContributorsLoadingState(binding: ScreenDetailsBinding) {
    binding.contributorLoadingIndicator.visibility = View.VISIBLE
    binding.contributorList.visibility = View.GONE
  }

  private fun handleInfoLoadedState(binding: ScreenDetailsBinding, state: RepoInfoViewStateLoaded) {
    binding.detailsLoadingIndicator.visibility = View.GONE
    binding.toggleInfoTextVisibility(View.VISIBLE)
    binding.repoName.text = state.repoName
    binding.repoDescription.text = state.repoDescription
    binding.creationDate.text = state.createdDate
    binding.updatedDate.text = state.updatedDate
  }

  private fun handleInfoLoadingState(binding: ScreenDetailsBinding) {
    binding.detailsLoadingIndicator.visibility = View.VISIBLE
    binding.toggleInfoTextVisibility(View.GONE)
  }

  private fun ScreenDetailsBinding.toggleInfoTextVisibility(visibility: Int) {
    repoName.visibility = visibility
    repoDescription.visibility = visibility
    creationDateLabel.visibility = visibility
    creationDate.visibility = visibility
    updatedDateLabel.visibility = visibility
    updatedDate.visibility = visibility
  }
}
