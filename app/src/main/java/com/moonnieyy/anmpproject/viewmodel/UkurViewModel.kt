package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.io.FileOutputStream

class UkurViewModel(app: Application) : AndroidViewModel(app) {
    val beratLD = MutableLiveData<String>()
    val tinggiLD = MutableLiveData<String>()
    val usiaLD = MutableLiveData<String>()
    val saveSuccessLD = MutableLiveData<Boolean>()

    private val TAG = "UkurViewModel"

    // Simpan data ke file lokal
    fun simpanData(berat: String, tinggi: String, usia: String) {
        try {
            val file = File(getApplication<Application>().filesDir, "data_ukur.txt")
            val content = "Berat: $berat, Tinggi: $tinggi, Usia: $usia\n"
            FileOutputStream(file, true).bufferedWriter().use {
                it.write(content)
            }
            saveSuccessLD.value = true
            Log.d(TAG, "Data tersimpan: $content")
        } catch (e: Exception) {
            saveSuccessLD.value = false
            Log.e(TAG, "Gagal simpan data: ${e.message}")
        }
    }
}
