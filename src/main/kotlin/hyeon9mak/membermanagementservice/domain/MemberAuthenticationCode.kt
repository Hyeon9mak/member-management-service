package hyeon9mak.membermanagementservice.domain

import java.time.LocalDateTime
import java.util.*

class MemberAuthenticationCode(
    val id: Long = 0,
    val phoneNumber: MemberPhoneNumber,
    val code: String = UUID.randomUUID().toString().take(8),
    var authenticated: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    private val expiredAt: LocalDateTime = createdAt.plusMinutes(10L)

    fun authenticate(code: String) {
        require(this.code == code) { "인증 코드가 일치하지 않습니다." }
        check(expiredAt >= LocalDateTime.now()) { "인증 코드가 만료되었습니다." }
        authenticated = true
    }

    fun generateMessage(): String = "[인증 코드] $code"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberAuthenticationCode

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
