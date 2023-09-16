package com.yourssu.preassignment.controller

import com.yourssu.preassignment.request.DeleteRequestDto
import com.yourssu.preassignment.request.UserJoinRequestDto
import com.yourssu.preassignment.response.UserJoinResponse
import com.yourssu.preassignment.service.UserService
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