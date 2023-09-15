package com.yourssu.preassignment.entity

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.Email
import lombok.Getter

@Entity
@Getter
class User (
    @Id
    @Column(name = "user_id")
    private var id: Long,

    @Column(name = "created_at")
    private var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt: LocalDateTime,

    @Email
    private var email: String,

    private var password: String,

    private var username: String,

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    private var articleList: List<Article> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    private var commentList: List<Comment> = mutableListOf()
) {

}