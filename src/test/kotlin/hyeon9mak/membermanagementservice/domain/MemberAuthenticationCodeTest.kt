package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

internal class MemberAuthenticationCodeTest : StringSpec({

    "코드를 인증한다." {
        val authenticationCode = MemberAuthenticationCode(
            phoneNumber = MemberPhoneNumber("01012345678"),
            code = "ABCDEFGH",
        )
        authenticationCode.authenticate("ABCDEFGH")
        authenticationCode.authenticated shouldBe true
    }

    "코드가 일치하지 않으면 인증에 실패한다." {
        val authenticationCode = MemberAuthenticationCode(
            phoneNumber = MemberPhoneNumber("01012345678"),
            code = "ABCDEFGH",
        )
        val exception = shouldThrow<IllegalArgumentException> { authenticationCode.authenticate("ZYXWVUTS") }
        exception.message shouldBe "인증 코드가 일치하지 않습니다."
    }

    "인증시간이 10분이 지나면 인증할 수 없다." {
        val authenticationCode = MemberAuthenticationCode(
            phoneNumber = MemberPhoneNumber("01012345678"),
            code = "ABCDEFGH",
            createdAt = LocalDateTime.now().minusMinutes(30L))
        val exception = shouldThrow<IllegalStateException> { authenticationCode.authenticate("ABCDEFGH") }
        exception.message shouldBe "인증 코드가 만료되었습니다."
    }

    "코드가 일치하지 않으면 인증여부를 확인할 수 없다." {
        val authenticationCode = MemberAuthenticationCode(
            phoneNumber = MemberPhoneNumber("01012345678"),
            code = "ABCDEFGH",
            authenticated = true,
        )
        shouldThrow<IllegalArgumentException> { authenticationCode.checkAuthenticated("ZYXWVUTS") }
    }

    "인증여부가 거짓이면 예외가 발생한다." {
        val authenticationCode = MemberAuthenticationCode(
            phoneNumber = MemberPhoneNumber("01012345678"),
            code = "ABCDEFGH",
            authenticated = false,
        )
        shouldThrow<IllegalStateException> { authenticationCode.checkAuthenticated("ABCDEFGH") }
    }
})
