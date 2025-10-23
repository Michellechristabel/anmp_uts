package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val registerResultLD = MutableLiveData<JSONObject>()
    val registerErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volley_tag"
    private var queue = Volley.newRequestQueue(getApplication())

    fun registerUser(name: String, email: String, password: String, confirmPassword: String) {
        loadingLD.value = true
        registerErrorLD.value = false

        val url = "https://www.jsonkeeper.com/b/LLMW"


        val stringRequest = object : StringRequest(
            Request.Method.GET,
            url,
            { response ->
                try {

                    val user = JSONObject().apply {
                        put("name", name)
                        put("email", email)
                    }

                    val result = JSONObject().apply {
                        put("success", true)
                        put("message", "Registration successful.")
                        put("user", user)
                    }

                    registerResultLD.value = result
                    loadingLD.value = false
                    Log.d(TAG, "Register success: $result")

                } catch (e: Exception) {
                    registerErrorLD.value = true
                    loadingLD.value = false
                    Log.e(TAG, "Error parsing register response", e)
                }
            },
            { error ->
                registerErrorLD.value = true
                loadingLD.value = false
                Log.e(TAG, "Volley error: ${error.message}")
            }
        ) {}
        stringRequest.tag = TAG
        queue.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue.cancelAll(TAG)
    }

}