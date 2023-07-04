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

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val _isSaveButtonEnabled = MutableLiveData<Boolean>()
    val isSaveButtonEnabled: LiveData<Boolean>
        get() = _isSaveButtonEnabled

    private val _errorMessagePassword = MutableLiveData<String>()
    val errorMessagePassword: LiveData<String>
        get() = _errorMessagePassword

    fun getUser() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            _viewState.value = ViewState(
                "dynamics",
                true,
                "repeat",
           "Invalid email",
           true
            )
        }
    }

    fun onEmailChangeOrProfileVisibilityChanged(email: String, password: String, isChecked: Boolean) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _viewState.value = _viewState.value?.copy(errorMessage= "", isInitialState = false)
        } else {
            _viewState.value = _viewState.value?.copy(errorMessage="Invalid email", isInitialState = false)
        }
        if (Patterns.EMAIL_ADDRESS.matcher(password).matches()) {
            _errorMessagePassword.value = ""
        } else {
            _errorMessagePassword.value = "Invalid password"
        }

        if ((email != "dynamics" || isChecked != true || password != "password") &&
            (Patterns.EMAIL_ADDRESS.matcher(email).matches()) &&
            Patterns.EMAIL_ADDRESS.matcher(password).matches()
        ) {
            _isSaveButtonEnabled.value = true
        } else {
            _isSaveButtonEnabled.value = false
        }

        /*when (isChecked) {
            false -> _isSaveButtonEnabled.value = true
            else-> _isSaveButtonEnabled.value = false
        }*/
    }
}
