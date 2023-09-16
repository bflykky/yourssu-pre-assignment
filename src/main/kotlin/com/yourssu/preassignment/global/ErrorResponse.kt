package com.yourssu.preassignment.global

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class ErrorResponse(
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val time: LocalDateTime,
    val status: Int,
    val message: String
)