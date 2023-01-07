package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MemberPasswordTest : FreeSpec({

    "숫자, 알파벳, 특수문자가 모두 포함된 8~20 자리 비밀번호를 생성할 수 있다." {
        val password = MemberPassword("passworD123!@#")
        password.value shouldBe "passworD123!@#"
    }

    "비밀번호 길이가 8 미만이거나 20 초과일 경우 예외가 발생한다." - {
        listOf(
            "12ab!@3",
            "123456789012345!@#ABC",
        ).forAll { invalidValue ->

            "비밀번호 $invalidValue(${invalidValue.length}) 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberPassword(invalidValue) }
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
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberPassword(invalidValue) }
                exception.message shouldBe "회원 비밀번호 형식이 올바르지 않습니다."
            }

        }
    }
})
