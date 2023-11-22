package com.gig.hue.view_models

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.gig.hue.com.gig.hue.utilities.CredentialValidationUtil
import com.gig.hue.data.repositories.LoginRepository
import com.gig.hue.utilities.CrashlyticsUtil
import com.gig.hue.utilities.extensions.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): BaseViewModel() {

    //Public
    val loginEnabled by lazy { _loginEnabled.asStateFlow() }
    val navigateToMain by lazy { _navigateToMain.asSharedFlow() }
    val usernameErrorMessage by lazy { _usernameErrorMessage.asStateFlow() }
    val passwordErrorMessage by lazy { _passwordErrorMessage.asStateFlow() }

    //Private
    private val _loginEnabled = MutableStateFlow(true)
    private val _navigateToMain = MutableSharedFlow<Boolean>()
    private val _usernameErrorMessage = MutableStateFlow<Int?>(null)
    private val _passwordErrorMessage = MutableStateFlow<Int?>(null)

    //DatabaseUserName/Password
    @Bindable
    val password = MutableStateFlow<String?>(null)
    @Bindable
    val username = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            _navigateToMain.emit(false)
        }
    }

    fun setUsernameErrorMessage(user: String?) {
        _usernameErrorMessage.value = CredentialValidationUtil.checkUsername(user)?.stringResource
    }

    fun setPasswordErrorMessage(password: String?) {
        _passwordErrorMessage.value = CredentialValidationUtil.checkPassword(password)?.stringResource
    }

    fun loginClick(){
        viewModelScope.launch {
            try {
                if(internalCredentialValidation(username.value, password.value)) {
                    return@launch
                }
                _loginEnabled.value = false
                with(repository.postLogin()) {
                    if (!this) {

                    }
                    _loginEnabled.value = !this
                    _navigateToMain.emit(this)
                }
            }
            catch(e: Exception) {
                CrashlyticsUtil.recordError(e)
            }
        }
    }

    private fun internalCredentialValidation(
        username: String?,
        password: String?
    ) = CredentialValidationUtil.checkUsername(username) != null ||
            CredentialValidationUtil.checkPassword(password) != null

}