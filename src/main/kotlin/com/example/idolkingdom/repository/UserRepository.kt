package com.example.idolkingdom.repository;

import com.example.idolkingdom.model.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long> {
        fun findByEmail(email: String): User?
}
