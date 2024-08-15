package com.bassem.appstore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.appstore.data.AppsDao
import com.bassem.appstore.data.MainRepository
import com.bassem.appstore.data.models.AppsDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {
    val appsFlow = mainRepository.getAppFromLocal()

    init {
        getApps()
    }

    private fun getApps() = viewModelScope.launch {
        mainRepository.getAppsResult()
    }


}