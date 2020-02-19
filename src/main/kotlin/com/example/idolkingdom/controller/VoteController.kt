package com.example.idolkingdom.controller

import com.example.idolkingdom.controller.request.BallotRequest
import com.example.idolkingdom.controller.response.BallotResponse
import com.example.idolkingdom.dto.VoteRequestDto
import com.example.idolkingdom.dto.VoteResponseDto
import com.example.idolkingdom.service.VoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class VoteController(@Autowired private val voteService: VoteService) {

    @GetMapping("/votes")
    fun getVoteList(): ResponseEntity<List<VoteResponseDto>> =
        ResponseEntity.status(HttpStatus.OK).body(voteService.getVoteList())

    @GetMapping("/vote")
    fun getVote(@RequestParam voteId: Long): ResponseEntity<VoteResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(voteService.get(voteId))

    @GetMapping("/vote/current")
    fun getCurrentVote(): ResponseEntity<VoteResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(voteService.getVoteList().last())

    @GetMapping("/vote/ballots")
    fun getBallotList(@RequestParam(value = "ballotIds", required = true) ballotIds: List<Long>): ResponseEntity<List<BallotResponse>> =
        ResponseEntity.status(HttpStatus.OK).body(voteService.getBallots(ballotIds))

    @PostMapping("/vote")
    fun createVote(@RequestBody voteRequestDto: VoteRequestDto): ResponseEntity<VoteResponseDto> =
        ResponseEntity.status(HttpStatus.CREATED).body(voteService.createVote(voteRequestDto))

    @DeleteMapping("/vote")
    fun deleteVote(@RequestParam voteId: Long): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.OK).body(voteService.deleteVote(voteId))

    @PostMapping("/ballot")
    fun createBallot(@RequestAttribute("id") id: Long, @RequestBody ballotRequest: BallotRequest): ResponseEntity<BallotResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(voteService.castBallot(id, ballotRequest))
}