package com.bassem.appstore.viewmodels

import androidx.lifecycle.ViewModel
import com.bassem.appstore.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getApps() = mainRepository.getAppsResult()
}