package com.moonnieyy.anmpproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.FragmentProfilAnakBinding
import com.moonnieyy.anmpproject.viewmodel.ProfilViewModel

class FragmentProfilAnak : Fragment() {
    private lateinit var binding: FragmentProfilAnakBinding
    private lateinit var viewModel: ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilAnakBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.loadData()

        // Tombol Simpan Data
        binding.btnSimpanProfil.setOnClickListener {
            val nama = binding.inputNamaAnak.text.toString()
            val tanggal = binding.inputTanggalLahir.text.toString()
            val selectedGenderId = binding.radioGroupGender.checkedRadioButtonId

            if (nama.isEmpty() || tanggal.isEmpty() || selectedGenderId == -1) {
                Toast.makeText(context, "Lengkapi semua data!", Toast.LENGTH_SHORT).show()
            } else {
                val gender = view.findViewById<RadioButton>(selectedGenderId).text.toString()
                viewModel.simpanData(nama, tanggal, gender)
                Toast.makeText(context, "Data profil berhasil disimpan", Toast.LENGTH_SHORT).show()

                // Navigasi ke Fragment Data
                findNavController().navigate(R.id.navprofilkedata)
            }
        }

        // Observe data dari ViewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.nama.observe(viewLifecycleOwner, Observer {
            binding.inputNamaAnak.setText(it)
        })
        viewModel.tanggal.observe(viewLifecycleOwner, Observer {
            binding.inputTanggalLahir.setText(it)
        })
        viewModel.gender.observe(viewLifecycleOwner, Observer {
            when (it) {
                "Laki-laki" -> binding.radioLaki.isChecked = true
                "Perempuan" -> binding.radioPerempuan.isChecked = true
            }
        })
    }
}
