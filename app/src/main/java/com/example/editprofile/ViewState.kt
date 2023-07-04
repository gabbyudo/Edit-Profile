package com.example.editprofile

data class ViewState(
    var email: String,
    val isChecked: Boolean,
    val password: String,
    val errorMessage: String,
    val isInitialState: Boolean,
                     //val isSaveButtonEnabled: Boolean,
                     /*
                     val errorMessagePassword: String*/
                     )
