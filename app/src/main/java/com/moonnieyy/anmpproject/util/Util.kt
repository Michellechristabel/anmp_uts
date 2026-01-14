package com.moonnieyy.anmpproject.util

import android.content.Context
import androidx.room.Room
import com.moonnieyy.anmpproject.model.AppDatabase

const val DB_NAME = "anmp_uts_db"

fun buildDb(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DB_NAME
    ).build()
}