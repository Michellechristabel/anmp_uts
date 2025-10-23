package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.util.FileHelper
import java.io.File

class DataViewModel(app: Application) : AndroidViewModel(app) {
    val dataUkurLD = MutableLiveData<List<String>>()
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

            val dataList: List<String> =
                if (text.isBlank()) emptyList()
                else text.lines()
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }

            dataUkurLD.value = dataList
            Log.d(TAG, "Data berhasil dibaca (${dataList.size} baris) dari: ${fh.getFilePath()}")
            loadingLD.value = false
        } catch (e: Exception) {
            loadErrorLD.value = true
            loadingLD.value = false
            Log.e(TAG, "Gagal baca data: ${e.message}")
        }
    }
}
