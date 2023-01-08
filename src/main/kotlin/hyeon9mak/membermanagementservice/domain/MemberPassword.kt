package hyeon9mak.membermanagementservice.domain

import hyeon9mak.membermanagementservice.exception.MemberAuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@JvmInline
value class MemberPassword private constructor(
    val value: String
) {
    fun authenticate(other: MemberPassword) {
        if (this.value != other.value) {
            throw MemberAuthenticationException("회원 정보가 일치하지 않습니다.")
        }
    }

    companion object {
        private val PATTERN = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,20}$")
        private val PASSWORD_ENCODER = BCryptPasswordEncoder()

        fun createWithEncrypt(value: String): MemberPassword {
            require(value.matches(PATTERN)) { "회원 비밀번호 형식이 올바르지 않습니다." }
            return MemberPassword(PASSWORD_ENCODER.encode(value))
        }
    }
}
