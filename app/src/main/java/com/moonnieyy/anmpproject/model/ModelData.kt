package com.moonnieyy.anmpproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ukur(
    @ColumnInfo(name="weight")
    var weight:Int?,
    @ColumnInfo(name="height")
    var height:Int?,
    @ColumnInfo(name="age")
    var age:Int?,
    @ColumnInfo(name = "created_at")
    var createdAt: Long = System.currentTimeMillis()
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}