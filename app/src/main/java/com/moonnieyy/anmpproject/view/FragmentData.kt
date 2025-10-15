package com.moonnieyy.anmpproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonnieyy.anmpproject.databinding.FragmentDataBinding
import com.moonnieyy.anmpproject.viewmodel.DataViewModel

class FragmentData : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private lateinit var viewModel: DataViewModel
    private val dataListAdapter = DataListAdapter(arrayListOf())

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
        binding.recViewData.layoutManager = LinearLayoutManager(context)
        binding.recViewData.adapter = dataListAdapter

        // pull-to-refresh
        binding.refreshLayout.setOnRefreshListener {
            binding.recViewData.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.fetchData()
            binding.refreshLayout.isRefreshing = false
        }

        // observe ViewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dataUkurLD.observe(viewLifecycleOwner, Observer {
            dataListAdapter.updateDataList(it)
            binding.recViewData.visibility = View.VISIBLE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.GONE
        })

        viewModel.loadErrorLD.observe(viewLifecycleOwner, Observer { isError ->
            binding.txtError.visibility = if (isError) View.VISIBLE else View.GONE
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }
}
