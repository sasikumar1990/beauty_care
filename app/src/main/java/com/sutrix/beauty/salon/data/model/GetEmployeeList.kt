package com.sutrix.beauty.salon.data.model

data class GetEmployeeList(
    val `data`: List<Employee>,
    val responseCode: Int
)