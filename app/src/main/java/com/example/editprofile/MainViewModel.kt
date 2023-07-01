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

    private val viewState = MutableLiveData<ViewState>()
    val user: LiveData<ViewState>
        get() = viewState

    private val _isSaveButtonEnabled = MutableLiveData<Boolean>()
    val isSaveButtonEnabled: LiveData<Boolean>
        get() = _isSaveButtonEnabled

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _errorMessagePassword = MutableLiveData<String>()
    val errorMessagePassword: LiveData<String>
        get() = _errorMessagePassword

    private val _passwordMessage = MutableLiveData<String>()
    val passwordMessage: LiveData<String>
        get() = _passwordMessage

    fun getPassword() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            val password = "password"
            _passwordMessage.value = password

        }
    }

    fun getUser() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            viewState.value = ViewState("dynamics", true)
        }
    }

    fun onEmailChangeOrProfileVisibilityChanged(email: String, password: String, isChecked: Boolean) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessage.value = ""
        } else {
            _errorMessage.value = "Invalid email"
        }
        if (Patterns.EMAIL_ADDRESS.matcher(password).matches()) {
            _errorMessagePassword.value = ""
        } else {
            _errorMessagePassword.value = "invalid password"
        }

        if ((email != "dynamics" || isChecked != true || password != "password") &&
            (Patterns.EMAIL_ADDRESS.matcher(email).matches()) &&
            Patterns.EMAIL_ADDRESS.matcher(password).matches()
        ) {
            _isSaveButtonEnabled.value = true
        } else {
            _isSaveButtonEnabled.value = false
        }

        when (isChecked) {
            false -> _isSaveButtonEnabled.value = true
            else-> _isSaveButtonEnabled.value = false
        }
    }
}
