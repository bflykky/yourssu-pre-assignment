package com.yourssu.preassignment.service

import com.yourssu.preassignment.entity.Article
import com.yourssu.preassignment.entity.User
import com.yourssu.preassignment.repository.ArticleRepository
import com.yourssu.preassignment.repository.UserRepository
import com.yourssu.preassignment.request.ArticleRequestDto
import com.yourssu.preassignment.response.ArticleResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ArticleService(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun writeArticle(articleRequestDto: ArticleRequestDto): ArticleResponse {
        val user: User = userRepository.findByEmail(articleRequestDto.email).orElse(null)
            ?: throw RuntimeException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(articleRequestDto.password, user)

        val currentTime: LocalDateTime = LocalDateTime.now()
        val article: Article = Article(
            createdAt = currentTime,
            updatedAt = currentTime,
            title = articleRequestDto.title as String,
            content = articleRequestDto.content as String,
            user = user
        )
        val articleId: Long = articleRepository.save(article).id

        return ArticleResponse(
            articleId = articleId,
            email = user.email,
            title = article.title,
            content = article.content
        )
    }

    fun validatePassword(password: String, user: User) {
        if (passwordEncoder.matches(password, user.password) == false) {
            throw RuntimeException("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.")
        }
    }
}