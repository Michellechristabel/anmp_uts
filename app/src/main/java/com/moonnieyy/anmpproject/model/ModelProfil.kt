package com.moonnieyy.anmpproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Akun(
    @ColumnInfo(name="name")
    var name:String?,
    @ColumnInfo(name="birthdate")
    var birthdate:String?,
    @ColumnInfo(name="gender")
    var gender:String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
