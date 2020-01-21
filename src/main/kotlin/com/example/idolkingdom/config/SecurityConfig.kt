package com.example.idolkingdom.config

import com.example.idolkingdom.interceptor.AuthInterceptor
import com.example.idolkingdom.service.impl.JwtTokenUtil
import lombok.AllArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
@AllArgsConstructor
class SecurityConfig : WebSecurityConfigurerAdapter(), WebMvcConfigurer {

    @Bean
    fun jwtTokenUtil(): JwtTokenUtil = JwtTokenUtil()

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) { // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/**")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthInterceptor(jwtTokenUtil()))
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/users")
            .excludePathPatterns("/api/login")
            .excludePathPatterns("/api/school/**")
            .excludePathPatterns("/api/idol/**")
    }
}