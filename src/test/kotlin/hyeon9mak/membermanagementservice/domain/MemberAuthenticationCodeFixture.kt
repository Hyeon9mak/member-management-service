package hyeon9mak.membermanagementservice.domain

import java.time.LocalDateTime

object MemberAuthenticationCodeFixture {

    fun MemberAuthenticationCode(
        phoneNumber: String = "01012345678",
        code: String = "ABCDEFGH",
        authenticated: Boolean = false,
        createdAt: LocalDateTime = LocalDateTime.now()
    ) = MemberAuthenticationCode(
        phoneNumber = MemberPhoneNumber(phoneNumber),
        code = code,
        authenticated = authenticated,
        createdAt = createdAt
    )

    const val AUTHENTICATION_CODE = "ABCDEFGH"
}
