package com.example.idolkingdom.util

import com.example.idolkingdom.dao.IdolDao
import com.example.idolkingdom.model.*
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.springframework.core.io.ClassPathResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.stream.Stream

object IdolJsonParser {

    fun parse(path: String): List<IdolDao> {

        val gson = Gson()
        val file = ClassPathResource(path).inputStream.reader()
        val jsonArray = gson.fromJson(file, JsonArray::class.java)
        return jsonArray.map {
            it.asJsonObject.let {
                val groups = hashSetOf<String>()
                val dao = IdolDao(
                    it.get("name").asString.replace("\"", ""),
                    it.get("image").asString.replace("\"", ""),
                    IdolDao.Info("", "", "", "", ""),
                    listOf()
                )
                it.get("group").asString.replace("\"", "").split(",").filter { it.contains("..").not() }.firstOrNull()?.let { groups.add(it) }
                val info = it.getAsJsonArray("info")
                var bloodType: String = ""
                var entertainmentName: String = ""
                var graduation: String = ""
                var hometown: String = ""
                var birth: String = ""
                info.forEach {
                    it.asJsonObject.let {
                        bloodType = it.get("혈액형")?.asString?.replace("\"", "") ?: bloodType
                        entertainmentName = it.get("소속사")?.asString?.replace("\"", "") ?: entertainmentName
                        graduation = it.get("학력")?.asString?.replace("\"", "") ?: graduation
                        hometown = it.get("출신")?.asString?.replace("\"", "") ?: hometown
                        birth = it.get("출생")?.asString?.replace("\"", "") ?: birth
                    }
                }
                dao.copy(
                    info = IdolDao.Info(
                        bloodType,
                        entertainmentName,
                        graduation,
                        hometown,
                        birth
                    ),
                    groups = groups.map { it.trim() }.distinct()
                )
            }
        }.filter {
            it.name.contains("..").not()
        }
    }
}