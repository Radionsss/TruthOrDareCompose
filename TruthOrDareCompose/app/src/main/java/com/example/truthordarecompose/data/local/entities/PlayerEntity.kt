package com.example.truthordarecompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val name:String,
    val genderMan:Boolean=true
)
