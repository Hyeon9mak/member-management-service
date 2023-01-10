package hyeon9mak.membermanagementservice.application.login

import hyeon9mak.membermanagementservice.domain.MemberEmail
import hyeon9mak.membermanagementservice.domain.MemberNickname
import hyeon9mak.membermanagementservice.domain.MemberPhoneNumber
import hyeon9mak.membermanagementservice.domain.MemberRepository
import hyeon9mak.membermanagementservice.domain.findByEmailOrThrow
import hyeon9mak.membermanagementservice.domain.findByNicknameOrThrow
import hyeon9mak.membermanagementservice.domain.findByPhoneNumberOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberLoginService(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun generateTokenByEmailLogin(request: MemberEmailLoginRequest): MemberLoginResponse {
        val email = MemberEmail(value = request.email)
        val member = memberRepository.findByEmailOrThrow(email = email)
        member.authenticate(plainPassword = request.password)
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    fun generateTokenByNicknameLogin(request: MemberNicknameLoginRequest): MemberLoginResponse {
        val nickname = MemberNickname(value = request.nickname)
        val member = memberRepository.findByNicknameOrThrow(nickname = nickname)
        member.authenticate(plainPassword = request.password)
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    fun generateTokenByPhoneNumberLogin(request: MemberPhoneNumberLoginRequest): MemberLoginResponse {
        val phoneNumber = MemberPhoneNumber(value = request.phoneNumber)
        val member = memberRepository.findByPhoneNumberOrThrow(phoneNumber = phoneNumber)
        member.authenticate(plainPassword = request.password)
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }
}
