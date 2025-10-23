package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.model.DataUkur
import com.moonnieyy.anmpproject.util.FileHelper
import java.io.File

class DataViewModel(app: Application) : AndroidViewModel(app) {
    val dataUkurLD = MutableLiveData<List<DataUkur>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "DataViewModel"

    private val fh by lazy { FileHelper(getApplication()) }

    fun fetchData() {
        loadingLD.value = true
        loadErrorLD.value = false

        try {
            val fh = FileHelper(getApplication())
            val text = fh.readFromFile()

            val dataList: List<DataUkur> =
                if (text.isBlank()) emptyList()
                else text.lines()
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .mapNotNull { parseDataUkur(it) }

            dataUkurLD.value = dataList
            Log.d(TAG, "Data berhasil dibaca (${dataList.size} baris) dari: ${fh.getFilePath()}")
            loadingLD.value = false
        } catch (e: Exception) {
            loadErrorLD.value = true
            loadingLD.value = false
            Log.e(TAG, "Gagal baca data: ${e.message}")
        }
    }

    private fun parseDataUkur(line: String): DataUkur? {
        return try {
            // Format: "Berat: X, Tinggi: Y, Usia: Z"
            val parts = line.split(",").map { it.trim() }
            if (parts.size < 3) return null

            val berat = parts[0].substringAfter(":").trim().toIntOrNull() ?: return null
            val tinggi = parts[1].substringAfter(":").trim().toIntOrNull() ?: return null
            val usia = parts[2].substringAfter(":").trim().toIntOrNull() ?: return null

            DataUkur(age = usia, height = tinggi, weight = berat)
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing line: $line", e)
            null
        }
    }
}
