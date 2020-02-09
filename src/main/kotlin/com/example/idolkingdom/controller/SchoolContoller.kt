package com.example.idolkingdom.controller;


import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.dto.SchoolResponseDto
import com.example.idolkingdom.exception.WrongParameterAcceptedException
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
    fun get(@RequestParam(value = "schoolIds", required = true) schoolIds: List<Long>): ResponseEntity<List<SchoolResponseDto>> =
        ResponseEntity.status(HttpStatus.OK).body(schoolService.get(schoolIds))

    @GetMapping("/school/search")
    fun serach(
        @RequestParam("query") query: String?,
        @RequestParam("size") size: Int?
    ): ResponseEntity<List<SchoolResponseDto>> = ResponseEntity.status(HttpStatus.OK)
        .body(
            if (query != null)
                schoolService.search(query, size)
            else throw WrongParameterAcceptedException("query or (x, y) must not be null")
        )

    @GetMapping("/school/nearby")
    fun nearby(
        @RequestParam("start_x") startX: Float?,
        @RequestParam("start_y") startY: Float?,
        @RequestParam("end_x") endX: Float?,
        @RequestParam("end_y") endY: Float?,
        @RequestParam("size") size: Int?
    ): ResponseEntity<List<SchoolResponseDto>> = ResponseEntity.status(HttpStatus.OK)
        .body(
            if (startX != null && startY != null && endX != null && endY != null)
                schoolService.search(LocationDto(startX, startY), LocationDto(endX, endY), size)
            else throw WrongParameterAcceptedException("query or (x, y) must not be null")
        )
}
