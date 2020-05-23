package com.example.searchsuggestions.ui.address

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.searchsuggestions.data.response.AddressResponse
import com.example.searchsuggestions.data.remote.RetrofitFactory
import com.example.searchsuggestions.data.remote.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AddressViewModel : ViewModel() {
    private var addressLiveData = MutableLiveData<AddressResponse?>()
    lateinit var service: RetrofitService

    fun init() {
        service = RetrofitFactory.makeRetrofitService()
    }

    // get Live Data only if data is changed
    fun getLiveData(): LiveData<AddressResponse?> {
        return Transformations.distinctUntilChanged(addressLiveData)
    }

    fun apiCall(query: String?, city: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAddressData(query!!, city!!)
            withContext(Dispatchers.Main) {
                try {
                    if (response!!.isSuccessful && response != null) {
                        // update live data
                        addressLiveData.postValue(response.body() as AddressResponse?)
                    } else {
                        addressLiveData.postValue(null)
                    }
                } catch (e: HttpException) {
                    Log.e("Exception", "Exception ${e.message}")
                } catch (e: Throwable) {
                    Log.e("Exception", "Ooops: Something else went wrong")
                }
            }
        }
    }
}