package com.moonnieyy.anmpproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.FragmentDataBinding
//import com.moonnieyy.anmpproject.model.DataUkur
import com.moonnieyy.anmpproject.model.Ukur
import com.moonnieyy.anmpproject.viewmodel.DataViewModel

class FragmentData : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private lateinit var viewModel: DataViewModel
    private val dataListAdapter = DataListAdapter(arrayListOf<Ukur>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init ViewModel
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        viewModel.fetchData()
        // setup RecyclerView
        binding.recyclerViewData.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewData.adapter = dataListAdapter
        // observe ViewModel
        observeViewModel()
        viewModel.fetchData()
    }

    private fun observeViewModel() {
        viewModel.ukurLD.observe(viewLifecycleOwner, Observer {
            dataListAdapter.updateDataList(it)
            binding.recyclerViewData.visibility = View.VISIBLE
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

}
