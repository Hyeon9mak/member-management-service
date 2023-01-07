package hyeon9mak.membermanagementservice.domain

import java.time.LocalDateTime
import java.util.*

class MemberAuthenticationCode(
    val phoneNumber: MemberPhoneNumber
) {
    val id: Long = 0
    val code: String = UUID.randomUUID().toString().take(8)
    var authenticated: Boolean = false
    private val createdAt: LocalDateTime = LocalDateTime.now()
    private val expiredAt: LocalDateTime = createdAt.plusMinutes(10L)

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
