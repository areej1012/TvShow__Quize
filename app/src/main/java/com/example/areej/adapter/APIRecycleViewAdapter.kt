package com.example.areej.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.areej.R
import com.example.areej.databinding.CardCellApiBinding
import com.example.areej.db.ShowsDatabase
import com.example.areej.db.TvShowsDB
import com.example.areej.tvshow.TvShow
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class APIRecycleViewAdapter(private val context: Context?) :
    RecyclerView.Adapter<APIRecycleViewAdapter.ItemHolder>() {
    class ItemHolder(val binding: CardCellApiBinding) : RecyclerView.ViewHolder(binding.root)

    private var arrayShow: TvShow = TvShow()
    private val tvShowDao by lazy { ShowsDatabase.getDatabase(context!!).tvShowsDao() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            CardCellApiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val show = arrayShow[position].show
        var image: String? = null
        var summary : String? = null
        holder.binding.apply {
            title.text = show.name
            try {
                Picasso.with(context)
                    .load(show.image.medium)
                    .into(poster)

            } catch (e: Exception) {
                poster.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
            }

            holder.binding.cardView.setOnClickListener {
                image = try {
                    show.image.medium
                } catch (e: Exception) {
                    null

                }
                summary = try {
                    show.summary
                } catch (e: Exception) {
                    Log.e("cat su", e.localizedMessage)
                    null
                }
                val tvShow = TvShowsDB(null, show.name, summary, show.language, image)
                CoroutineScope(IO).launch {
                    tvShowDao.addShow(tvShow)
                }
                Toast.makeText(context, "Tv Show Added", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int = arrayShow.size

    fun update(newArray: TvShow) {
        arrayShow = newArray
        notifyDataSetChanged()

    }
}