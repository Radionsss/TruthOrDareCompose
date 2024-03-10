package com.example.truthordarecompose.domain.manger

import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode
import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun saveAppEntry()

    suspend fun saveTypeMode(typeMode: TypeMode)

    fun readAppEntry(): Flow<Boolean>
    fun readTypeMode(): Flow<TypeMode?>

}