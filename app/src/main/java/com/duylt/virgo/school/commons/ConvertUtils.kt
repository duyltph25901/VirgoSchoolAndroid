package com.duylt.virgo.school.commons

import com.google.gson.Gson

object ConvertUtils {

    fun <T> convertObjectToJsonConvertUtils(obj: T): String =
        Gson().toJson(obj)

    fun <T> convertJsonToObjectConvertUtils(json: String, classOfT: Class<T>): T =
        Gson().fromJson(json, classOfT)

}