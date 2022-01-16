package com.example.areej.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.areej.R
import com.example.areej.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class homeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater , container , false)
        val view = binding.root
        binding.btSearch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.btSave.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_saveLocalFragment)
        }
        return view

    }


}