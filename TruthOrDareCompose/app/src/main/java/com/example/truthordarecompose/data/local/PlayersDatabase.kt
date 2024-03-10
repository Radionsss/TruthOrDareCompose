package com.example.truthordarecompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.truthordarecompose.data.local.entities.PlayerEntity


@Database(entities = [PlayerEntity::class],version = 1,)
abstract class PlayersDatabase : RoomDatabase() {

    abstract val newsDao: PlayersDao

}