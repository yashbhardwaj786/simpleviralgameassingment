package com.simpleviralgamesassingment.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class PreferenceProvider(var context: Context) {
    private var PRIVATE_MODE = 0
    private val PREFERENCE_NAME = "dog_generator"
    private val IMAGE_OBJ = "DogImageObject"

    private val preference: SharedPreferences
        get() =
            context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE)

    fun saveImagesJsonObject(imagesObj: ArrayList<String?>) {
        val prefsEditor = preference.edit()
        val gson = Gson()
        val json = gson.toJson(imagesObj)
        prefsEditor.putString(IMAGE_OBJ, json)
        prefsEditor.apply()
    }

    fun getImagesJsonObject(): ArrayList<String?>? {
        val gson = Gson()
        val json = preference.getString(IMAGE_OBJ, "")
        val type: Type =
            object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(json, type)
    }
}
