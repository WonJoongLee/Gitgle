package com.wonjoong.data.di

import android.content.Context
import androidx.room.Room
import com.wonjoong.data.local.GitgleDAO
import com.wonjoong.data.local.GitgleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideGitgleDatabase(@ApplicationContext applicationContext: Context): GitgleDatabase {
        return Room.databaseBuilder(
            applicationContext,
            GitgleDatabase::class.java,
            "gitgle.db"
        ).build()
    }

    @Provides
    fun provideGitgleDAO(gitgleDatabase: GitgleDatabase): GitgleDAO {
        return gitgleDatabase.gitgleDAO()
    }
}