package com.sutrix.beauty.salon.data.model

data class Employee(
    val id: Int,
    val image: String,
    val name: String,
    var isSelected: Boolean = false
)