package com.example.idolkingdom.controller.response

import com.example.idolkingdom.dto.IdolDto

data class RankResponse(
    val ranks : List<Rank>
){
    data class Rank(
        val idolDto: IdolDto,
        val ballotIds : List<Long>
    )
}