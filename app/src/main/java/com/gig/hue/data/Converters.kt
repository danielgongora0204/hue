package com.gig.hue.data

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {

    @TypeConverter
    fun fromString(value: String?): LocalDateTime? = value?.let{ LocalDateTime.parse(value) }

    @TypeConverter
    fun fromDateToString(value: LocalDateTime?): String? = value?.toString()


}