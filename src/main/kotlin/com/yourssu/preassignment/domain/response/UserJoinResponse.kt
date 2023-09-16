package com.yourssu.preassignment.domain.response

import lombok.Builder
import lombok.Getter

@Getter
class UserJoinResponse(
    val email: String,

    val username: String
)