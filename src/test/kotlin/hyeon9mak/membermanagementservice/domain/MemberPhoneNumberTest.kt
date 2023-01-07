package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MemberPhoneNumberTest : FreeSpec({

    "전화번호에 숫자 외 값이 포함될 경우 예외가 발생한다." - {
        listOf(
            "010-1234-5678",
            "010 1234 5678",
        ).forAll { invalidValue ->

            "전화번호 $invalidValue 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberPhoneNumber(invalidValue) }
                exception.message shouldBe "회원 전화번호 형식이 올바르지 않습니다."
            }

        }
    }

    "전화번호 길이가 10 미만 11 초과일 경우 예외가 발생한다." - {
        listOf(
            "123456789",
            "123456789000",
        ).forAll { invalidValue ->

            "전화번호 $invalidValue(${invalidValue.length}) 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberPhoneNumber(invalidValue) }
                exception.message shouldBe "회원 전화번호 형식이 올바르지 않습니다."
            }

        }
    }
})
