package hyeon9mak.membermanagementservice.application.login

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
        val member = findMemberByEmail(email = request.email)
        member.authenticate(plainPassword = request.password)
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    private fun findMemberByEmail(email: String) =
        memberRepository.findByEmail(email = email)
            ?: throw IllegalArgumentException("이메일에 해당하는 회원정보가 없습니다.")

    fun generateTokenByNicknameLogin(request: MemberNicknameLoginRequest): MemberLoginResponse {
        val member = findMemberByNickname(nickname = request.nickname)
        member.authenticate(plainPassword = request.password)
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    private fun findMemberByNickname(nickname: String) =
        memberRepository.findByNickname(nickname = nickname)
            ?: throw IllegalArgumentException("닉네임에 해당하는 회원정보가 없습니다.")

    fun generateTokenByPhoneNumberLogin(request: MemberPhoneNumberLoginRequest): MemberLoginResponse {
        val member = findMemberByPhoneNumber(phoneNumber = request.phoneNumber)
        member.authenticate(plainPassword = request.password)
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }

    private fun findMemberByPhoneNumber(phoneNumber: String) =
        memberRepository.findByPhoneNumber(phoneNumber = phoneNumber)
            ?: throw IllegalArgumentException("전화번호에 해당하는 회원정보가 없습니다.")
}
