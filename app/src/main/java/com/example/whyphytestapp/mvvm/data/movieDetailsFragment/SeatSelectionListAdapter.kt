package com.example.whyphytestapp.mvvm.data.movieDetailsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whyphytestapp.mvvm.data.dataInterface.MovieItemClickListener
import com.example.whyphytestapp.databinding.ListItemsOfSeatBinding
import com.example.whyphytestapp.mvvm.data.seatData


class SeatSelectionListAdapter(
    val context: Context,
    val arrayList: List<seatData>?,
    private var screensItemClickListener: MovieItemClickListener? = null
) : RecyclerView.Adapter<SeatSelectionListAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ListItemsOfSeatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val seatData = arrayList?.get(position)
        holder.binding.txtSeatPrice.text = seatData?.price.toString()
        holder.binding.recycleSeatList.layoutManager =
            GridLayoutManager(context, 4) // Set 2 columns
        val userListAdapter = SeatsAdapter(
            context, seatData?.seats as ArrayList<String>
        )
        holder.binding.recycleSeatList.adapter = userListAdapter
    }


    /**
     * Get Item Counts
     */
    override fun getItemCount(): Int {
        return arrayList?.size ?:0
    }

    /**
     * Custom FavouriteAdsViewHolder
     */
    class UserViewHolder(val binding: ListItemsOfSeatBinding) :
        RecyclerView.ViewHolder(binding.root)


}