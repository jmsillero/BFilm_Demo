package com.bacteria.bestfilm.data.cache.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class Converters {
    @TypeConverter
    fun listIntToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListInt(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun listStringToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListString(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun sizeToJson(value: SizeLocal?) = Gson().toJson(value)

    @TypeConverter
    fun jsonTosize(value: String) = Gson().fromJson(value, SizeLocal::class.java)

    @TypeConverter
    fun mediaToJson(value: List<MediaLocal>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToMedia(value: String) = Gson().fromJson(value, Array<MediaLocal>::class.java).toList()

    @TypeConverter
    fun dateToJson(value: Date?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToDate(value: String) = Gson().fromJson(value, Date::class.java)


}