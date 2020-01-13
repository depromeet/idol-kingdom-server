package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "idol_group")
data class IdolGroup(
    val id: Int,
    val name: String,
    val description: String,
    @ManyToMany
    @JoinTable(name = "idol_group_members")
    val members: List<Idol>,
    val images: List<String>,
    @ManyToMany(mappedBy = "idols")
    val fans : List<User>,
    @OneToMany(mappedBy = "idol_group")
    val ballots: List<Ballot>
)
