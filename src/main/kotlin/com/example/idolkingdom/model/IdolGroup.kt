package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "idol_group")
data class IdolGroup(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String,
    @ManyToMany
    @JoinTable(name = "idol_group_members")
    var members: List<Idol> = listOf(),
    @OneToMany
    @JoinColumn(name = "idol_group_id")
    var images: List<Image> = listOf(),
    @ManyToMany(mappedBy = "idols")
    var fans: List<User> = listOf(),
    @OneToMany(mappedBy = "idol")
    var ballots: List<Ballot> = listOf()
)
