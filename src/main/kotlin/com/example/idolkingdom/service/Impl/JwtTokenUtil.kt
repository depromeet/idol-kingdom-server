package com.example.idolkingdom.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.Verification
import com.example.idolkingdom.model.User
import org.springframework.stereotype.Component


@Component
class JwtTokenUtil() {

    private val HEADER_PREFIX = "Bearer "

    fun generateToken(user: User): String {
        val token = JWT.create()
            .withIssuer("IDOL_KINGDOM")
            .withClaim("USERNAME", user.nickName)
            .withClaim("USER_ID", user.id)
            .sign(Algorithm.HMAC256("JWT_KEY"))

        return token
    }

    fun getMemberId(token: String): Long? {
        val jwt = JWT.decode(token)
        return jwt.getClaim("USER_ID").asLong()
    }

    fun decodeToken(header: String): Long? {
        val token = tokenExtractor(header)
        val verification: Verification = JWT.require(Algorithm.HMAC256("JWT_KEY"))
        val verifier: JWTVerifier = verification.build()
        val decodedJWT: DecodedJWT = verifier.verify(token)

        val claims = decodedJWT.claims
        return claims["USER_ID"]!!.asLong()

    }

    private fun tokenExtractor(header: String): String? {
        return header.substring(HEADER_PREFIX.length)
    }
}
