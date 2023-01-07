package hyeon9mak.membermanagementservice.application.authentication

import hyeon9mak.membermanagementservice.domain.MemberFixture.Member
import hyeon9mak.membermanagementservice.persistence.InMemoryMemberAuthenticationCodeRepository
import hyeon9mak.membermanagementservice.persistence.InMemoryMemberRepository
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class MemberAuthenticationServiceTest : FreeSpec({

    val memberRepository = InMemoryMemberRepository()
    val memberAuthenticationCodeRepository = InMemoryMemberAuthenticationCodeRepository()
    val memberAuthenticationService = MemberAuthenticationService(
        authenticationCodeRepository = memberAuthenticationCodeRepository,
        memberRepository = memberRepository,
    )

    beforeEach {
        memberRepository.init()
        memberAuthenticationCodeRepository.init()
    }

    "8자리 회원 인증 코드를 발급받을 수 있다." {
        val response =
            memberAuthenticationService.generateAuthenticationCodeForRegister(phoneNumber = "01012345678")

        response.authenticationCode.length shouldBe 8
    }

    "이미 동일한 전화번호로 가입된 회원계정이 있으면 예외가 발생한다." {
        memberRepository.save(Member(phoneNumber = "01012345678"))

        val exception = shouldThrowExactly<IllegalStateException> {
            memberAuthenticationService.generateAuthenticationCodeForRegister(phoneNumber = "01012345678")
        }

        exception.message shouldBe "이미 전화번호가 동일한 회원계정이 존재합니다."
    }
})
