package com.moonnieyy.anmpproject.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.Volley
import com.moonnieyy.anmpproject.databinding.ActivityLoginBinding
import com.moonnieyy.anmpproject.viewmodel.LoginViewModel
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // cek user udah pernah login/belum
        val sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // Jika sudah login, arahkan ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // kalau blm login, tampilkan halaman login
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        observeViewModel()
    }

    private fun observeViewModel() {
//        viewModel.loginResult.observe(this) { response ->
//            if(response?.getBoolean("success") == true) {)

    }
}