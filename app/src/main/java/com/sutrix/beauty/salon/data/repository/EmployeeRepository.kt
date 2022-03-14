package com.sutrix.beauty.salon.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sutrix.beauty.salon.data.api.ApiHelper
import com.sutrix.beauty.salon.data.model.GetEmployeeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository(private val apiHelper: ApiHelper) {

    val list = MutableLiveData<GetEmployeeList>()

    fun getEmployeeList(): MutableLiveData<GetEmployeeList> {

        val call = apiHelper.getEmployee()

        call.enqueue(object: Callback<GetEmployeeList> {
            override fun onFailure(call: Call<GetEmployeeList>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<GetEmployeeList>,
                response: Response<GetEmployeeList>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                list.value = data
            }
        })

        return list
    }

}