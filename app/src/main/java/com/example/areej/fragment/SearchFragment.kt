package com.example.areej.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.areej.R
import com.example.areej.adapter.APIRecycleViewAdapter
import com.example.areej.api.AIPInterface
import com.example.areej.api.APIClient
import com.example.areej.databinding.FragmentHomeBinding
import com.example.areej.databinding.FragmentSearchBinding
import com.example.areej.tvshow.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {
    private lateinit var adapter : APIRecycleViewAdapter
    private lateinit var tvShow : TvShow
    lateinit var binding: FragmentSearchBinding
    var text = "girls"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater , container , false)
        val view = binding.root
        tvShow = TvShow()
        adapter = APIRecycleViewAdapter(activity?.applicationContext)
        binding.rvApi.adapter = adapter
        binding.rvApi.layoutManager = LinearLayoutManager(activity)

        binding.btSearch.setOnClickListener {
             text = binding.etSearch.text.toString()
            if(text.isEmpty())
                Toast.makeText(activity?.applicationContext,"Please write something", Toast.LENGTH_LONG ).show()
            else
                readData(text.trim())
        }

        readData(text)

        return view
    }

    private fun readData(text: String) {
        val apiInterface = APIClient().tvShows()?.create(AIPInterface::class.java)
        apiInterface?.getShows(text)?.enqueue(object :Callback<TvShow>{
            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                try{
                tvShow = response.body()!!
                adapter.update(tvShow)
                }
                catch (e : Exception){
                    Log.e("CATCH", "$e")
                }

            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                Log.e("onFailure readData", t.localizedMessage)
            }

        })
    }


}