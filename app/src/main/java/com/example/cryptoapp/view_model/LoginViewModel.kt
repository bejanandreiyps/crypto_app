package com.example.cryptoapp.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.MovieRepository
import com.example.cryptoapp.domain.login.CredentialsModel
import com.example.cryptoapp.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: MovieRepository
): ViewModel() {
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val state = MutableLiveData<LoginState>()

    private var job: Job = Job()


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
                repo.createSession(
                    repo.postLogin(
                        CredentialsModel(usr, pwd, repo.getToken().requestToken)
                    )
                )
                state.postValue(LoginState.Success)
            } catch (e: HttpException) {
                state.postValue(LoginState.Error("Incorrect username or password!"))
                Log.e("LoginViewModel: ", e.message.toString())
            }
        }
    }
}