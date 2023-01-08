package hyeon9mak.membermanagementservice.application.authentication

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class JwtTokenProviderTest : StringSpec({

    val jwtTokenProvider = JwtTokenProvider()

    "payload 를 통해 토큰을 생성 후, 토큰을 이용해 다시 payload 를 얻을 수 있다." {
        val token = jwtTokenProvider.createToken("jinha3507@gmail.com")
        jwtTokenProvider.parseToken(token = token) shouldBe "jinha3507@gmail.com"
    }
})
