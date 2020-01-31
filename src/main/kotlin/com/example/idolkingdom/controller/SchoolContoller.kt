package com.example.idolkingdom.controller;


import com.example.idolkingdom.dto.SchoolResponseDto
import com.example.idolkingdom.dto.LocationDto
import com.example.idolkingdom.exception.WrongParameterAcceptedException
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

    @GetMapping("/school/list")
    fun get(@RequestParam("name") name: String?): ResponseEntity<List<School>> = ResponseEntity.status(HttpStatus.OK)
        .body(
            if (name == null)
                schoolService.getAll()
            else schoolService.get(name)
        )


    @GetMapping("/school")
    fun get(@RequestParam schoolId: Long): ResponseEntity<SchoolResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(schoolService.get(schoolId))

    @GetMapping("/school/search")
    fun serach(
        @RequestParam("query") query: String?,
        @RequestParam("start_x") startX: Float?,
        @RequestParam("start_y") startY: Float?,
        @RequestParam("end_x") endX: Float?,
        @RequestParam("end_y") endY: Float?,
        @RequestParam("size") size: Int?
    ): ResponseEntity<List<School>> = ResponseEntity.status(HttpStatus.OK)
        .body(
            if (query != null)
                schoolService.search(query, size)
            else if (startX != null && startY != null && endX != null && endY != null)
                schoolService.search(LocationDto(startX, startY), LocationDto(endX, endY), size)
            else throw WrongParameterAcceptedException("query or (x, y) must not be null")
        )
}
