package com.example.idolkingdom.controller;

import com.example.idolkingdom.model.IdolGroup
import com.example.idolkingdom.service.IdolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class IdolContoller(@Autowired private val idolService: IdolService) {

    @GetMapping("/idol/list")
    fun get(@RequestParam("name") name: String?): ResponseEntity<List<IdolGroup>> = ResponseEntity.status(HttpStatus.OK)
        .body(
            if (name == null)
                idolService.getAll()
            else idolService.get(name)
        )

    @GetMapping("/idol/search")
    fun serach(@RequestParam("query") query: String): ResponseEntity<List<IdolGroup>> = ResponseEntity.status(HttpStatus.OK)
        .body(idolService.search(query))
}
