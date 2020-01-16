package com.example.idolkingdom.model

import javax.persistence.*

@Entity
@Table(name = "idol_group")
data class IdolGroup(
    @Id
    @GeneratedValue
    val id: Int,
    val name: String,
    val description: String,
    @ManyToMany
    @JoinTable(name = "idol_group_members")
    val members: List<Idol>,
    @OneToMany
    @JoinColumn(name = "idol_group_id")
    val images: List<Image>,
    @ManyToMany(mappedBy = "idols")
    val fans : List<User>,
    @OneToMany(mappedBy = "idol")
    val ballots: List<Ballot>
)
