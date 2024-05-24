package com.example.whyphytestapp.mvvm.data.movieDetailsFragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whyphytestapp.mvvm.data.dataInterface.MovieItemClickListener
import com.example.whyphytestapp.databinding.ListItemsOfSeatNumberBinding


class SeatsAdapter(
    val context: Context,
    val arrayList: ArrayList<String>,
    private var screensItemClickListener: MovieItemClickListener? = null
) : RecyclerView.Adapter<SeatsAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        Log.d("userProfile->", "ADAPTER CALL")

        val binding = ListItemsOfSeatNumberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val screenData = arrayList[position]

        holder.binding.txtSeatNumber.text = screenData.toString()

        holder.itemView.setOnClickListener {
            screensItemClickListener?.movieItemClickListener(position)
        }

    }


    /**
     * Get Item Counts
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Custom FavouriteAdsViewHolder
     */
    class UserViewHolder(val binding: ListItemsOfSeatNumberBinding) :
        RecyclerView.ViewHolder(binding.root)


}