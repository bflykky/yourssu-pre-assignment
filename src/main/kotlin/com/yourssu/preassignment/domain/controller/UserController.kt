package com.yourssu.preassignment.domain.controller

import com.yourssu.preassignment.domain.request.DeleteRequestDto
import com.yourssu.preassignment.domain.request.UserJoinRequestDto
import com.yourssu.preassignment.domain.response.UserJoinResponse
import com.yourssu.preassignment.domain.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping(value = ["/users"])
    fun join(@RequestBody userJoinRequestDto: UserJoinRequestDto): UserJoinResponse {
        return userService.join(userJoinRequestDto);
    }

    @DeleteMapping(value = ["/users"])
    fun quit(@RequestBody deleteRequestDto: DeleteRequestDto) {
        return userService.quit(deleteRequestDto)
    }

}