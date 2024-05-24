package com.example.whyphytestapp.mvvm.data.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appunotest.Utils.*
import com.example.whyphytestapp.mvvm.data.base.BaseModel


abstract class BaseViewModel(networkStateData: NetworkStateData) : ViewModel() {

    /** Loading Detection */
    val mLoadingStateData = MutableLiveData(Event(LoadingState.IDLE))

    /** Update When Need to Move Next Destination */
    val mDestinationForward = MutableLiveData<Event<Any>>()

    /**
     * Network Connection/Disconnect Detection
     * */
    val mErrorModel = ObservableField(BaseModel())
    open val mNetworkErrorData = MutableLiveData(BaseModel())
    var showNoInternetSnackBar = ObservableBoolean(false)

    /** Error View: Retry Click */
    open fun onErrorButtonClick() {/* Ignore */
    }

    init {
        /* Observer ConnectivityState Change */
        networkStateData.observeForever {
            it?.let { onNetworkState(it) }
        }

        /* Need to Notify First Time
        *  Because NetworkLiveData doesn't send callback if network is disconnected
        *  while launching the app */
        networkStateData.getConnectivityState().let {
            if (it == ConnectivityState.DISCONNECTED)
                onNetworkState(it)
        }
    }

    /**
     * Update LiveData when State change
     * */
    private fun onNetworkState(connectivityState: ConnectivityState) {
        when (connectivityState) {
            ConnectivityState.WIFI_CONNECTED,
            ConnectivityState.MOBILE_DATA_CONNECTED,
            -> {
                mNetworkErrorData.value =
                    BaseModel(
                        true,
                        NetworkMessage.NETWORK_CONNECTED

                    )
            }

            ConnectivityState.DISCONNECTED -> {
                mNetworkErrorData.value = BaseModel(message = NetworkMessage.NO_NETWORK)
            }
        }
    }
}