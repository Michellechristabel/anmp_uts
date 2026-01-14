package com.moonnieyy.anmpproject.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moonnieyy.anmpproject.model.dao.MeasurementDao
import com.moonnieyy.anmpproject.model.dao.ProfileDao
import com.moonnieyy.anmpproject.model.entity.MeasurementEntity
import com.moonnieyy.anmpproject.model.entity.ProfileEntity

@Database(
    entities = [MeasurementEntity::class, ProfileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
    abstract fun profileDao(): ProfileDao

    companion object
}