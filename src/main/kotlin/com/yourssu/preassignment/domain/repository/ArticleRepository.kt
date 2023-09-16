package com.yourssu.preassignment.domain.repository

import com.yourssu.preassignment.domain.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {

}