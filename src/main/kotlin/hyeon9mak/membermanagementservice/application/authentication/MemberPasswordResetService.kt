package hyeon9mak.membermanagementservice.application.authentication

import hyeon9mak.membermanagementservice.domain.Member
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import hyeon9mak.membermanagementservice.domain.MemberPassword
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberPasswordResetService(
    private val memberRepository: MemberRepository,
    private val authenticationCodeRepository: MemberAuthenticationCodeRepository,
) {
    fun resetPassword(request: MemberPasswordResetRequest) {
        validateAuthenticationCode(phoneNumber = request.phoneNumber, code = request.authenticationCode)
        val member = findMemberByEmailAndNameAndPhoneNumber(
            email = request.email,
            name = request.name,
            phoneNumber = request.phoneNumber,
        )
        member.resetPassword(password = MemberPassword.createWithEncrypt(value = request.password))
        memberRepository.save(member = member)
    }

    private fun findMemberByEmailAndNameAndPhoneNumber(email: String, name: String, phoneNumber: String): Member =
        memberRepository.findByEmailAndNameAndPhoneNumber(
            email = email,
            name = name,
            phoneNumber = phoneNumber,
        ) ?: throw IllegalArgumentException("회원 정보가 존재하지 않습니다.")

    private fun validateAuthenticationCode(phoneNumber: String, code: String) {
        val authenticationCode = findLastCodeByPhoneNumber(phoneNumber = phoneNumber)
        authenticationCode.checkAuthenticated(code = code)
    }

    private fun findLastCodeByPhoneNumber(phoneNumber: String) =
        authenticationCodeRepository.findLastOneByPhoneNumber(phoneNumber = phoneNumber)
            ?: throw IllegalArgumentException("전화번호에 해당하는 인증코드 기록이 존재하지 않습니다.")
}
