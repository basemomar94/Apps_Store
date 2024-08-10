package com.bassem.appstore.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bassem.appstore.utilities.sendBackgroundNotification

class NewAppsWorker(val context: Context, val workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        context.sendBackgroundNotification()
        return Result.success()
    }
}