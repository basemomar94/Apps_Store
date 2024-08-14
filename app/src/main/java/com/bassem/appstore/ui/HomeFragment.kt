package com.bassem.appstore.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bassem.appstore.R
import com.bassem.appstore.data.models.AppsDetails
import com.bassem.appstore.databinding.FragmentHomeBinding
import com.bassem.appstore.utilities.AppConstants.APP_BUNDLE
import com.bassem.appstore.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), AppsAdapter.OnClick {
    private val viewModel: MainViewModel by viewModels()
    private var mainAppsAdapter: AppsAdapter? = null
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectAppsList()
        Log.v("Home", "on home")
    }

    private fun collectAppsList() = lifecycleScope.launch {
        viewModel.getApps().collect { appsList ->
            populateMainRv(appsList)
        }
    }

    private fun populateMainRv(apps: List<AppsDetails>) {
        mainAppsAdapter = AppsAdapter(requireContext(), apps, this)
        binding.allAppsRv.apply {
            adapter = mainAppsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    override fun onAppClick(appsDetails: AppsDetails, position: Int) {
        val appBundle = Bundle()
        appBundle.putParcelable(APP_BUNDLE, appsDetails)
        findNavController().navigate(R.id.action_home_to_detailsFragment,appBundle)

    }
}