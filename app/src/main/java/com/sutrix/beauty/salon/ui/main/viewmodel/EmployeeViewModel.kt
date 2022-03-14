package com.sutrix.beauty.salon.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sutrix.beauty.salon.data.model.GetEmployeeList
import com.sutrix.beauty.salon.data.model.GetSalonList
import com.sutrix.beauty.salon.data.repository.EmployeeRepository
import com.sutrix.beauty.salon.data.repository.MainRepository


class EmployeeViewModel(private val mainRepository: EmployeeRepository) : ViewModel() {

    var listLiveData: MutableLiveData<GetEmployeeList>? = null

    fun getList() : LiveData<GetEmployeeList>? {
        listLiveData = mainRepository.getEmployeeList()
        return listLiveData
    }

}