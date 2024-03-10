package com.example.truthordarecompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.truthordarecompose.data.local.entities.PlayerEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    suspend fun insert(player: PlayerEntity)


    @Query("DELETE FROM PlayerEntity WHERE id = :entityId")
    suspend fun delete(entityId: Int)

    @Query("SELECT * FROM PlayerEntity")
    fun getPlayers(): Flow<List<PlayerEntity>>

    @Query("UPDATE PlayerEntity SET name = :newText WHERE id = :playerId")
    suspend fun updatePlayerText(playerId: Int, newText: String)

    @Query("UPDATE PlayerEntity SET genderMan = :newGender WHERE id = :playerId")
    suspend fun updatePlayerGender(playerId: Int, newGender: Boolean)
}