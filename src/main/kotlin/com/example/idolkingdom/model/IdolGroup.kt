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
    val members: List<Idol> = listOf(),
    @OneToMany
    @JoinColumn(name = "idol_group_id")
    val images: List<Image> = listOf(),
    @ManyToMany(mappedBy = "idols")
    val fans: List<User> = listOf(),
    @OneToMany(mappedBy = "idol")
    val ballots: List<Ballot> = listOf()
)
