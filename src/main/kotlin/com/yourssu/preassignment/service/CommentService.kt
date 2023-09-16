package com.yourssu.preassignment.service

import com.yourssu.preassignment.entity.Article
import com.yourssu.preassignment.entity.Comment
import com.yourssu.preassignment.entity.User
import com.yourssu.preassignment.repository.ArticleRepository
import com.yourssu.preassignment.repository.CommentRepository
import com.yourssu.preassignment.repository.UserRepository
import com.yourssu.preassignment.request.ArticleRequestDto
import com.yourssu.preassignment.request.CommentRequestDto
import com.yourssu.preassignment.response.ArticleResponse
import com.yourssu.preassignment.response.CommentResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
    private val commentRepository: CommentRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun addComment(articleId: Long, commentRequestDto: CommentRequestDto): CommentResponse {
        val article: Article = articleRepository.findById(articleId).orElse(null)
            ?: throw RuntimeException("해당 id를 가진 게시글이 존재하지 않습니다.")

        val user: User = userRepository.findByEmail(commentRequestDto.email).orElse(null)
            ?: throw RuntimeException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(commentRequestDto.password, user)

        val currentTime: LocalDateTime = LocalDateTime.now()
        val comment: Comment = Comment(
            createdAt = currentTime,
            updatedAt = currentTime,
            content = commentRequestDto.content as String,
            article = article,
            user = user
        )

        val commentId: Long = commentRepository.save(comment).id

        return CommentResponse(
            commentId = commentId,
            email = user.email,
            content = comment.content
        )
    }

    fun editComment(articleId: Long, commentId: Long, commentRequestDto: CommentRequestDto): CommentResponse {
        val article: Article = articleRepository.findById(articleId).orElse(null)
            ?: throw RuntimeException("해당 id를 가진 게시글이 존재하지 않습니다.")

        val comment: Comment = commentRepository.findById(commentId).orElse(null)
            ?: throw RuntimeException("해당 id를 가진 댓글이 존재하지 않습니다.")

        val user: User = userRepository.findByEmail(commentRequestDto.email).orElse(null)
            ?: throw RuntimeException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(commentRequestDto.password, user)

        if (user.id != comment.user.id) {
            throw RuntimeException("댓글의 작성자가 아니므로 수정 권한이 없습니다.")
        }

        // 댓글 수정
        comment.content = commentRequestDto.content as String
        comment.updatedAt = LocalDateTime.now()

        val editedCommentId: Long = commentRepository.save(comment).id

        return CommentResponse(
            commentId = editedCommentId,
            email = user.email,
            content = comment.content
        )
    }

    fun validatePassword(password: String, user: User) {
        if (passwordEncoder.matches(password, user.password) == false) {
            throw RuntimeException("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.")
        }
    }

}