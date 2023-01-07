package hyeon9mak.membermanagementservice.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class MemberEmailTest : FreeSpec({

    "이메일 ID 가 올바르지 않으면 예외가 발생한다." - {
        listOf(
            "",
            "#",
            "#hyeon9mak"
        ).forAll { invalidId ->
            "$invalidId@hyeon9mak.io 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> {
                    MemberEmail("$invalidId@hyeon9mak.io")
                }
                exception.message shouldBe "회원 이메일 형식이 올바르지 않습니다."
            }

        }
    }

    "이메일 도메인이 올바르지 않으면 예외가 발생한다." - {
        listOf(
            "",
            "#",
            "@hyeon9mak"
        ).forAll { invalidDomain ->
            "hyeon9mak@$invalidDomain.io 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> {
                    MemberEmail("hyeon9mak@$invalidDomain.io")
                }
                exception.message shouldBe "회원 이메일 형식이 올바르지 않습니다."
            }

        }
    }

    "이메일 최상위 도메인이 올바르지 않으면 예외가 발생한다." - {
        listOf(
            "",
            "A",
            "@ABC",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        ).forAll { invalidTopDomain ->
            "hyeon9mak@hyeon9mak.$invalidTopDomain 은 예외를 발생시킨다." {
                val exception = shouldThrowExactly<IllegalArgumentException> {
                    MemberEmail("hyeon9mak@hyeon9mak.$invalidTopDomain")
                }
                exception.message shouldBe "회원 이메일 형식이 올바르지 않습니다."
            }

        }
    }
})
