package com.sutrix.beauty.salon.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sutrix.beauty.salon.data.api.ApiHelper
import com.sutrix.beauty.salon.data.model.GetSalonList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val apiHelper: ApiHelper) {

    val list = MutableLiveData<GetSalonList>()

    fun getSalonList(): MutableLiveData<GetSalonList> {

        val call = apiHelper.getList()

        call.enqueue(object: Callback<GetSalonList> {
            override fun onFailure(call: Call<GetSalonList>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<GetSalonList>,
                response: Response<GetSalonList>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                list.value = data
            }
        })

        return list
    }

}