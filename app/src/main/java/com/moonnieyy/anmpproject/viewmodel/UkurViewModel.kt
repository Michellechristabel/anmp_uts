package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.model.entity.MeasurementEntity
import com.moonnieyy.anmpproject.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UkurViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {
    val beratLD = MutableLiveData<String>()
    val tinggiLD = MutableLiveData<String>()
    val usiaLD = MutableLiveData<String>()
    val saveSuccessLD = MutableLiveData<Boolean>()

    private val TAG = "UkurViewModel"

    private val job = Job()
    override val coroutineContext = job + Dispatchers.IO

    fun simpanData(berat: String, tinggi: String, usia: String) {
        launch {
            try {
                val weight = berat.toInt()
                val height = tinggi.toInt()
                val age = usia.toInt()
                val db = buildDb(getApplication())
                db.measurementDao().insert(
                    MeasurementEntity(weight = weight, height = height, age = age)
                )
                saveSuccessLD.postValue(true)
                Log.d(TAG, "Data tersimpan ke DB: w=$weight, h=$height, a=$age")
            } catch (e: Exception) {
                saveSuccessLD.postValue(false)
                Log.e(TAG, "Gagal simpan data: ${e.message}")
            }
        }
    }
}
