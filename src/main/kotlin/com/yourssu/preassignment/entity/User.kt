package com.yourssu.preassignment.entity

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.Email
import lombok.Getter
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Getter
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime,

    @Email
    val email: String,

    val password: String,

    val username: String,

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    val articleList: List<Article> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    val commentList: List<Comment> = mutableListOf()
) {

}