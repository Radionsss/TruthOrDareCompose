package com.example.truthordarecompose.domain.usecases.app_entry

import com.example.truthordarecompose.domain.manger.LocalUserManger
import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode
import javax.inject.Inject

class SaveTypeMode @Inject constructor(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(typeMode: TypeMode){
        localUserManger.saveTypeMode(typeMode)
    }

}