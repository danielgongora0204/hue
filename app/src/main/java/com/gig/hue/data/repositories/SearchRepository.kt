package com.gig.hue.com.gig.hue.data.repositories

import android.util.Log
import com.gig.hue.data.DataStoreManager
import com.gig.hue.models.temp.RentItemTemp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(

) {

    suspend fun getItems(number: Int?): List<RentItemTemp> {
        val list: MutableList<RentItemTemp> = mutableListOf()
        delay(3000L)
        number?.let{
            for (i in 0..number){
                list.add(RentItemTemp(
                    "Hello",
                    "This is the $i tittle",
                    "This is the $i secondary text",
                    "This is the $i paragraph"
                ))
            }
        }
        return list;
    }

}