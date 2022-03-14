package com.sutrix.beauty.salon.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sutrix.beauty.salon.data.api.ApiHelper
import com.sutrix.beauty.salon.data.repository.EmployeeRepository
import com.sutrix.beauty.salon.data.repository.MainRepository
import com.sutrix.beauty.salon.ui.main.viewmodel.EmployeeViewModel
import com.sutrix.beauty.salon.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            return EmployeeViewModel(EmployeeRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

