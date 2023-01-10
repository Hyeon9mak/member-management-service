package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.domain.Member
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import hyeon9mak.membermanagementservice.domain.MemberRepository
import hyeon9mak.membermanagementservice.domain.isNotExistsEmail
import hyeon9mak.membermanagementservice.domain.isNotExistsNickname
import hyeon9mak.membermanagementservice.domain.isNotExistsPhoneNumber
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberRegisterService(
    private val memberRepository: MemberRepository,
    private val memberAuthenticationCodeRepository: MemberAuthenticationCodeRepository,
) {
    fun register(request: MemberRegisterRequest): MemberRegisterResponse {
        val member = Member.from(
            email = request.email,
            password = request.password,
            name = request.name,
            nickname = request.nickname,
            phoneNumber = request.phoneNumber,
        )
        validateAuthenticationCode(member = member, code = request.authenticationCode)
        validateDuplicateEmailNicknamePhoneNumber(member = member)
        val savedMember = memberRepository.save(member = member)
        return MemberRegisterResponse.from(member = savedMember)
    }

    private fun validateAuthenticationCode(member: Member, code: String) {
        val authenticationCode = findLastCodeByPhoneNumber(phoneNumber = member.getPhoneNumberValue())
        authenticationCode.checkAuthenticated(code = code)
    }

    private fun findLastCodeByPhoneNumber(phoneNumber: String) =
        memberAuthenticationCodeRepository.findLastOneByPhoneNumber(phoneNumber = phoneNumber)
            ?: throw IllegalArgumentException("전화번호에 해당하는 인증코드 기록이 존재하지 않습니다.")

    private fun validateDuplicateEmailNicknamePhoneNumber(member: Member) {
        check(memberRepository.isNotExistsEmail(email = member.email)) { "이미 존재하는 이메일입니다." }
        check(memberRepository.isNotExistsNickname(nickname = member.nickname)) { "이미 존재하는 닉네임입니다." }
        check(memberRepository.isNotExistsPhoneNumber(phoneNumber = member.phoneNumber)) { "이미 존재하는 전화번호입니다." }
    }
}
