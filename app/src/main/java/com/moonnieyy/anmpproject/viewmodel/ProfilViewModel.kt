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
        with(prefs.edit()) {
            putString("nama", namaAnak)
            putString("tanggal", tanggalLahir)
            putString("gender", jenisKelamin)
            apply()
        }


        nama.value = namaAnak
        tanggal.value = tanggalLahir
        gender.value = jenisKelamin


        val userId = "001"
        val dataFile = "$userId;$namaAnak;$tanggalLahir;$jenisKelamin"
        fileHelper.writeToFile(dataFile)
    }


    fun loadData() {
        val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        nama.value = prefs.getString("nama", "")
        tanggal.value = prefs.getString("tanggal", "")
        gender.value = prefs.getString("gender", "")
    }

    fun getUserProfile(userId: String) {
        val data = fileHelper.readFromFile()
        if (data.isNotEmpty()) {
            val parts = data.split(";")
            if (parts.size >= 4 && parts[0] == userId) {
                val savedNama = parts[1]
                val savedTanggal = parts[2]
                val savedGender = parts[3]

                nama.value = savedNama
                tanggal.value = savedTanggal
                gender.value = savedGender
            }
        }
    }
}
