package com.example.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.di.viewmodel.ViewModelFactory
import com.example.home.databinding.ScreenHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

  @Inject
  lateinit var appViewModelFactory: ViewModelFactory

  private val homeViewModel: HomeViewModel by lazy {
    ViewModelProvider(this, appViewModelFactory)[HomeViewModel::class.java]
  }

  override fun onAttach(context: Context) {
    inject()
    super.onAttach(context)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = ScreenHomeBinding.inflate(inflater, container, false)
    return binding.root
  }
}
