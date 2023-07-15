package com.example.editprofile

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.editprofile.database.UserDatabase
import com.example.editprofile.database.UserInfo
import com.example.editprofile.database.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val repository: UserRepository
    init {
        val dao = UserDatabase.getDatabase(application).getUserInfoDao()
        repository = UserRepository(dao)
    }

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    fun addUser(info: UserInfo){
        CoroutineScope(Dispatchers.Main).launch {
            repository.insertInfo(info)
        }
    }

    fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
            val user = repository.getUser()
            if (user == null) {
                _viewState.postValue(ViewState(
                    "",
                    true,
                    "",
                    "Invalid email",
                    true,
                    "Invalid password",
                    true,
                )
                )
            }else if (user != null) {
                _viewState.postValue(ViewState(
                    user?.username,
                    true,
                    user?.password,
                    "Invalid email",
                    true,
                    "Invalid password",
                    true,
                )
                )
            }
        }
    }

    fun onEmailChangeOrProfileVisibilityChanged(email: String, password: String, isChecked: Boolean) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _viewState.value = _viewState.value?.copy(errorMessage= "", isInitialState = false)
        } else {
            _viewState.value = _viewState.value?.copy(errorMessage="Invalid email", isInitialState = false)
        }
        if (Patterns.EMAIL_ADDRESS.matcher(password).matches()) {
            _viewState.value = _viewState.value?.copy(errorMessagePassword= "")
        } else {
            _viewState.value = _viewState.value?.copy(errorMessagePassword = "Invalid password")
        }

        if ((email != "dynamics" || isChecked != true || password != "password") &&
            (Patterns.EMAIL_ADDRESS.matcher(email).matches()) &&
            Patterns.EMAIL_ADDRESS.matcher(password).matches()
        ) {
            _viewState.value = _viewState.value?.copy(isSaveButtonEnabled = true)
        } else {
            _viewState.value = _viewState.value?.copy(isSaveButtonEnabled = false)
        }

       /* when (isChecked) {
            false ->  _viewState.value = _viewState.value?.copy(isSaveButtonEnabled = true)
            else->  _viewState.value = _viewState.value?.copy(isSaveButtonEnabled = false)
        }*/
    }
}
