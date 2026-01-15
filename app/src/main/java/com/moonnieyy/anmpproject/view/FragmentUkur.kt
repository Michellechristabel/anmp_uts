package com.moonnieyy.anmpproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moonnieyy.anmpproject.databinding.FragmentUkurBinding
import com.moonnieyy.anmpproject.model.Ukur
import com.moonnieyy.anmpproject.viewmodel.UkurViewModel

class FragmentUkur : Fragment(), UkurListener {

    private lateinit var binding: FragmentUkurBinding
    private lateinit var viewModel: UkurViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUkurBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[UkurViewModel::class.java]


        binding.ukur = Ukur("", "", "")
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listener = this
    }

    override fun onSaveClick(v: View) {
        // Ambil data terbaru dari binding
        val dataUkur = binding.ukur

        if (dataUkur != null &&
            dataUkur.weight.isNotEmpty() &&
            dataUkur.height.isNotEmpty() &&
            dataUkur.age.isNotEmpty()
        ) {
            viewModel.simpanData(dataUkur)
            Toast.makeText(context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            binding.ukur = Ukur("", "", "")
        } else {
            Toast.makeText(context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
        }
    }
}