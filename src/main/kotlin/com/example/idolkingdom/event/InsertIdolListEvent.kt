package com.example.idolkingdom.event


import com.example.idolkingdom.model.Entertainment
import com.example.idolkingdom.model.Idol
import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.model.Image
import com.example.idolkingdom.repository.EntertainmentRepository
import com.example.idolkingdom.repository.IdolGroupRepository
import com.example.idolkingdom.repository.IdolRepository
import com.example.idolkingdom.repository.ImageRepository
import com.example.idolkingdom.util.IdolImage
import com.example.idolkingdom.util.IdolJsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val S3_PATH = "https://s3.ap-northeast-2.amazonaws.com/idol.kingdom/"

@Component
class InsertIdolListEvent(
    @Autowired private val entertainmentRepository: EntertainmentRepository,
    @Autowired private val imageRepository: ImageRepository,
    @Autowired private val idolGroupRepository: IdolGroupRepository,
    @Autowired private val idolRepository: IdolRepository
) : ApplicationListener<ApplicationReadyEvent> {

    @Transactional
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        IdolJsonParser.parse("static/idol.json").forEach { dao ->
            var idol = Idol(
                name = dao.name,
                bloodType = Idol.BloodType.get(dao.info.bloodType),
                groups = listOf(),
                entertainment = null,
                graduation = dao.info.graduation,
                dateOfBirth = if (dao.info.birth.isNotEmpty()) LocalDate.parse(dao.info.birth, dateFormatter) else null,
                hometown = dao.info.hometown,
                images = listOf()
            )
            if (dao.image.isNotEmpty())
                imageRepository.save(Image(url = dao.image)).let {
                    idol = idol.copy(images = listOf(it))
                }
            if (dao.info.entertainmentName.isNotEmpty()) {
                val entertainment = entertainmentRepository.findByName(dao.info.entertainmentName).firstOrNull()
                idol = idol.copy(
                    entertainment = entertainment ?: entertainmentRepository.save(
                        Entertainment(name = dao.info.entertainmentName)
                    )
                )
            }
            if (dao.groups.isNotEmpty()) {
                dao.groups.forEach {
                    val idolImageName = IdolImage.getIdolGroupByName(it)
                    val images: List<Image> = if (idolImageName != null) listOf(
                        Image(url = "$S3_PATH$idolImageName/marker.png"),
                        Image(url = "$S3_PATH$idolImageName/circle.png"),
                        Image(url = "$S3_PATH$idolImageName/card.png")
                    ) else listOf()

                    idol = idol.copy(

                        groups = listOf(
                            idolGroupRepository.findByName(it).firstOrNull() ?: idolGroupRepository.save(
                                IdolGroup(
                                    name = it,
                                    images = images.map { image -> imageRepository.save(image) }
                                )
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
