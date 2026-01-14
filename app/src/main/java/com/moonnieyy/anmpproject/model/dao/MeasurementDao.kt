package com.moonnieyy.anmpproject.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moonnieyy.anmpproject.model.entity.MeasurementEntity

@Dao
interface MeasurementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(measurement: MeasurementEntity)

    @Query("SELECT * FROM measurement ORDER BY id DESC")
    suspend fun selectAll(): List<MeasurementEntity>
}