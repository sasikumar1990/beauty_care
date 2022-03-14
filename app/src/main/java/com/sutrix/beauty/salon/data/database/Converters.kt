package com.sutrix.beauty.salon.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {
        }.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    companion object {
        @TypeConverter
        @JvmStatic
        fun stringToMap(value: JsonElement): Map<String, String> {
            return Gson().fromJson(value, object : TypeToken<Map<String, String>>() {}.type)
        }
        @TypeConverter
        @JvmStatic
        fun mapToString(value: Map<String, String>?): String {
            return if (value == null) "" else Gson().toJson(value)
        }
    }

}