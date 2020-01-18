package com.example.idolkingdom.controller;

import com.example.idolkingdom.model.School
import com.example.idolkingdom.service.SchoolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SchoolContoller(@Autowired private val schoolService: SchoolService) {

    @GetMapping("/school")
    fun get(@RequestParam("name") name: String?): ResponseEntity<List<School>> = ResponseEntity.status(HttpStatus.OK)
        .body(
            if (name == null)
                schoolService.getAll()
            else schoolService.get(name)
        )

    @GetMapping("/school/search")
    fun serach(@RequestParam("query") query: String): ResponseEntity<List<School>> = ResponseEntity.status(HttpStatus.OK)
        .body(schoolService.search(query))
}
