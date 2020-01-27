package com.example.idolkingdom.controller

import com.example.idolkingdom.dto.BallotRequestDto
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.model.Ballot
import com.example.idolkingdom.model.Vote
import com.example.idolkingdom.service.VoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class VoteController(@Autowired private val voteService: VoteService) {

    @PostMapping("/vote")
    fun createVote(@RequestBody voteRequestDto: VoteRequestDto): ResponseEntity<Vote> =
        ResponseEntity.status(HttpStatus.CREATED).body(voteService.createVote(voteRequestDto))

    @DeleteMapping("/vote")
    fun deleteVote(@RequestParam voteId: Long): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.OK).body(voteService.deleteVote(voteId))

    @PostMapping("/ballot")
    fun createBallot(@RequestBody ballotRequestDto: BallotRequestDto): ResponseEntity<Ballot> =
        ResponseEntity.status(HttpStatus.CREATED).body(voteService.castBallot(ballotRequestDto))

}