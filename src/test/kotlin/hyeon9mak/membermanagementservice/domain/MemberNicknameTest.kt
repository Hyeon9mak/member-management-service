package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

internal class MemberNicknameTest : FreeSpec({

    "알파벳, 숫자, 한글로 이루어진 닉네임을 생성할 수 있다." {
        val hyeon9makNickname = MemberNickname("hyeon9mak")
        val choiHyeonGuNickname = MemberNickname("최현구")
        val oneTwoThreeNickname = MemberNickname("123")

        hyeon9makNickname shouldBe MemberNickname("hyeon9mak")
        choiHyeonGuNickname shouldBe MemberNickname("최현구")
        oneTwoThreeNickname shouldBe MemberNickname("123")
    }

    "닉네임에 알파벳, 숫자, 한글 외 특수문자가 포함될 경우 예외가 발생한다." - {
        listOf(
            "#",
            "현구막!@#",
        ).forAll { invalidValue ->

            "닉네임 $invalidValue 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberNickname(invalidValue) }
                exception.message shouldBe "회원 닉네임 형식이 올바르지 않습니다."
            }

        }
    }

    "닉네임의 길이가 2 미만이거나 10 초과일 경우 예외가 발생한다." - {
        listOf(
            "현",
            "지져스홀리몰리메리크리스마스",
        ).forAll { invalidValue ->

            "닉네임 $invalidValue(${invalidValue.length}) 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberNickname(invalidValue) }
                exception.message shouldBe "회원 닉네임 형식이 올바르지 않습니다."
            }

        }
    }
})
