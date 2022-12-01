package com.gig.hue.com.gig.hue.data.repositories

import com.gig.hue.models.temp.RentItemTemp
import kotlinx.coroutines.delay
import javax.inject.Inject

class LocationRepository @Inject constructor(

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