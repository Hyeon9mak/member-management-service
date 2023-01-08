package hyeon9mak.membermanagementservice.application.read

import hyeon9mak.membermanagementservice.domain.MemberFixture.Member
import hyeon9mak.membermanagementservice.persistence.InMemoryMemberRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class MemberInfoServiceTest : FreeSpec({

    val memberRepository = InMemoryMemberRepository()
    val memberInfoService = MemberInfoService(memberRepository)

    beforeEach { memberRepository.init() }

    "이메일을 통해 회원정보를 조회할 수 있다." {
        memberRepository.save(Member(email = "jinha3507@gmail.com"))
        val response = memberInfoService.getInfoByEmail(email = "jinha3507@gmail.com")
        response.email shouldBe "jinha3507@gmail.com"
    }
})
