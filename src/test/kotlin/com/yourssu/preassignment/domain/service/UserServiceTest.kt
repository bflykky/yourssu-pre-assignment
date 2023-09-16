package com.yourssu.preassignment.domain.service

import com.yourssu.preassignment.domain.entity.User
import com.yourssu.preassignment.domain.repository.UserRepository
import com.yourssu.preassignment.domain.request.DeleteRequestDto
import com.yourssu.preassignment.domain.request.UserJoinRequestDto
import com.yourssu.preassignment.global.exception.DuplicateEmailException
import com.yourssu.preassignment.global.exception.PasswordFalseException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@SpringBootTest
class UserServiceTest @Autowired constructor(
    val userService: UserService,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    @Test
    @Transactional
    fun 회원가입_테스트() {
        // given
        val email: String = "kkyxw@naver.com"
        val password: String = "20203085"
        val username: String = "김강연"
        val userJoinRequestDto: UserJoinRequestDto = UserJoinRequestDto(
            email = email,
            password = password,
            username = username
        )

        // when
        userService.join(userJoinRequestDto)

        // then
        val user: User = userRepository.findByEmail(email).orElse(null)

        assertThat(user).isNotNull
        assertThat(user.email).isEqualTo(email)
        assertThat(passwordEncoder.matches(password, user.password)).isTrue
        assertThat(user.username).isEqualTo(username)
    }

    @Test
    @Transactional
    @DisplayName("이미 회원가입한 이메일로 회원가입 시도를 하면, 예외가 발생한다.")
    fun 중복된_이메일로_회원가입_시도_시_예외_발생() {
        // given
        val email: String = "kkyxw@naver.com"
        val passwordA: String = "20203085"
        val usernameA: String = "김강연"

        val passwordB: String = "010709"
        val usernameB: String = "연강김"

        val userJoinRequestDtoA: UserJoinRequestDto = UserJoinRequestDto(
            email = email,
            password = passwordA,
            username = usernameA
        )

        val userJoinRequestDtoB: UserJoinRequestDto = UserJoinRequestDto(
            email = email,
            password = passwordB,
            username = usernameB
        )

        // when
        userService.join(userJoinRequestDtoA)

        // then
        assertThatThrownBy {
            userService.join(userJoinRequestDtoB)
        }
            .isExactlyInstanceOf(DuplicateEmailException::class.java)
            .hasMessage("이미 해당 이메일로 가입된 회원이 존재합니다.")
    }

    @Test
    @Transactional
    @DisplayName("존재하지 않는 이메일의 회원을 삭제하려 할 경우, 예외가 발생한다.")
    fun 존재하지_않는_회원_탈퇴_시도() {
        // given
        val emailA: String = "kkyxw@naver.com"
        val passwordA: String = "20203085"
        val username: String = "김강연"

        val emailB: String = "seongtae@naver.com"
        val passwordB: String = "010709"

        val userJoinRequestDto: UserJoinRequestDto = UserJoinRequestDto(
            email = emailA,
            password = passwordA,
            username = username
        )


        // when
        userService.join(userJoinRequestDto)

        // then
        assertThatThrownBy {
            userService.quit(DeleteRequestDto(email = emailB, password = passwordB))
        }
            .isExactlyInstanceOf(EntityNotFoundException::class.java)
            .hasMessage("해당 이메일을 가진 회원이 존재하지 않습니다.")
    }

    @Test
    @Transactional
    @DisplayName("올바르지 않은 비밀번호로 회원 탈퇴를 시도할우, 예외가 발생한다.")
    fun 올바르지_않은_비밀번호로_회원_탈퇴_시도() {
        // given
        val email: String = "kkyxw@naver.com"
        val password: String = "20203085"
        val username: String = "김강연"
        val userJoinRequestDto: UserJoinRequestDto = UserJoinRequestDto(
            email = email,
            password = password,
            username = username
        )

        val falsePassword: String = "20203085false"

        // when
        userService.join(userJoinRequestDto)

        // then
        assertThatThrownBy {
            userService.quit(DeleteRequestDto(email = email, password = falsePassword))
        }
            .isExactlyInstanceOf(PasswordFalseException::class.java)
            .hasMessage("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.")
    }
}