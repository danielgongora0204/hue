package com.gig.hue.com.gig.hue.view_models

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gig.hue.com.gig.hue.data.repositories.InboxRepository
import com.gig.hue.models.temp.ConversationItemTemp
import com.gig.hue.view_models.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InboxViewModel @Inject constructor(private val repository: InboxRepository): BaseViewModel() {

    val conversationItems by lazy { _conversationItems.asSharedFlow() }

    private val _conversationItems = MutableSharedFlow<List<ConversationItemTemp>?>()

    init {
        updateConversations((1..30).random())
    }

    fun updateConversations(numberOfItems: Int? = null){
        viewModelScope.launch {
            _conversationItems.emit(repository.getItems(numberOfItems))
        }
    }
}