package com.yourssu.preassignment.global

import com.yourssu.preassignment.global.exception.DuplicateEmailException
import com.yourssu.preassignment.global.exception.PasswordFalseException
import com.yourssu.preassignment.global.exception.WriterAuthorizationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [DuplicateEmailException::class])
    fun handleDuplicateEmailException(e: DuplicateEmailException): ResponseEntity<ErrorResponse> {
        val errorResponse: ErrorResponse = ErrorResponse(
            time = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.message as String
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse: ErrorResponse = ErrorResponse(
            time = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            message = e.message as String
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [PasswordFalseException::class])
    fun handlePasswordFalseException(e: PasswordFalseException): ResponseEntity<ErrorResponse?> {
        val errorResponse: ErrorResponse = ErrorResponse(
            time = LocalDateTime.now(),
            status = HttpStatus.UNAUTHORIZED.value(),
            message = e.message as String)

        return ResponseEntity<ErrorResponse?>(errorResponse, HttpStatus.UNAUTHORIZED)
    }


    @ExceptionHandler(value = [WriterAuthorizationException::class])
    fun handlePasswordFalseException(e: WriterAuthorizationException): ResponseEntity<ErrorResponse?> {
        val errorResponse: ErrorResponse = ErrorResponse(
            time = LocalDateTime.now(),
            status = HttpStatus.FORBIDDEN.value(),
            message = e.message as String)

        return ResponseEntity<ErrorResponse?>(errorResponse, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val fieldError = e.fieldError
        val errorResponse: ErrorResponse = ErrorResponse(
            time = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            message = fieldError?.defaultMessage as String)

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST)
    }
}