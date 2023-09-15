package com.yourssu.preassignment.service

import com.yourssu.preassignment.entity.User
import com.yourssu.preassignment.repository.UserRepository
import com.yourssu.preassignment.request.UserJoinRequestDto
import com.yourssu.preassignment.response.UserJoinResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun join(userJoinRequestDto: UserJoinRequestDto): UserJoinResponse {
        val encodedPassword: String = passwordEncoder.encode(userJoinRequestDto.password)
        val currentTime: LocalDateTime = LocalDateTime.now();

        val user: User = User(
            createdAt = currentTime,
            updatedAt = currentTime,
            email = userJoinRequestDto.email,
            password = encodedPassword,
            username = userJoinRequestDto.username
        )
        userRepository.save(user)

        return UserJoinResponse(email = user.email, username = user.username)
    }
}