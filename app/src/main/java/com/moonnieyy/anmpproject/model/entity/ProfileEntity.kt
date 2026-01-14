package com.moonnieyy.anmpproject.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val name: String,
    val birthDate: String,
    val gender: String
)