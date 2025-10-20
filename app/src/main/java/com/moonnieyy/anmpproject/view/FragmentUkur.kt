package com.moonnieyy.anmpproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.FragmentUkurBinding
import com.moonnieyy.anmpproject.viewmodel.UkurViewModel

class FragmentUkur : Fragment() {
    private lateinit var binding: FragmentUkurBinding
    private lateinit var viewModel: UkurViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUkurBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UkurViewModel::class.java)
        
        binding.btnTambahData.setOnClickListener {
            val berat = binding.inputBeratBadan.text.toString()
            val tinggi = binding.inputTinggiBadan.text.toString()
            val usia = binding.inputUsia.text.toString()


            if (berat.isNotEmpty() && tinggi.isNotEmpty() && usia.isNotEmpty()) {
                viewModel.simpanData(berat, tinggi, usia)
                Toast.makeText(context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()

                binding.inputBeratBadan.text?.clear()
                binding.inputTinggiBadan.text?.clear()
                binding.inputUsia.text?.clear()

                findNavController().navigate(R.id.navprofilkeanak)

            } else {
                Toast.makeText(context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
