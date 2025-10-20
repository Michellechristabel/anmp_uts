package com.moonnieyy.anmpproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.FragmentRegisterActivityBinding
import com.moonnieyy.anmpproject.viewmodel.RegisterViewModel

class FragmentRegisterActivity : Fragment() {
    private lateinit var binding: FragmentRegisterActivityBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        // button sign up
        binding.btnSignUp.setOnClickListener {
            val name = binding.txtSuName.text.toString()
            val email = binding.txtSuEmail.text.toString()
            val password = binding.txtSuPassword.text.toString()
            val confirmPassword = binding.txtSuConfirmPassword.text.toString()

            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Lengkapi semua data!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.registerUser(name, email, password, confirmPassword)
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.registerResultLD.observe(viewLifecycleOwner) { response ->
            if (response?.optBoolean("success") == true) {
                Toast.makeText(requireContext(), "Register berhasil!", Toast.LENGTH_SHORT).show()

                // kembali ke login
                findNavController().navigate(R.id.toFragmentRegister)
            } else if (response != null) {
                Toast.makeText(requireContext(), "Register gagal!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Terjadi kesalahan!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}