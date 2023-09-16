package com.yourssu.preassignment.domain.service

import com.yourssu.preassignment.domain.entity.Article
import com.yourssu.preassignment.domain.entity.User
import com.yourssu.preassignment.domain.repository.ArticleRepository
import com.yourssu.preassignment.domain.repository.UserRepository
import com.yourssu.preassignment.domain.request.ArticleRequestDto
import com.yourssu.preassignment.domain.request.DeleteRequestDto
import com.yourssu.preassignment.domain.response.ArticleResponse
import com.yourssu.preassignment.global.exception.PasswordFalseException
import com.yourssu.preassignment.global.exception.WriterAuthorizationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class ArticleService(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun writeArticle(articleRequestDto: ArticleRequestDto): ArticleResponse {
        val user: User = userRepository.findByEmail(articleRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

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

    fun editArticle(articleId: Long, articleRequestDto: ArticleRequestDto): ArticleResponse {
        val article: Article = articleRepository.findById(articleId).orElse(null)
            ?: throw EntityNotFoundException("해당 id를 가진 게시글이 존재하지 않습니다.")

        val user: User = userRepository.findByEmail(articleRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(articleRequestDto.password, user)
        validateWriter(user, article)

        // 게시글 수정
        article.title = articleRequestDto.title as String
        article.content = articleRequestDto.content as String
        article.updatedAt = LocalDateTime.now()

        val editedArticleId: Long = articleRepository.save(article).id

        return ArticleResponse(
            articleId = editedArticleId,
            email = user.email,
            title = article.title,
            content = article.content
        )
    }

    fun deleteArticle(articleId: Long, deleteRequestDto: DeleteRequestDto) {
        val article: Article = articleRepository.findById(articleId).orElse(null)
            ?: throw EntityNotFoundException("해당 id를 가진 게시글이 존재하지 않습니다.")

        val user: User = userRepository.findByEmail(deleteRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(deleteRequestDto.password, user)
        validateWriter(user, article)

        articleRepository.delete(article)
    }

    fun validateWriter(user: User, article: Article) {
        if (user.id != article.user.id) {
            throw WriterAuthorizationException("게시글의 작성자만 수정/삭제가 가능합니다.")
        }
    }

    fun validatePassword(password: String, user: User) {
        if (passwordEncoder.matches(password, user.password) == false) {
            throw PasswordFalseException("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.")
        }
    }
}