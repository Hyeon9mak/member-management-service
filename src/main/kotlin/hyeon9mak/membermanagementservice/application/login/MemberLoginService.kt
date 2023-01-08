package hyeon9mak.membermanagementservice.application.login

import hyeon9mak.membermanagementservice.domain.MemberPassword
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberLoginService(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun generateTokenByEmailLogin(request: MemberEmailLoginRequest): MemberLoginResponse {
        val member = memberRepository.findByEmail(email = request.email)
        member.authenticate(password = MemberPassword(value = request.password))
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    fun generateTokenByNicknameLogin(request: MemberNicknameLoginRequest): MemberLoginResponse {
        val member = memberRepository.findByNickname(nickname = request.nickname)
        member.authenticate(password = MemberPassword(value = request.password))
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    fun generateTokenByPhoneNumberLogin(request: MemberPhoneNumberLoginRequest): MemberLoginResponse {
        val member = memberRepository.findByPhoneNumber(phoneNumber = request.phoneNumber)
        member.authenticate(password = MemberPassword(value = request.password))
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }
}
