package com.yourssu.preassignment.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import lombok.Getter
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Getter
class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long = 0,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime,

    var content: String,

    @ManyToOne
    @JoinColumn(name = "article_id")
    val article: Article,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
) {
}