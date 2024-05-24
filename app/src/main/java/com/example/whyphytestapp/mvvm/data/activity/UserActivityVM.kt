package com.example.whyphytestapp.mvvm.data.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appunotest.Utils.NetworkStateData
import com.example.whyphytestapp.MyApplication
import com.example.whyphytestapp.mvvm.data.ScreenListAdapter
import com.example.whyphytestapp.mvvm.data.base.BaseViewModel
import com.example.whyphytestapp.mvvm.data.respose
import com.example.whyphytestapp.mvvm.data.screenData

import com.example.whyphytestapp.network.ApiService
import com.example.whyphytestapp.network.NetworkSDK

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class userActivityVM @Inject constructor(
    val myApplication: MyApplication,
    private val apiService: ApiService,
    private val networkSDK: NetworkSDK,
    networkStateData: NetworkStateData,
)
: BaseViewModel(networkStateData) {



    var screenResultResposne = MutableLiveData<respose?>()
    var screenArrayList = ArrayList<screenData>()
    var screenListAdapter: ScreenListAdapter? = null




    fun getUserScreens(){
        viewModelScope.launch {
            try {
                val userProfileList = apiService.getScreens()
                userProfileList.enqueue(object : Callback<respose> {
                    override fun onResponse(call: Call<respose>, response: Response<respose>) {
                            val movieResponse = response.body()
                            screenArrayList= movieResponse?.screens as ArrayList<screenData>
                            screenResultResposne.postValue(movieResponse)
                    }

                    override fun onFailure(call: Call<respose>, t: Throwable) {
                        Log.e("API", "Failed to fetch popular screen", t)
                    }
                })
            } catch (e: Exception) {
                Log.e("API", "Failed to fetch popular movies", e)
            }


        }

    }

}



