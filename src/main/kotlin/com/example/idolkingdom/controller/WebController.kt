package com.example.idolkingdom.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class WebController {
    @GetMapping("/hello")
    fun index(model: Model):String{
        return "hello"
    }
}