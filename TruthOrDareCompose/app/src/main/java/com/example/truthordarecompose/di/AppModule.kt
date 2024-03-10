package com.example.truthordarecompose.di

import android.app.Application
import androidx.room.Room
import com.example.truthordarecompose.data.local.PlayersDao
import com.example.truthordarecompose.data.local.PlayersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): PlayersDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = PlayersDatabase::class.java,
            name = "news_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: PlayersDatabase
    ): PlayersDao = newsDatabase.newsDao

}