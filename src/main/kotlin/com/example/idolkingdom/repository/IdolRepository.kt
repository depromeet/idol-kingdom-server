package com.example.idolkingdom.repository

import com.example.idolkingdom.model.Idol
import org.springframework.data.jpa.repository.JpaRepository


interface IdolRepository : JpaRepository<Idol, Int>
