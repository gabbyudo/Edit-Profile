package com.example.editprofile

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _isSaveButtonEnabled = MutableLiveData<Boolean>()
    val isSaveButtonEnabled: LiveData<Boolean>
        get() = _isSaveButtonEnabled

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getUser() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            _user.value = User("dynamics", true)
        }
    }

    fun onEmailChangeOrProfileVisibilityChanged(email: String, isChecked: Boolean) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessage.value = ""
        } else {
            _errorMessage.value = "invalid Email"
        }
        if ((email != "dynamics" || isChecked != true ) &&
            (Patterns.EMAIL_ADDRESS.matcher(email).matches()) && email != "") {
            _isSaveButtonEnabled.value =true
        } else  { _isSaveButtonEnabled.value = false}


       /* if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (email != "dynamics" || isChecked != true) {
                _isSaveButtonEnabled.value = true
            } else _isSaveButtonEnabled.value = false
        } else _isSaveButtonEnabled.value = false*/
    }



}

// (did email change or did switch change)  and is email  valid