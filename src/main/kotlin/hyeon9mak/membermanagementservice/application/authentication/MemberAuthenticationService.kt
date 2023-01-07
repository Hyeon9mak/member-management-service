package hyeon9mak.membermanagementservice.application.authentication

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import hyeon9mak.membermanagementservice.domain.MemberPhoneNumber
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberAuthenticationService(
    private val authenticationCodeRepository: MemberAuthenticationCodeRepository,
    private val memberRepository: MemberRepository,
) {
    fun generateAuthenticationCodeForRegister(phoneNumber: String): MemberAuthenticationCodeResponse {
        val memberPhoneNumber = MemberPhoneNumber(value = phoneNumber)
        validateAlreadyExistsMember(phoneNumber = memberPhoneNumber)
        val memberAuthenticationCode = authenticationCodeRepository.save(MemberAuthenticationCode(phoneNumber = memberPhoneNumber))
        return MemberAuthenticationCodeResponse(authenticationCode = memberAuthenticationCode.code)
    }

    private fun validateAlreadyExistsMember(phoneNumber: MemberPhoneNumber) {
        check(memberRepository.existsByPhoneNumber(phoneNumber = phoneNumber).not()) { "이미 전화번호가 동일한 회원계정이 존재합니다." }
    }
}
