package com.example.idolkingdom.controller;

import com.example.idolkingdom.controller.response.IdolGroupResponse
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
class IdolContoller(
    @Autowired private val idolService: IdolService
) {

    @GetMapping("/idol/list")
    fun get(@RequestParam("name") name: String?): ResponseEntity<IdolGroupResponse> = ResponseEntity.status(HttpStatus.OK)
        .body(
            IdolGroupResponse((if (name == null)
                idolService.getAll()
            else idolService.get(name)).map { IdolGroupResponse.IdolGroup.of(it) })
        )

    @GetMapping("/idol")
    fun get(@RequestParam idolId: Long): ResponseEntity<IdolGroupResponse.IdolGroup> =
        ResponseEntity.status(HttpStatus.OK).body(IdolGroupResponse.IdolGroup.of(idolService.get(idolId)))

    @GetMapping("/idol/search")
    fun serach(@RequestParam("query") query: String): ResponseEntity<IdolGroupResponse> = ResponseEntity.status(HttpStatus.OK)
        .body(IdolGroupResponse(idolService.search(query).map { IdolGroupResponse.IdolGroup.of(it) }))
}
