package com.example.areej.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.areej.ViewModel.MyViewModel
import com.example.areej.adapter.SaveShowAdapter
import com.example.areej.databinding.FragmentSaveLocalBinding
import com.example.areej.db.ShowsDatabase


class SaveLocalFragment : Fragment() {
    lateinit var binding: FragmentSaveLocalBinding
    private lateinit var viewModel: MyViewModel
    private val tvShowDao by lazy {
        ShowsDatabase.getDatabase(activity?.applicationContext!!).tvShowsDao()
    }

    private lateinit var adapter: SaveShowAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSaveLocalBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        adapter = SaveShowAdapter(viewModel)
        binding.rvDb.adapter = adapter
        binding.rvDb.layoutManager = LinearLayoutManager(activity)
        readDB()
        return view
    }

    private fun readDB() {
        viewModel.getTVShow().observe(viewLifecycleOwner, { tvShows ->
            adapter.update(tvShows)

        })
    }
}