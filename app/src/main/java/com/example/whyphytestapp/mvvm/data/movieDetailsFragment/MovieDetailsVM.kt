package com.example.movielistapp.mvvm.views.fragment.movieDetailsFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whyphytestapp.mvvm.data.*
import com.example.whyphytestapp.mvvm.data.movieDetailsFragment.SeatSelectionListAdapter
import com.example.whyphytestapp.network.ApiService
import com.example.whyphytestapp.network.NetworkSDK

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class MovieDetailsVM @Inject constructor(
    private val apiService: ApiService,
    private val networkSDK1: NetworkSDK
) :ViewModel(){

//    var popularMovieArrayList: List<Movie>? = null
//    val networkSDK = NetworkSDK()
//    var detailsResposne = MutableLiveData<MovieDetails>()


//    fun insertMovieDetails(movieTables: movieTables) {
//        viewModelScope.launch {
//            repository.insertMovieDetails(movieTables)
//        }
//    }
var seatResultResposne = MutableLiveData<seatsResponse?>()
    var seaDataArrayList = ArrayList<seatData>()
    var screenListAdapter: SeatSelectionListAdapter? = null




    fun getUserSeats(){
        viewModelScope.launch {
            try {
                val userProfileList = apiService.getSeats()
                userProfileList.enqueue(object : Callback<seatsResponse> {
                    override fun onResponse(call: Call<seatsResponse>, response: Response<seatsResponse>) {
                        val movieResponse = response.body()
                        seaDataArrayList= movieResponse?.seat_layout as ArrayList<seatData>
                        Log.e("API", "seaDataArrayList Size: ${seaDataArrayList.size}")
                        seatResultResposne.postValue(movieResponse)

                    }

                    override fun onFailure(call: Call<seatsResponse>, t: Throwable) {
                        Log.e("API", "Failed to fetch popular screen", t)
                    }
                })
            } catch (e: Exception) {
                Log.e("API", "Failed to fetch popular movies", e)
            }


        }

    }
}