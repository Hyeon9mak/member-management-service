package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.persistence.InMemoryMemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

internal class MemberRegisterServiceTest : FreeSpec({

    val memberRepository = InMemoryMemberRepository()
    val memberRegisterService = MemberRegisterService(memberRepository = memberRepository)

    beforeEach {
        memberRepository.init()
    }

    "회원을 등록할 수 있다." {
        val request = MemberRegisterRequest(
            nickname = "hyeon9mak",
            email = "jinha3507@gmail.com",
            password = "password123!@#",
            name = "최현구",
            phoneNumber = "01012345678",
        )

        val response = memberRegisterService.register(request)

        response.id shouldBeGreaterThan 0
        response.nickname shouldBe "hyeon9mak"
        response.email shouldBe "jinha3507@gmail.com"
        response.name shouldBe "최현구"
        response.phoneNumber shouldBe "01012345678"
    }

    "중복된 이메일이 존재하는 경우 회원가입을 진행할 수 없다." {
        val request = MemberRegisterRequest(
            nickname = "hyeon9mak",
            email = "jinha3507@gmail.com",
            password = "password123!@#",
            name = "최현구",
            phoneNumber = "01012345678",
        )
        memberRegisterService.register(request)

        val copyedRequest = request.copy(
            nickname = "another",
            password = "another123!@#",
            name = "홍길동",
            phoneNumber = "01098765432",
        )

        val exception =
            shouldThrow<IllegalStateException> { memberRegisterService.register(copyedRequest) }

        exception.message shouldBe "이미 존재하는 이메일입니다."
    }

    "중복된 닉네임이 존재하는 경우 회원가입을 진행할 수 없다." {
        val request = MemberRegisterRequest(
            nickname = "hyeon9mak",
            email = "jinha3507@gmail.com",
            password = "password123!@#",
            name = "최현구",
            phoneNumber = "01012345678",
        )
        memberRegisterService.register(request)

        val copyedRequest = request.copy(
            email = "another@gmail.com",
            password = "another123!@#",
            name = "홍길동",
            phoneNumber = "01098765432",
        )

        val exception =
            shouldThrow<IllegalStateException> { memberRegisterService.register(copyedRequest) }

        exception.message shouldBe "이미 존재하는 닉네임입니다."
    }

    "중복된 전화번호가 존재하는 경우 회원가입을 진행할 수 없다." {
        val request = MemberRegisterRequest(
            nickname = "hyeon9mak",
            email = "jinha3507@gmail.com",
            password = "password123!@#",
            name = "최현구",
            phoneNumber = "01012345678",
        )
        memberRegisterService.register(request)

        val copyedRequest = request.copy(
            email = "another@gmail.com",
            nickname = "another",
            password = "another123!@#",
            name = "홍길동",
        )

        val exception =
            shouldThrow<IllegalStateException> { memberRegisterService.register(copyedRequest) }

        exception.message shouldBe "이미 존재하는 전화번호입니다."
    }
})
