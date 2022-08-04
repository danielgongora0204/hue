package com.gig.hue.com.gig.hue.data.repositories

import com.gig.hue.models.temp.ConversationItemTemp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

class InboxRepository @Inject constructor(

) {

    suspend fun getItems(number: Int?): List<ConversationItemTemp> {
        val list: MutableList<ConversationItemTemp> = mutableListOf()
        delay(3000L)
        number?.let {
            for (i in 0..number){
                list.add(ConversationItemTemp(
                    "Jhon Smith",
                    "This is a test message for jhon...",
                    "Ensenada - Two bedroom apartment",
                    LocalDateTime.now(),
                    null
                ))
                list.add(ConversationItemTemp(
                    "Andy Grove",
                    "Hello Andy, how are you doing?...",
                    "Tij. - Huge house, 3 bedroom",
                    LocalDateTime.now(),
                    2
                ))
            }
        }
        return list;
    }

}