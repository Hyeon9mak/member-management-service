package hyeon9mak.membermanagementservice.domain

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Table(
    name = "member_authentication_code",
    indexes = [
        Index(name = "idx_member_authentication_code_phone_number", columnList = "phone_number")
    ]
)
@Entity
class MemberAuthenticationCode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,

    @Column(nullable = false, length = 11)
    val phoneNumber: MemberPhoneNumber,

    @Column(nullable = false, length = 8)
    val code: String = UUID.randomUUID().toString().take(8),

    @Column(nullable = false)
    var authenticated: Boolean = false,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Column(nullable = false)
    private val expiredAt: LocalDateTime = createdAt.plusMinutes(10L)

    fun authenticate(code: String) {
        require(this.code == code) { "인증 코드가 일치하지 않습니다." }
        check(expiredAt >= LocalDateTime.now()) { "인증 코드가 만료되었습니다." }
        authenticated = true
    }

    fun checkAuthenticated(code: String) {
        require(this.code == code) { "인증 코드가 일치하지 않습니다." }
        check(authenticated) { "인증되지 않았습니다." }
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
