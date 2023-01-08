package hyeon9mak.membermanagementservice.application.authentication

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import hyeon9mak.membermanagementservice.domain.MemberPassword
import hyeon9mak.membermanagementservice.domain.MemberPhoneNumber
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberAuthenticationService(
    private val authenticationCodeRepository: MemberAuthenticationCodeRepository,
    private val memberRepository: MemberRepository,
    private val smsMessageSender: SmsMessageSender,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun generateAuthenticationCodeForRegister(request: MemberAuthenticationCodeRequest) {
        val memberPhoneNumber = MemberPhoneNumber(value = request.phoneNumber)
        validateAlreadyExistsMember(phoneNumber = memberPhoneNumber)
        val memberAuthenticationCode = authenticationCodeRepository.save(MemberAuthenticationCode(phoneNumber = memberPhoneNumber))
        smsMessageSender.send(phoneNumber = memberPhoneNumber.value, message = memberAuthenticationCode.generateMessage())
    }

    private fun validateAlreadyExistsMember(phoneNumber: MemberPhoneNumber) {
        check(memberRepository.existsByPhoneNumber(phoneNumber = phoneNumber.value).not()) { "이미 전화번호가 동일한 회원계정이 존재합니다." }
    }

    fun authenticate(request: MemberAuthenticateRequest) {
        val authenticationCode = authenticationCodeRepository.findLastOneByPhoneNumber(phoneNumber = request.phoneNumber)
        authenticationCode.authenticate(code = request.code)
    }

    fun generateTokenByEmailLogin(request: MemberEmailLoginRequest): MemberLoginResponse {
        val member = memberRepository.findByEmail(email = request.email)
        member.authenticate(password = MemberPassword(value = request.password))
        val token = jwtTokenProvider.createToken(payload = member.getEmailValue())
        return MemberLoginResponse(token = token)
    }
}
