package com.example.idolkingdom.repository

import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.model.Image
import org.springframework.data.jpa.repository.JpaRepository


interface ImageRepository : JpaRepository<Image, Long>
