package com.yourssu.preassignment.entity

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Article (
    @Id
    @Column(name = "article_id")
    private var id: Long,

    @Column(name = "created_at")
    private var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt: LocalDateTime,

    private var title: String,

    private var content: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    private var user: User,

    @OneToMany(mappedBy = "article", cascade = arrayOf(CascadeType.ALL))
    private var commentList: List<Comment> = mutableListOf()
) {

}