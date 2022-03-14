package com.sutrix.beauty.salon.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getList() = apiService.getList()
    fun getEmployee() = apiService.getEmployees()
}