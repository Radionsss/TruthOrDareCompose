package com.example.truthordarecompose.domain.usecases.app_entry

import com.example.truthordarecompose.domain.manger.LocalUserManger
import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadTypeMode @Inject constructor(
    private val localUserManger: LocalUserManger
) {

    operator fun invoke(): Flow<TypeMode?> {
        return localUserManger.readTypeMode()
    }

}