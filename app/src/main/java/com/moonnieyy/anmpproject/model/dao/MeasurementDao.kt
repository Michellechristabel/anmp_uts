package com.moonnieyy.anmpproject.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moonnieyy.anmpproject.model.Ukur
//import com.moonnieyy.anmpproject.model.entity.MeasurementEntity

@Dao
interface MeasurementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ukur: Ukur)

    @Query("SELECT * FROM ukur ORDER BY id DESC")
    fun selectAll(): List<Ukur>
}