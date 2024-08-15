package com.bassem.appstore.data

import android.util.Log
import com.bassem.appstore.api.ApiService
import com.bassem.appstore.data.models.AppsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val service: ApiService,
    private val appsDao: AppsDao
) {

    suspend fun getAppsResult() {
        try {
            val response = service.getAppsList()
            appsDao.deleteAll()
            Log.d("MainRepository", "Response: $response")
            val apps = response.responses.listApps.datasets.all.data.list
            storeApps(apps)

        } catch (e: Exception) {
            Log.e("MainRepository", "Error fetching apps", e)

        }
    }

     fun getAppFromLocal() = appsDao.getApps()

    suspend fun storeApps(apps: List<AppsDetails>) {
        appsDao.upsertAll(apps)
    }
}