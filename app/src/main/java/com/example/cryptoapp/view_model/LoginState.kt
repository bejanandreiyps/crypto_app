package com.example.cryptoapp.view_model

sealed class LoginState {
    data class Error(val message: String) : LoginState()
    object Success : LoginState()
    object InProgress: LoginState()
}
