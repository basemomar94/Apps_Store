package com.bassem.appstore.data

import android.util.Log
import com.bassem.appstore.api.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val service: ApiService) {

    fun getAppsResult() = flow {
        try {
            val response = service.getAppsList()
            Log.d("MainRepository", "Response: $response")

           val apps = response.responses.listApps.datasets.all.data.list
            emit(apps)
        } catch (e: Exception) {
            Log.e("MainRepository", "Error fetching apps", e)
            emit(emptyList())
        }
    }
}