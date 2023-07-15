package com.example.editprofile

data class ViewState(
    var email: String?,
    val isChecked: Boolean,
    val password: String?,
    val errorMessage: String,
    val isInitialState: Boolean?,
    val errorMessagePassword: String,
    val isSaveButtonEnabled: Boolean,
)
