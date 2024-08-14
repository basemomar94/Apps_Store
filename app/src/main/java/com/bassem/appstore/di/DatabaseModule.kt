
package com.bassem.appstore.di
import android.content.Context
import com.bassem.appstore.data.AppDatabase
import com.bassem.appstore.data.AppsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideAppsDao(appDatabase: AppDatabase): AppsDao {
        return appDatabase.appsDao()
    }
}
