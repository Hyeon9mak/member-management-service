package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import javax.naming.AuthenticationException

internal class MemberPasswordTest : FreeSpec({

    "비밀번호가 일치하는지 확인할 수 있다." {
        val password = MemberPassword.createWithEncrypt("password123!@#")
        val invalidPassword = MemberPassword.createWithEncrypt("invalid321#@!")
        shouldNotThrowAny { password.authenticate(password) }
        shouldThrowExactly<AuthenticationException> { password.authenticate(invalidPassword) }
    }

    "비밀번호 길이가 8 미만이거나 20 초과일 경우 예외가 발생한다." - {
        listOf(
            "12ab!@3",
            "123456789012345!@#ABC",
        ).forAll { invalidValue ->

            "비밀번호 $invalidValue(${invalidValue.length}) 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberPassword.createWithEncrypt(invalidValue) }
                exception.message shouldBe "회원 비밀번호 형식이 올바르지 않습니다."
            }

        }
    }

    "비밀번호에 숫자, 알파벳, 특수문자가 모두 포함되지 않을 경우 예외가 발생한다." - {
        listOf(
            "12345ABC",
            "ABCDEFGH!@#",
            "1234567890!@#",
        ).forAll { invalidValue ->

            "비밀번호 $invalidValue 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberPassword.createWithEncrypt(invalidValue) }
                exception.message shouldBe "회원 비밀번호 형식이 올바르지 않습니다."
            }

        }
    }
})
