package com.moonnieyy.anmpproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ukur(
    @ColumnInfo(name = "weight")
    var weight: String = "",
    @ColumnInfo(name = "height")
    var height: String = "",
    @ColumnInfo(name = "age")
    var age: String = "",
    @ColumnInfo(name = "created_at")
    var createdAt: Long = System.currentTimeMillis()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
