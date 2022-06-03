package com.gig.hue.view_models

import android.content.Context
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gig.hue.utilities.ObservableViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@ApplicationContext val context: Context): ObservableViewModel() {


    val showProgress: LiveData<Boolean> get() = _showProgress  //by lazy { MutableLiveData(true) }
    val loginEnabled: LiveData<Boolean> get() = _loginEnable // by lazy { MutableLiveData(true) }
    private val _loginEnable = MutableLiveData<Boolean>(true)
    private val _showProgress = MutableLiveData<Boolean>(true)
   // val loginErrorMessage by lazy { MutableLiveData<String?>(null) }
    //val passwordErrorMessage by lazy { MutableLiveData<String?>(null) }

    @Bindable
    val password = MutableLiveData<String>(null) //by lazy { MutableLiveData<String>(null)}
    @Bindable
    val username = MutableLiveData<String>(null)//by lazy { MutableLiveData<String>(null)}

    fun loginClick(){
        try{
            // show progress
            var number = 4
            when(password.value) {
                "Hello" -> {
                }
                else ->{
                }
            }
            if(number in 10..20){
                return
            }

            val name = "Daniel"
            val lastName = "Gongora"
            val age = "26"
            val lastWord = "THis is good"

            var array = mutableListOf<String?>("Daniel", "Gongora", "Mancha", "26")
            array.forEach { ti -> ti?.let {  } }
            val listIterator = array.listIterator()
            while(listIterator.hasNext()) {
                listIterator.set("Hola"); listIterator.next()
            }

            _showProgress.value = !_showProgress.value!!
            var t = password.value;
            var u = username.value;
        }
        catch(e: Exception) {

        }
    }

}