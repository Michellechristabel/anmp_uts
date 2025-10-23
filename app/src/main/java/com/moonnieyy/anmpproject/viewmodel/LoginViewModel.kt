package com.moonnieyy.anmpproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val loginResultLD = MutableLiveData<JSONObject>()
    val loginErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "volley_tag"

    private var queue = Volley.newRequestQueue(getApplication())

    fun loginUser(email: String, password: String) {
        loadingLD.value = true
        loginErrorLD.value = false

        val url = "https://www.jsonkeeper.com/b/LLMW"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                try {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        val user = JSONObject().apply {
                            put("email", email)
                            put("name", "Test User")
                        }
                        val result = JSONObject().apply {
                            put("success", true)
                            put("user", user)
                        }
                        loginResultLD.value = result
                    } else {
                        val fail = JSONObject().apply { put("success", false) }
                        loginResultLD.value = fail
                    }

                    loadingLD.value = false
                } catch (e: Exception) {
                    loginErrorLD.value = true
                    loadingLD.value = false
                }
            },
            { error ->
                loginErrorLD.value = true
                loadingLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}