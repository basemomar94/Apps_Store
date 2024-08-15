package com.bassem.appstore.utilities

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.bassem.appstore.R
import com.bassem.appstore.workers.NewAppsWorker
import java.util.concurrent.TimeUnit


fun Context.scheduleHourlyWork() {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
    val workRequest =
        PeriodicWorkRequestBuilder<NewAppsWorker>(30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

    val workManager = WorkManager.getInstance(this)
    workManager.enqueueUniquePeriodicWork(
        "NewAppWorker",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}
