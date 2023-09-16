package com.yourssu.preassignment.domain.repository

import com.yourssu.preassignment.domain.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
}