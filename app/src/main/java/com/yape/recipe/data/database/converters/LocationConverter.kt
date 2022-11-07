package com.yape.recipe.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yape.recipe.domain.model.Location
import java.lang.reflect.Type

class LocationConverter {

    @TypeConverter
    fun stringToLocation(json: String?): Location? {
        val gson = Gson()
        val type: Type = object : TypeToken<Location?>() {}.type
        return gson.fromJson<Location>(json, type)
    }

    @TypeConverter
    fun locationToString(location: Location?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<Location?>() {}.type
        return gson.toJson(location, type)
    }

}