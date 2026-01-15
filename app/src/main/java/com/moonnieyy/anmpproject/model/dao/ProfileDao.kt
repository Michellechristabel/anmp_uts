package com.moonnieyy.anmpproject.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moonnieyy.anmpproject.model.Akun
import com.moonnieyy.anmpproject.model.Profil
//import com.moonnieyy.anmpproject.model.entity.ProfileEntity

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(akun: Akun)

    @Update
    suspend fun update(akun: Akun)

    @Query("SELECT * FROM akun WHERE id = 1")
    suspend fun getProfile(): Akun?
}
