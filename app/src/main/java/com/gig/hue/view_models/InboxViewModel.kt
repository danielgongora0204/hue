package com.gig.hue.com.gig.hue.view_models


import androidx.lifecycle.viewModelScope
import com.gig.hue.com.gig.hue.data.repositories.InboxRepository
import com.gig.hue.models.temp.ConversationItemTemp
import com.gig.hue.view_models.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InboxViewModel @Inject constructor(private val repository: InboxRepository): BaseViewModel() {

    val conversationItems by lazy { _conversationItems.asStateFlow() }

    private val _conversationItems = MutableStateFlow<List<ConversationItemTemp>?>(null)

    init {
        updateConversations((1..30).random())
    }

    fun updateConversations(numberOfItems: Int? = null){
        viewModelScope.launch {
            _conversationItems.value = repository.getItems(numberOfItems)
        }
    }
}