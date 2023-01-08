package hyeon9mak.membermanagementservice.domain

import hyeon9mak.membermanagementservice.domain.MemberFixture.Member
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import javax.naming.AuthenticationException

internal class MemberTest : FreeSpec({

    "인증을 시도했을 때 비밀번호가 일치하지 않으면 예외가 발생한다." {
        val member = Member(password = "password123!@#")
        val invalidPassword = MemberPassword("invalid321#@!")
        shouldThrowExactly<AuthenticationException> { member.authenticate(password = invalidPassword) }
    }
})