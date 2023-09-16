package com.yourssu.preassignment.domain.request

import lombok.Getter

@Getter
class UserJoinRequestDto(
    val email: String,

    val password: String,

    val username: String
)