package com.example.newsapp.data.local

import androidx.room.TypeConverter
import com.example.newsapp.data.remote.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

   // Converts a Source object to a JSON String for storing in room
    @TypeConverter
    fun fromSource(source: Source): String {
        return Gson().toJson(source) // Convert object to JSON string
    }

    //Converts a JSON String back into a Source object for retrieving from room
    @TypeConverter
    fun toSource(sourceString: String): Source {
        val type = object : TypeToken<Source>() {}.type
        return Gson().fromJson(sourceString, type) // Convert JSON string back to Source object
    }
}
