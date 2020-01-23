package com.example.idolkingdom.event


import com.example.idolkingdom.model.Entertainment
import com.example.idolkingdom.model.Idol
import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.model.Image
import com.example.idolkingdom.repository.EntertainmentRepository
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.IdolRepository
import com.example.idolkingdom.repository.ImageRepository
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Component
class InsertIdolListEvent(
    @Autowired private val entertainmentRepository: EntertainmentRepository,
    @Autowired private val imageRepository: ImageRepository,
    @Autowired private val idolGroupRepository: IdolGroupRepository,
    @Autowired private val idolRepository: IdolRepository
) : ApplicationListener<ApplicationReadyEvent> {

    @Transactional
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val gson = Gson()
        val file = ClassPathResource("static/idol.json").inputStream.reader()
        val jsonArray = gson.fromJson(file, JsonArray::class.java)
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        jsonArray.forEach {
            it.asJsonObject.let {
                val groups = hashSetOf<String>()
                val name = it.get("name").asString.replace("\"", "")
                val image = it.get("image").asString.replace("\"", "")
                groups.addAll(it.get("group").asString.replace("\"", "").split(","))
                val info = it.getAsJsonArray("info")
                var bloodType: String = ""
                var entertainmentName: String = ""
                var graduation: String = ""
                var hometown: String = ""
                var birth: String = ""
                info.forEach {
                    val it = it.asJsonObject
                    bloodType = it.get("혈액형")?.asString?.replace("\"", "") ?: bloodType
                    entertainmentName = it.get("소속사")?.asString?.replace("\"", "") ?: entertainmentName
                    graduation = it.get("학력")?.asString?.replace("\"", "") ?: graduation
                    hometown = it.get("출신")?.asString?.replace("\"", "") ?: hometown
                    birth = it.get("출생")?.asString?.replace("\"", "") ?: birth
                    groups.addAll(it.get("그룹명")?.asString?.replace("\"", "")?.split(",") ?: listOf())
                }
                var idol = Idol(
                    name = name,
                    bloodType = Idol.BloodType.get(bloodType),
                    groups = listOf(),
                    entertainment = null,
                    graduation = graduation,
                    dateOfBirth = if (birth.isNotEmpty()) LocalDate.parse(birth, dateFormatter) else null,
                    hometown = hometown,
                    images = listOf()
                )

                if (image.isNotEmpty())
                    imageRepository.save(Image(url = image)).let {
                        idol = idol.copy(images = listOf(it))
                    }
                if (entertainmentName.isNotEmpty()) {
                    val entertainment = entertainmentRepository.findByName(entertainmentName).firstOrNull()
                    idol = idol.copy(
                        entertainment = entertainment ?: entertainmentRepository.save(
                            Entertainment(name = entertainmentName)
                        )
                    )
                }
                if (groups.isNotEmpty()) {
                    groups.forEach {
                        idol = idol.copy(
                            groups = listOf(
                                idolGroupRepository.findByName(it).firstOrNull() ?: idolGroupRepository.save(
                                    IdolGroup(name = it)
                                )
                            ) + idol.groups
                        )
                    }
                }
                idol = idolRepository.save(idol)
                idol.entertainment?.let {
                    it.idols = it.idols + listOf(idol)
                    entertainmentRepository.save(it)
                }
                idol.groups.forEach {
                    it.members = it.members + listOf(idol)
                    idolGroupRepository.save(it)
                }
            }
        }

    }

    private fun searchJson(jsonArray: JsonArray, map: HashMap<String, HashSet<String>>): Unit =
        jsonArray.forEach { searchJson(it as JsonObject, map) }

    private fun searchJson(jsonObject: JsonObject, map: HashMap<String, HashSet<String>>): Unit =
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