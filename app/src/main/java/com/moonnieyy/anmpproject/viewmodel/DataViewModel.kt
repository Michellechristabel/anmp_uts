package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moonnieyy.anmpproject.model.Ukur
import com.moonnieyy.anmpproject.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DataViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {
    val ukurLD = MutableLiveData<List<Ukur>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "DataViewModel"

    private val job = Job()
    override val coroutineContext = job + Dispatchers.IO

    fun fetchData() {
        loadingLD.postValue(true)
        loadErrorLD.postValue(false)

        launch {
            try {
                val db = buildDb(getApplication())
                val list = db.measurementDao().selectAll()
                ukurLD.postValue(list)
                loadingLD.postValue(false)
                Log.d(TAG, "Loaded ${'$'}{list.size} rows from Room")
            } catch (e: Exception) {
                loadErrorLD.postValue(true)
                loadingLD.postValue(false)
                Log.e(TAG, "Failed to load from Room: ${'$'}{e.message}", e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
