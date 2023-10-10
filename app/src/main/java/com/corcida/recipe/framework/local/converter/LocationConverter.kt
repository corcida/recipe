package com.corcida.recipe.framework.local.converter

import androidx.room.TypeConverter
import com.corcida.domain.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LocationConverter {

    @TypeConverter
    fun stringToLocation(json: String?): Location? {
        val gson = Gson()
        val type: Type = object : TypeToken<Location?>() {}.type
        return gson.fromJson<Location>(json, type)
    }

    @TypeConverter
    fun locationToString(list: Location?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<Location?>() {}.type
        return gson.toJson(list, type)
    }

}