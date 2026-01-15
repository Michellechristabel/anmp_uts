package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.model.Ukur
import com.moonnieyy.anmpproject.util.buildDb
import kotlinx.coroutines.*

class UkurViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {

    val saveSuccessLD = MutableLiveData<Boolean>()

    private val job = Job()
    override val coroutineContext = job + Dispatchers.IO

    private val TAG = "UkurViewModel"

    fun simpanData(ukur: Ukur) {
        launch {
            try {
                val db = buildDb(getApplication())
                db.measurementDao().insert(ukur)
                saveSuccessLD.postValue(true)
                Log.d(TAG, "Data tersimpan: $ukur")
            } catch (e: Exception) {
                saveSuccessLD.postValue(false)
                Log.e(TAG, "Gagal simpan data", e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
