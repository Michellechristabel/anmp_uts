package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.util.FileHelper
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
//            val file = File(getApplication<Application>().filesDir, "fileData.txt")
            val fh = FileHelper(getApplication())
            val old = fh.readFromFile().trimEnd()
            val content = "Berat: $berat, Tinggi: $tinggi, Usia: $usia\n"
            val joined = if (old.isBlank()) content else old + "\n" + content
            fh.writeToFile(joined)

//            FileOutputStream(file, true).bufferedWriter().use {
//                it.write(content)
//            }
            saveSuccessLD.value = true
            Log.d(TAG, "Data tersimpan: $content")
        } catch (e: Exception) {
            saveSuccessLD.value = false
            Log.e(TAG, "Gagal simpan data: ${e.message}")
        }
    }
//    fun testSaveFile() {
//        val fileHelper = FileHelper(getApplication())
//        fileHelper.writeToFile("Hello World")
//        val content = fileHelper.readFromFile()
//        Log.d("print_file", content)
//        Log.d("print_file", fileHelper.getFilePath())
//    }
}
