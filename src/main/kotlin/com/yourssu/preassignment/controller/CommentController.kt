package com.yourssu.preassignment.controller

import com.yourssu.preassignment.request.CommentRequestDto
import com.yourssu.preassignment.request.DeleteRequestDto
import com.yourssu.preassignment.response.CommentResponse
import com.yourssu.preassignment.service.CommentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
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

    @PatchMapping(value = ["/articles/{articleId}/comments/{commentId}"])
    fun editComment(@PathVariable("articleId") articleId: Long,
                    @PathVariable("commentId") commentId: Long,
                    @Valid @RequestBody commentRequestDto: CommentRequestDto): CommentResponse {
        return commentService.editComment(articleId, commentId, commentRequestDto)
    }

    @DeleteMapping(value = ["/articles/{articleId}/comments/{commentId}"])
    fun deleteComment(@PathVariable("articleId") articleId: Long,
                      @PathVariable("commentId") commentId: Long,
                      @Valid @RequestBody deleteRequestDto: DeleteRequestDto) {
        commentService.deleteComment(articleId, commentId, deleteRequestDto)
    }
}