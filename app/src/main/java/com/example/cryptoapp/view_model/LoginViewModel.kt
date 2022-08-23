package com.example.cryptoapp.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.MovieRepositoryRetrofit
import com.example.cryptoapp.domain.login.CredentialsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel: ViewModel() {
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val state = MutableLiveData<LoginState>()

    private var job: Job = Job()

    private val repo = MovieRepositoryRetrofit

    fun login() {
        val usr = username.value
        val pwd = password.value

        if(usr.isNullOrBlank()){
            state.value = LoginState.Error("Username blank!")
            return
        }
        if(pwd.isNullOrBlank()){
            state.value = LoginState.Error("Password blank!")
            return
        }

        job.cancel()

        job  = viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(LoginState.InProgress)
                val token = repo.getToken()
                repo.postLogin(
                    CredentialsModel(usr, pwd, token.requestToken)
                )
                state.postValue(LoginState.Success)
            } catch (e: HttpException) {
                state.postValue(LoginState.Error("Incorrect username or password!"))
                Log.e("LoginViewModel: ", e.message.toString())
            }
        }
    }
}