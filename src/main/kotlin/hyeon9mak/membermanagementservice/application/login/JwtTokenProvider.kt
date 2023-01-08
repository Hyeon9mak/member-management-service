package hyeon9mak.membermanagementservice.application.login

import hyeon9mak.membermanagementservice.exception.MemberAuthenticationException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {
    fun createToken(payload: String): String {
        val now = Date()
        val expiredAt = createExpireDate(now)
        val claims = Jwts.claims().setSubject(payload)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiredAt)
            .signWith(SECRET_KEY)
            .compact()
    }

    private fun createExpireDate(now: Date): Date = Date(now.time + ONE_HOUR_IN_MILLISECONDS)

    fun parseToken(token: String): String {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.encoded)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: Exception) {
            throw MemberAuthenticationException("올바른 인증 정보가 아닙니다.")
        }
    }

    companion object {
        private const val ONE_HOUR_IN_MILLISECONDS: Long = 1000 * 60 * 60
        private val SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }
}
