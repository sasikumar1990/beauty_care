package com.sutrix.beauty.salon.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sutrix.beauty.salon.data.model.GetSalonList
import com.sutrix.beauty.salon.data.repository.MainRepository


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var listLiveData: MutableLiveData<GetSalonList>? = null

    fun getList() : LiveData<GetSalonList>? {
        listLiveData = mainRepository.getSalonList()
        return listLiveData
    }

}