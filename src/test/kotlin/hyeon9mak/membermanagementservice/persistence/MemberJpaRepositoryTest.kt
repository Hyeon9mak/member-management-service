package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberFixture.Member
import hyeon9mak.membermanagementservice.domain.isNotExistsEmail
import hyeon9mak.membermanagementservice.domain.isNotExistsNickname
import hyeon9mak.membermanagementservice.domain.isNotExistsPhoneNumber
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

    "이메일로 회원 정보 존재여부를 확인할 수 있다." {
        val member = memberRepository.save(Member())
        memberRepository.existsByEmail(email = member.getEmailValue()) shouldBe true
        memberRepository.isNotExistsEmail(email = member.email) shouldBe false
    }

    "닉네임으로 회원 정보 존재여부를 확인할 수 있다." {
        val member = memberRepository.save(Member())
        memberRepository.existsByNickname(nickname = member.getNicknameValue()) shouldBe true
        memberRepository.isNotExistsNickname(nickname = member.nickname) shouldBe false
    }

    "전화번호로 회원 정보 존재여부를 확인할 수 있다." {
        val member = memberRepository.save(Member())
        memberRepository.existsByPhoneNumber(phoneNumber = member.getPhoneNumberValue()) shouldBe true
        memberRepository.isNotExistsPhoneNumber(phoneNumber = member.phoneNumber) shouldBe false
    }

    "이메일로 회원 정보를 조회할 수 있다." {
        val member = memberRepository.save(Member())
        val found = memberRepository.findByEmail(email = member.getEmailValue())
        found shouldBe member
    }

    "닉네임으로 회원 정보를 조회할 수 있다." {
        val member = memberRepository.save(Member())
        val found = memberRepository.findByNickname(nickname = member.getNicknameValue())
        found shouldBe member
    }

    "전화번호로 회원 정보를 조회할 수 있다." {
        val member = memberRepository.save(Member())
        val found = memberRepository.findByPhoneNumber(phoneNumber = member.getPhoneNumberValue())
        found shouldBe member
    }

    "이메일, 이름, 전화번호로 회원을 조회할 때" - {
        "모두 일치하는 회원정보를 조회할 수 있다." {
            val member = memberRepository.save(Member(
                email = "jinha3507@gmail.com",
                name = "최현구",
                phoneNumber = "01012345678",
            ))

            val found = memberRepository.findByEmailAndNameAndPhoneNumber(
                email = "jinha3507@gmail.com",
                name = "최현구",
                phoneNumber = "01012345678",
            )

            found shouldBe member
        }

        "하나라도 일치하지 않으면 조회할 수 없다." {
            memberRepository.save(Member(
                email = "jinha3507@gmail.com",
                name = "최현구",
                phoneNumber = "01012345678",
            ))

            memberRepository.findByEmailAndNameAndPhoneNumber(
                email = "abc@def.com",
                name = "최현구",
                phoneNumber = "01012345678",
            ) shouldBe null

            memberRepository.findByEmailAndNameAndPhoneNumber(
                email = "jinha3507@gmail.com",
                name = "CHOI HYEONGU",
                phoneNumber = "01012345678",
            ) shouldBe null

            memberRepository.findByEmailAndNameAndPhoneNumber(
                email = "jinha3507@gmail.com",
                name = "최현구",
                phoneNumber = "01099999999",
            ) shouldBe null
        }
    }
})
