package com.example.idolkingdom.interceptor

import com.example.idolkingdom.exception.UnauthorizedException
import com.example.idolkingdom.service.impl.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthInterceptor(@Autowired private val jwtTokenUtil: JwtTokenUtil) : HandlerInterceptorAdapter() {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("authorization")
        val id: Long? = jwtTokenUtil.decodeToken(token)
            ?: throw UnauthorizedException("토큰 값이 유효하지 않습니다.")

        request.setAttribute("id", id)
        return super.preHandle(request, response!!, handler!!)
    }

}