package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.util.FileHelper

class ProfilViewModel(app: Application) : AndroidViewModel(app) {
    val nama = MutableLiveData<String>()
    val tanggal = MutableLiveData<String>()
    val gender = MutableLiveData<String>()

    private val PREFS_NAME = "ProfilAnakPrefs"
    private val fileHelper = FileHelper(getApplication())

    fun simpanData(namaAnak: String, tanggalLahir: String, jenisKelamin: String) {
        val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("nama", namaAnak)
        editor.putString("tanggal", tanggalLahir)
        editor.putString("gender", jenisKelamin)
        editor.apply()

        // update LiveData biar observer di fragment ikut update
        nama.value = namaAnak
        tanggal.value = tanggalLahir
        gender.value = jenisKelamin

        // 获取当前登录用户的ID
        val userPrefs = getApplication<Application>().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val userId = userPrefs.getString("userId", "") ?: ""

        // juga simpan ke file (backup lokal)
        val data = "$userId;$namaAnak;$tanggalLahir;$jenisKelamin"
        fileHelper.writeToFile(data)
    }

    fun loadData() {
        val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        nama.value = prefs.getString("nama", "")
        tanggal.value = prefs.getString("tanggal", "")
        gender.value = prefs.getString("gender", "")
    }

    // Ambil data profil berdasarkan userId dari file
    fun getUserProfile(userId: String) {
        val data = fileHelper.readFromFile()
        if (data.isNotEmpty()) {
            val parts = data.split(";")
            if (parts.size >= 4 && parts[0] == userId) {
                nama.value = parts[1]
                tanggal.value = parts[2]
                gender.value = parts[3]
            }
        }
    }

    // 添加一个方法来获取当前登录用户的ID
    fun getCurrentUserId(): String {
        val userPrefs = getApplication<Application>().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        return userPrefs.getString("userId", "") ?: ""
    }
}