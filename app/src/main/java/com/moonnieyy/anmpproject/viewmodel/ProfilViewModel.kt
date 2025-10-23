    package com.moonnieyy.anmpproject.viewmodel

    import android.app.Application
    import android.content.Context
    import androidx.lifecycle.AndroidViewModel
    import androidx.lifecycle.MutableLiveData

    class ProfilViewModel(app: Application) : AndroidViewModel(app) {
        val nama = MutableLiveData<String>()
        val tanggal = MutableLiveData<String>()
        val gender = MutableLiveData<String>()

        private val PREFS_NAME = "ProfilAnakPrefs"

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
        }

        fun loadData() {
            val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            nama.value = prefs.getString("nama", "")
            tanggal.value = prefs.getString("tanggal", "")
            gender.value = prefs.getString("gender", "")
        }
    }
