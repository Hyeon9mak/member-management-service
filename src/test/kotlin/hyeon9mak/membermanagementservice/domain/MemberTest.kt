package hyeon9mak.membermanagementservice.domain

import hyeon9mak.membermanagementservice.exception.MemberAuthenticationException
import hyeon9mak.membermanagementservice.domain.MemberFixture.Member
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec

internal class MemberTest : FreeSpec({

    "인증을 시도했을 때 비밀번호가 일치하지 않으면 예외가 발생한다." {
        val member = Member(password = "password123!@#")
        val invalidPassword = MemberPassword.createWithEncrypt("invalid321#@!")
        shouldThrowExactly<MemberAuthenticationException> { member.authenticate(password = invalidPassword) }
    }

    "비밀번호를 변경할 수 있다." {
        val member = Member(password = "password123!@#")
        val newPassword = MemberPassword.createWithEncrypt("newPassword321#@!")
        member.resetPassword(newPassword)
        shouldNotThrowAny { member.authenticate(newPassword) }
    }
})
