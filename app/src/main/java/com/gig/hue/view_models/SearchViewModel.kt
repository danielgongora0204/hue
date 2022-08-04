package com.gig.hue.com.gig.hue.view_models

import androidx.lifecycle.viewModelScope
import com.gig.hue.com.gig.hue.data.repositories.SearchRepository
import com.gig.hue.models.temp.RentItemTemp
import com.gig.hue.view_models.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository): BaseViewModel() {

    val rentItems by lazy { _rentItems.asStateFlow() }

    private val _rentItems = MutableStateFlow<List<RentItemTemp>?>(null)

    init {
        updateRentItems((1..100).random())
    }

    fun updateRentItems(numberOfItems: Int? = null){
        viewModelScope.launch {
            _rentItems.value = repository.getItems(numberOfItems)
        }
    }

}