package com.moonnieyy.anmpproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moonnieyy.anmpproject.model.dao.MeasurementDao
import com.moonnieyy.anmpproject.model.dao.ProfileDao
//import com.moonnieyy.anmpproject.model.entity.MeasurementEntity
//import com.moonnieyy.anmpproject.model.entity.ProfileEntity

@Database(
    entities = [DataUkur::class, Profil::class],
    version = 1,
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
            "newappdb").build()

        operator fun invoke(context: Context): AppDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "newappdb"
                ).build().also {
                    instance = it
                }
            }
        }
    }
}
