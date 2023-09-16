package com.yourssu.preassignment.domain.service

import com.yourssu.preassignment.domain.entity.User
import com.yourssu.preassignment.domain.repository.ArticleRepository
import com.yourssu.preassignment.domain.repository.UserRepository
import com.yourssu.preassignment.domain.request.DeleteRequestDto
import com.yourssu.preassignment.domain.request.UserJoinRequestDto
import com.yourssu.preassignment.domain.response.UserJoinResponse
import com.yourssu.preassignment.global.exception.PasswordFalseException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
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

    fun quit(deleteRequestDto: DeleteRequestDto) {
        val user: User = userRepository.findByEmail(deleteRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(deleteRequestDto.password, user)

        // 해당 회원이 작성한 게시글 전부 삭제
        for (article in user.articleList) {
            articleRepository.delete(article)
        }
        userRepository.delete(user)
    }

    fun validatePassword(password: String, user: User) {
        if (passwordEncoder.matches(password, user.password) == false) {
            throw PasswordFalseException("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.")
        }
    }
}