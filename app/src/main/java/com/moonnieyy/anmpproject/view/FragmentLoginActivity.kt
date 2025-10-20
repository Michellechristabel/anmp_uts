package com.moonnieyy.anmpproject.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.FragmentLoginActivityBinding
import com.moonnieyy.anmpproject.viewmodel.LoginViewModel

class FragmentLoginActivity : Fragment() {
    private lateinit var binding: FragmentLoginActivityBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val sharedPreferences = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)


        if (isLoggedIn) {
            findNavController().navigate(R.id.toFragmentUkur)
            return
        }

        // button login
        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Lengkapi semua data!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.loginUser(email, password)

            }
        }

        // button register
        viewModel.loginResultLD.observe(viewLifecycleOwner) { response ->
            if (response != null && response.optBoolean("success") == true) {
                Toast.makeText(requireContext(), "Login berhasil!", Toast.LENGTH_SHORT).show()

                // simpan status login ke sharedPreferences
                val sharedPreferences =
                    requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putBoolean("isLoggedIn", true)
                    apply()
                }

                // arahkan ke main activity
                findNavController().navigate(R.id.toFragmentUkur)
            } else {
                Toast.makeText(requireContext(), "Login gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}