package com.yourssu.preassignment.request

import javax.validation.constraints.NotBlank

class CommentRequestDto (
    val email: String,

    val password: String,

    @field:NotBlank(message = "댓글은 비워두거나, 공백만을 입력할 수 없습니다.")
    val content: String?
)