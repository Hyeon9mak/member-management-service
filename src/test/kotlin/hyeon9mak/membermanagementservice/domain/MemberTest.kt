package hyeon9mak.membermanagementservice.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class MemberTest : StringSpec({

    "Member 객체는 ID 를 통해 동등성을 비교한다." {
        val hyeon9makMember = Member.withId(
            id = 1,
            email = "hyeon9mak@abc.com",
            password = "password123!@#",
            name = "최현구",
            nickname = "hyeon9mak",
            phoneNumber = "01012345678",
        )
        val hongGilDongMember = Member.withId(
            id = 1,
            email = "choiHyeonGu@abc.com",
            password = "password123!@#",
            name = "홍길동",
            nickname = "GilDong",
            phoneNumber = "01098765432",
        )

        hyeon9makMember shouldBe hongGilDongMember
    }
})
