package com.bassem.appstore.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bassem.appstore.data.models.AppsDetails
import kotlinx.coroutines.flow.Flow
@Dao
interface AppsDao {
    @Upsert
    suspend fun upsertAll(plants: List<AppsDetails>)

    @Query("SELECT * FROM apps")
    fun getApps(): Flow<List<AppsDetails>>

    @Query("DELETE  FROM apps")
   suspend fun deleteAll()
}
