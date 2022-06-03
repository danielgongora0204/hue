package com.gig.hue.com.gig.hue.data

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {

    @TypeConverter
    fun fromString(value: String?): LocalDateTime? {
        return value?.let{ LocalDateTime.parse(value) }
    }

    @TypeConverter
    fun fromDateToString(value: LocalDateTime?): String? {
        return value?.toString()
    }

}