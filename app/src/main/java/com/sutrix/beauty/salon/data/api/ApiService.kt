package com.sutrix.beauty.salon.data.api

import com.sutrix.beauty.salon.data.model.GetEmployeeList
import com.sutrix.beauty.salon.data.model.GetSalonList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("challenge-services")
    fun getList(): Call<GetSalonList>

    @GET("challenge-employees")
    fun getEmployees(): Call<GetEmployeeList>
}