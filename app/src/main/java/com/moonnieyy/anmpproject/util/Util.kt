package com.moonnieyy.anmpproject.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moonnieyy.anmpproject.model.AppDatabase

const val DB_NAME = "anmp_uas_db"

fun buildDb(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DB_NAME
    ).build()
}

fun buildDB(context: Context): AppDatabase {
    return AppDatabase.buildDatabase(context)
}