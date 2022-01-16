package com.example.areej.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.areej.R
import com.example.areej.ViewModel.MyViewModel
import com.example.areej.databinding.CardCellSaveBinding
import com.example.areej.db.TvShowsDB
import com.squareup.picasso.Picasso

class SaveShowAdapter(val myView: MyViewModel) :
    RecyclerView.Adapter<SaveShowAdapter.ItemHolder>() {
    class ItemHolder(val binding: CardCellSaveBinding) : RecyclerView.ViewHolder(binding.root)

    private var arrayShow: List<TvShowsDB> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            CardCellSaveBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val tvShow = arrayShow[position]
        holder.binding.apply {
            tvTitle.text = tvShow.title
            tvlang.text = tvShow.language

            if (tvShow.image == null) {
                poster.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
            }
            else {
                Picasso.with(holder.itemView.context).load(tvShow.image).into(poster)
                Log.e("catch", tvShow.image.toString())
            }

        }

        holder.binding.btDelete.setOnClickListener {
            myView.deleteTVShow(tvShow)
        }
        holder.binding.cardView.setOnClickListener {
            if (tvShow.summary == null){

                Toast.makeText(holder.itemView.context , "There is no Summary", Toast.LENGTH_SHORT).show()
            }
            else{
                showAlert(tvShow.summary, holder.itemView.context)
            }
        }
    }

    override fun getItemCount(): Int = arrayShow.size

    fun update(newArray: List<TvShowsDB>) {
        arrayShow = newArray
        notifyDataSetChanged()
    }

    fun showAlert(text: String, context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage(text)
            .setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dia, _ ->
                dia.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Summary")
        // show alert dialog
        alert.show()
    }

}