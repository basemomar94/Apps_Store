package com.bassem.appstore.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.bassem.appstore.data.models.AppsDetails
import com.bassem.appstore.databinding.FragmentDetailsBinding
import com.bassem.appstore.utilities.AppConstants
import com.bumptech.glide.Glide

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.arguments?.let { arg ->
            val appAppsDetails = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arg.getParcelable(AppConstants.APP_BUNDLE, AppsDetails::class.java)
            } else {
                arg.getParcelable(AppConstants.APP_BUNDLE)
            }
            appAppsDetails?.let { app ->
                populateAppDetails(app)

            }

        }
        binding.downloadButton.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle("Error")
                .setMessage("You cant download on demo mode").show()
        }
    }

    private fun populateAppDetails(app: AppsDetails) {
        binding.apply {
            appName.text = app.name
            storeName.text = app.store_name
            downloadText.text = buildString {
                append("Downloads ")
                append((app.downloads ?: 0).toString())
            }
            size.text = buildString {
                append(app.size ?: 0)
                append(" mg")
            }
            appVersion.text = buildString {
                append("version ")
                append(app.vername)
            }
            Glide.with(requireContext()).load(app.graphic).into(binding.image)
        }
    }
}