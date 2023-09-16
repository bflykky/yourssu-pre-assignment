package com.yourssu.preassignment.domain.service

import com.yourssu.preassignment.domain.entity.Article
import com.yourssu.preassignment.domain.entity.Comment
import com.yourssu.preassignment.domain.entity.User
import com.yourssu.preassignment.domain.repository.ArticleRepository
import com.yourssu.preassignment.domain.repository.CommentRepository
import com.yourssu.preassignment.domain.repository.UserRepository
import com.yourssu.preassignment.domain.request.CommentRequestDto
import com.yourssu.preassignment.domain.request.DeleteRequestDto
import com.yourssu.preassignment.domain.response.CommentResponse
import com.yourssu.preassignment.global.exception.PasswordFalseException
import com.yourssu.preassignment.global.exception.WriterAuthorizationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
    private val commentRepository: CommentRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun addComment(articleId: Long, commentRequestDto: CommentRequestDto): CommentResponse {
        val article: Article = articleRepository.findById(articleId).orElse(null)
            ?: throw EntityNotFoundException("해당 id를 가진 게시글이 존재하지 않습니다.")

        val user: User = userRepository.findByEmail(commentRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

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
        if (articleRepository.existsById(articleId) == false) {
            throw EntityNotFoundException("해당 id를 가진 게시글이 존재하지 않습니다.")
        }

        val user: User = userRepository.findByEmail(commentRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        val comment: Comment = commentRepository.findById(commentId).orElse(null)
            ?: throw EntityNotFoundException("해당 id를 가진 댓글이 존재하지 않습니다.")

        validatePassword(commentRequestDto.password, user)
        validateWriter(user, comment)

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

    fun deleteComment(articleId: Long, commentId: Long, deleteRequestDto: DeleteRequestDto) {
        if (articleRepository.existsById(articleId) != true) {
            throw EntityNotFoundException("해당 id를 가진 게시글이 존재하지 않습니다.")
        }

        val comment: Comment = commentRepository.findById(commentId).orElse(null)
            ?: throw EntityNotFoundException("해당 id를 가진 댓글이 존재하지 않습니다.")

        val user: User = userRepository.findByEmail(deleteRequestDto.email).orElse(null)
            ?: throw EntityNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다.")

        validatePassword(deleteRequestDto.password, user)
        validateWriter(user, comment)

        commentRepository.delete(comment)
    }

    fun validateWriter(user: User, comment: Comment) {
        if (user.id != comment.user.id) {
            throw WriterAuthorizationException("댓글의 작성자만 수정/삭제가 가능합니다.")
        }
    }

    fun validatePassword(password: String, user: User) {
        if (passwordEncoder.matches(password, user.password) == false) {
            throw PasswordFalseException("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.")
        }
    }

}