package com.yourssu.preassignment.controller

import com.yourssu.preassignment.request.CommentRequestDto
import com.yourssu.preassignment.response.CommentResponse
import com.yourssu.preassignment.service.CommentService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping(value = ["/articles/{articleId}/comments"])
    fun addComment(@PathVariable("articleId") articleId: Long, @Valid @RequestBody commentRequestDto: CommentRequestDto): CommentResponse {
        return commentService.addComment(articleId, commentRequestDto)
    }

}