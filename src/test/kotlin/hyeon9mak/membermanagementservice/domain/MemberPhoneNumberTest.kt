package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MemberPhoneNumberTest : FreeSpec({

    "숫자로만 이루어진 10~11 자리의 전화번호를 생성할 수 있다." {
        val length11 = MemberPhoneNumber("01012345678")
        val length10 = MemberPhoneNumber("0101234567")

        length11 shouldBe MemberPhoneNumber("01012345678")
        length10 shouldBe MemberPhoneNumber("0101234567")
    }

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
