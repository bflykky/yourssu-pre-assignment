package com.yourssu.preassignment.request

import javax.validation.constraints.NotBlank

class ArticleRequestDto (
    val email: String,

    val password: String,

    @field:NotBlank(message = "게시글 제목은 비워두거나, 공백만을 입력할 수 없습니다.")
    val title: String?,

    @field:NotBlank(message = "게시글 내용은 비워두거나, 공백만을 입력할 수 없습니다.")
    val content: String?
)