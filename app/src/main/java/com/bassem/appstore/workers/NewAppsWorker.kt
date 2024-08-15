package com.bassem.appstore.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bassem.appstore.utilities.sendBackgroundNotification

class NewAppsWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        context.sendBackgroundNotification()
        Log.i("NewAppsWorker", "sending from worker")
        return Result.success()
    }
}