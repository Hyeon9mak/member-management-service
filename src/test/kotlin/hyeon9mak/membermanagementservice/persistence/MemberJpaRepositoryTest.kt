package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberFixture.Member
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException

@SpringBootTest
internal class MemberJpaRepositoryTest(
    private val memberRepository: MemberJpaRepository,
) : FreeSpec({

    beforeEach {
        memberRepository.deleteAllInBatch()
    }

    "회원 정보를 저장할 수 있다." {
        val member = memberRepository.save(Member())
        member.id shouldBeGreaterThan 0L
    }

    "중복된 이메일의 회원 정보를 저장할 수 없다." {
        val hyeon9makMember = Member(
            email = "abc@gmail.com",
            name = "최현구",
            nickname = "hyeon9mak",
            phoneNumber = "01012345678",
        )
        memberRepository.save(hyeon9makMember)

        val duplicateMember = Member(
            email = "abc@gmail.com",
            name = "홍길동",
            nickname = "ddong",
            phoneNumber = "01045671234",
        )
        shouldThrowExactly<DataIntegrityViolationException> { memberRepository.save(duplicateMember) }
    }

    "중복된 닉네임의 회원 정보를 저장할 수 없다." {
        val hyeon9makMember = Member(
            email = "abc@gmail.com",
            name = "최현구",
            nickname = "hyeon9mak",
            phoneNumber = "01012345678",
        )
        memberRepository.save(hyeon9makMember)

        val duplicateMember = Member(
            email = "defgh@gmail.com",
            name = "홍길동",
            nickname = "hyeon9mak",
            phoneNumber = "01045671234",
        )
        shouldThrowExactly<DataIntegrityViolationException> { memberRepository.save(duplicateMember) }
    }

    "중복된 전화번호의 회원 정보를 저장할 수 없다." {
        val hyeon9makMember = Member(
            email = "abc@gmail.com",
            name = "최현구",
            nickname = "hyeon9mak",
            phoneNumber = "01012345678",
        )
        memberRepository.save(hyeon9makMember)

        val duplicateMember = Member(
            email = "defgh@gmail.com",
            name = "홍길동",
            nickname = "ddong",
            phoneNumber = "01012345678",
        )
        shouldThrowExactly<DataIntegrityViolationException> { memberRepository.save(duplicateMember) }
    }

    "이메일로 회원 정보를 조회할 수 있다." {
        val member = memberRepository.save(Member())
        memberRepository.existsByEmail(email = member.getEmailValue()) shouldBe true
    }

    "닉네임으로 회원 정보를 조회할 수 있다." {
        val member = memberRepository.save(Member())
        memberRepository.existsByNickname(nickname = member.getNicknameValue()) shouldBe true
    }

    "전화번호로 회원 정보를 조회할 수 있다." {
        val member = memberRepository.save(Member())
        memberRepository.existsByPhoneNumber(phoneNumber = member.getPhoneNumberValue()) shouldBe true
    }
})