package com.gig.hue.view_models

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.gig.hue.data.repositories.LoginRepository
import com.gig.hue.utilities.CrashlyticsUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    loginRepository: LoginRepository
): BaseViewModel() {

    //Public
    val showProgress by lazy { _showProgress.asStateFlow() }
    val loginEnabled by lazy { _loginEnabled.asStateFlow() }
    val navigateToMain by lazy { _navigateToMain.asStateFlow() }
    val usernameErrorMessage by lazy { _usernameErrorMessage.asStateFlow() }
    val passwordErrorMessage by lazy { _passwordErrorMessage.asStateFlow() }

    //Private
    private val _loginEnabled = MutableStateFlow(true)
    private val _showProgress = MutableStateFlow(false)
    private val _navigateToMain = MutableStateFlow(false)
    private val _usernameErrorMessage = MutableStateFlow<Int?>(null)
    private val _passwordErrorMessage = MutableStateFlow<Int?>(null)

    //DatabaseUserName/Password
    @Bindable
    val password = MutableStateFlow<String?>(null)
    @Bindable
    val username = MutableStateFlow<String?>(null)

    fun loginClick(){
        viewModelScope.launch {
            try {
              /*  val user = username.value?: "".trim()
                val pass = password.value?: "".trim()
                _usernameErrorMessage.emit(user.validEmail().stringResource)
                _passwordErrorMessage.emit(pass.validPassword().stringResource)
                if(user.validEmail() != ValidateResult.VALID || pass.validPassword() != ValidateResult.VALID) {
                    return@launch
                }
                _showProgress.emit(true)
                _loginEnabled.emit(false)*/
                _navigateToMain.emit(true)
            }
            catch(e: Exception) {
                CrashlyticsUtil.recordError(e)
            }
        }
    }

    private suspend fun loginCall(user: String, pass: String) {

    }

}