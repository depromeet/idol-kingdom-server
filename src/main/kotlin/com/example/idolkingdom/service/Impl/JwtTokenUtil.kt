package com.example.idolkingdom.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.idolkingdom.model.User
import org.springframework.stereotype.Component

@Component
class JwtTokenUtil {

    fun generateToken(user: User): String {
        val token = JWT.create()
                .withIssuer("IDOL_KINGDOM")
                .withClaim("USERNAME", user.nickName)
                .withClaim("MEMBER_ID", user.id)
                .sign(Algorithm.HMAC256("JWT_KEY"))

        return token
    }

    fun getMemberId(token: String): Long? = try {
        val jwt = JWT.decode(token)
        jwt.getClaim("USER_ID").asLong()
    } catch (ex: Exception) {
        null
    }
}
