package com.example.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    fun getName() : String{
        val name = "dynamics"
        _name.value= name
        return _name.value.toString()
        }


}