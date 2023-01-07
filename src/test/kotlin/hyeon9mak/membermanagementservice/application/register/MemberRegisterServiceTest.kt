package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.domain.Member
import hyeon9mak.membermanagementservice.domain.MemberRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

internal class MemberRegisterServiceTest : FreeSpec({

    val memberRepository = mockk<MemberRepository>()
    val memberRegisterService = MemberRegisterService(memberRepository)

    "회원을 등록할 수 있다." - {
        every { memberRepository.save(any()) } returns Member.withId(
            id = 1,
            email = "jinha3507@gmail.com",
            password = "password123!@#",
            name = "최현구",
            nickname = "hyeon9mak",
            phoneNumber = "01012345678",
        )

        val request = MemberRegisterRequest(
            nickname = "hyeon9mak",
            email = "jinha3507@gmail.com",
            password = "password123!@#",
            name = "최현구",
            phoneNumber = "01012345678",
        )

        val response = memberRegisterService.register(request)

        response.id shouldBe 1
        response.nickname shouldBe "hyeon9mak"
        response.email shouldBe "jinha3507@gmail.com"
        response.name shouldBe "최현구"
        response.phoneNumber shouldBe "01012345678"
    }
})
