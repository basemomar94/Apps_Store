package com.bassem.appstore.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bassem.appstore.data.AppDatabase
import com.bassem.appstore.data.AppsDao
import com.bassem.appstore.data.models.AppsDetails
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class AppDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var appDao: AppsDao
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()
        appDao = db.appsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndReadApp() = runBlocking {
        val appName = "test app"
        val app = AppsDetails(
            keyId = 1,
            name = appName
        )
        appDao.upsertAll(listOf(app))
        val apps = appDao.getApps().first()
        Assert.assertEquals(appName, apps.first().name)

    }

    @Test
    fun insertAndReadMultiApp() = runBlocking {
        val appName = "test app"
        val app1 = AppsDetails(
            keyId = 1,
            name = appName
        )

        val app2 = AppsDetails(
            keyId = 2,
            name = appName
        )

        val app3 = AppsDetails(
            keyId = 3,
            name = appName
        )
        appDao.upsertAll(listOf(app1, app2, app3))
        val apps = appDao.getApps().first()
        Assert.assertEquals(3, apps.size)

    }

    @Test
    fun deleteAllData()= runBlocking {
        val appName = "test app"
        val app = AppsDetails(
            keyId = 1,
            name = appName
        )
        appDao.upsertAll(listOf(app))
        appDao.deleteAll()
        val apps = appDao.getApps().first()
        Assert.assertEquals(0,apps.size)
    }
}