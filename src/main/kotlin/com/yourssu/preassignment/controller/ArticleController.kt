package com.yourssu.preassignment.controller

import com.yourssu.preassignment.request.ArticleRequestDto
import com.yourssu.preassignment.response.ArticleResponse
import com.yourssu.preassignment.service.ArticleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class ArticleController(
    private val articleService: ArticleService
) {
    @PostMapping(value = ["/articles"])
    fun writeArticle(@Valid @RequestBody articleRequestDto: ArticleRequestDto): ArticleResponse {
        return articleService.writeArticle(articleRequestDto)
    }


}