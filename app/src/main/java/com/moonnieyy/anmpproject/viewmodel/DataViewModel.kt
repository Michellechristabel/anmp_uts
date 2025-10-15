package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.io.File

class DataViewModel(app: Application) : AndroidViewModel(app) {
    val dataUkurLD = MutableLiveData<List<String>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "DataViewModel"

    fun fetchData() {
        loadingLD.value = true
        loadErrorLD.value = false

        try {
            val file = File(getApplication<Application>().filesDir, "data_ukur.txt")
            if (file.exists()) {
                val dataList = file.readLines()
                dataUkurLD.value = dataList
                Log.d(TAG, "Data berhasil dibaca: $dataList")
            } else {
                dataUkurLD.value = emptyList()
                Log.d(TAG, "File tidak ditemukan")
            }
            loadingLD.value = false
        } catch (e: Exception) {
            loadErrorLD.value = true
            loadingLD.value = false
            Log.e(TAG, "Gagal baca data: ${e.message}")
        }
    }
}
