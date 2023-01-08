package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeFixture.MemberAuthenticationCode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberAuthenticationCodeJpaRepositoryTest(
    private val memberRepository: MemberAuthenticationCodeJpaRepository,
) : FreeSpec({

    beforeEach {
        memberRepository.deleteAllInBatch()
    }

    "회원 인증 코드를 저장할 수 있다." {
        val memberAuthenticationCode = memberRepository.save(MemberAuthenticationCode())
        memberAuthenticationCode.id shouldBeGreaterThan 0L
    }

    "전화번호를 통해 가장 최근 회원 인증 코드를 조회할 수 있다." {
        val first = memberRepository.save(MemberAuthenticationCode(phoneNumber = "01012345678"))
        val second = memberRepository.save(MemberAuthenticationCode(phoneNumber = "01012345678"))
        val foundMemberAuthenticationCode = memberRepository.findLastOneByPhoneNumber(phoneNumber = "01012345678")
        foundMemberAuthenticationCode shouldNotBe first
        foundMemberAuthenticationCode shouldBe second
    }
})
