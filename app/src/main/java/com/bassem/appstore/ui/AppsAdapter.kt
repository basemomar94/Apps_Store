package com.bassem.appstore.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassem.appstore.data.models.Details
import com.bassem.appstore.databinding.ItemAppBinding
import com.bumptech.glide.Glide

class AppsAdapter(val context: Context,val appsList: List<Details>, val onClick: OnClick) :
    RecyclerView.Adapter<AppsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = appsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = appsList[position]
        holder.binding.apply {
            appName.text = app.name
            Glide.with(context).load(app.graphic).into(appImage)

            appImage.setOnClickListener {
                onClick.onAppClick(app, position)
            }
        }
    }

    interface OnClick {
        fun onAppClick(details: Details, position: Int)

    }
}