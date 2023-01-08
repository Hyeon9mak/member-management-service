package hyeon9mak.membermanagementservice.application.authentication

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import hyeon9mak.membermanagementservice.domain.MemberPhoneNumber
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberAuthenticationCodeService(
    private val authenticationCodeRepository: MemberAuthenticationCodeRepository,
    private val memberRepository: MemberRepository,
    private val smsMessageSender: SmsMessageSender,
) {
    fun generateAuthenticationCodeForRegister(request: MemberRegisterAuthenticationCodeRequest) {
        val memberPhoneNumber = MemberPhoneNumber(value = request.phoneNumber)
        validateAlreadyExistsMember(phoneNumber = memberPhoneNumber)
        val memberAuthenticationCode = authenticationCodeRepository.save(MemberAuthenticationCode(phoneNumber = memberPhoneNumber))
        smsMessageSender.send(phoneNumber = memberPhoneNumber.value, message = memberAuthenticationCode.generateMessage())
    }

    private fun validateAlreadyExistsMember(phoneNumber: MemberPhoneNumber) {
        check(memberRepository.existsByPhoneNumber(phoneNumber = phoneNumber.value).not()) { "이미 전화번호가 동일한 회원계정이 존재합니다." }
    }

    fun generateAuthenticationCodeForPasswordReset(request: MemberPasswordResetAuthenticationCodeRequest) {
        val member = findMemberByEmailAndNameAndPhoneNumber(
            email = request.email,
            name = request.name,
            phoneNumber = request.phoneNumber,
        )
        val memberAuthenticationCode = authenticationCodeRepository.save(MemberAuthenticationCode(phoneNumber = member.phoneNumber))
        smsMessageSender.send(phoneNumber = member.getPhoneNumberValue(), message = memberAuthenticationCode.generateMessage())
    }

    private fun findMemberByEmailAndNameAndPhoneNumber(
        email: String,
        name: String,
        phoneNumber: String
    ) = memberRepository.findByEmailAndNameAndPhoneNumber(
        email = email,
        name = name,
        phoneNumber = phoneNumber
    ) ?: throw IllegalArgumentException("회원계정이 존재하지 않습니다.")

    fun authenticate(request: MemberRegisterAuthenticateRequest) {
        val authenticationCode = findLastOneByPhoneNumber(phoneNumber = request.phoneNumber)
        authenticationCode.authenticate(code = request.code)
    }

    private fun findLastOneByPhoneNumber(phoneNumber: String) =
        authenticationCodeRepository.findLastOneByPhoneNumber(phoneNumber = phoneNumber)
            ?: throw IllegalArgumentException("전화번호에 해당하는 인증코드 기록이 존재하지 않습니다.")
}
