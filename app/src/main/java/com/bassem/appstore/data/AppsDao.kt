package com.bassem.appstore.data

import androidx.room.Query
import androidx.room.Upsert
import com.bassem.appstore.data.models.AppsDetails
import kotlinx.coroutines.flow.Flow

interface AppsDao {
    @Upsert
    suspend fun upsertAll(plants: List<AppsDetails>)

    @Query("SELECT * FROM apps")
    fun getApps(): Flow<List<AppsDetails>>
}
