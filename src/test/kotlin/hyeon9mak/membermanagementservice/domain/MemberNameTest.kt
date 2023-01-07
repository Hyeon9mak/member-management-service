package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MemberNameTest : FreeSpec({

    "한글로만 이루어진 이름을 생성할 수 있다." {
        val name = MemberName.of("최현구")
        name.value shouldBe "최현구"
    }

    "알파벳과 공백으로 이루어진 이름을 생성할 수 있다." {
        val name = MemberName.of("hyeon gu")
        name.value shouldBe "hyeon gu"
    }

    "이름 길이가 2 미만 100 초과일 경우 에외가 발생한다." - {
        listOf(
            "현",
            "A",
            "가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차가나다라마바사아자차하",
            "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijK",
        ).forAll { invalidValue ->

            "이름 $invalidValue(${invalidValue.length}) 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberName.of(invalidValue) }
                exception.message shouldBe "회원 이름 형식이 올바르지 않습니다."
            }

        }
    }

    "알파벳과 한글이 섞인 이름은 생성할 수 없다." - {
        listOf(
            "현구MAK",
            "씩씩한JAMES",
        ).forAll { invalidValue ->

            "이름 $invalidValue 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberName.of(invalidValue) }
                exception.message shouldBe "회원 이름 형식이 올바르지 않습니다."
            }

        }
    }

    "한글로 이루어진 경우 중간 공백을 허용하지 않는다." - {
        listOf(
            "최 현구",
            "현구 입니다",
        ).forAll { invalidValue ->

            "이름 $invalidValue 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> { MemberName.of(invalidValue) }
                exception.message shouldBe "회원 이름 형식이 올바르지 않습니다."
            }

        }
    }

    "이름을 생성할 때 앞뒤 공백을 모두 제거한다." - {
        listOf(
            " good boy",
            "good boy ",
            " good boy ",
        ).forEach { value ->

            "이름 [$value] 은 [${value.trim()}] 으로 생성된다." {
                val name = MemberName.of(value)
                name.value shouldBe value.trim()
            }

        }
    }
})
