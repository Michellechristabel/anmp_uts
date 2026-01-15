package com.moonnieyy.anmpproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moonnieyy.anmpproject.model.dao.MeasurementDao
import com.moonnieyy.anmpproject.model.dao.ProfileDao

@Database(
    entities = [Ukur::class, Akun::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
    abstract fun profileDao(): ProfileDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "anmp_uas_db"
            ).build()
    }
}
