package com.yourssu.preassignment.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import lombok.Getter

@Entity
@Getter
class Comment (
    @Id
    @Column(name = "comment_id")
    private var id: Long,

    @Column(name = "created_at")
    private var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt: LocalDateTime,

    private var content: String,

    @ManyToOne
    @JoinColumn(name = "article_id")
    private var article: Article,

    @ManyToOne
    @JoinColumn(name = "user_id")
    private var user: User
) {
}