package com.example.idolkingdom.util

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.FileReader

object BestIdolParser {

    fun execute() {
        val gson = Gson()
        val file = FileReader("../assets/idol.json")
        val jsonArray = gson.fromJson(file, JsonArray::class.java)
        val keyMap = hashMapOf<String, HashSet<String>>()

        searchJson(jsonArray, keyMap)
        keyMap.forEach { t, u ->
            println("$t:$u")
        }
    }

    fun searchJson(jsonArray: JsonArray, map: HashMap<String, HashSet<String>>): Unit =
        jsonArray.forEach { searchJson(it as JsonObject, map) }

    fun searchJson(jsonObject: JsonObject, map: HashMap<String, HashSet<String>>): Unit =
        jsonObject.entrySet().forEach {
            when {
                it.value is JsonObject -> searchJson(it.value as JsonObject, map)
                it.value is JsonArray -> searchJson(it.value as JsonArray, map)
                else -> map.put(it.key, map.getOrElse(it.key) { hashSetOf() }.apply {
                    add(it.value.toString())
                })
            }
        }
}



