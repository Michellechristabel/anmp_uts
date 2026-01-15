package com.moonnieyy.anmpproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object{
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "newappdb")

        operator fun invoke(context: Context){
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}