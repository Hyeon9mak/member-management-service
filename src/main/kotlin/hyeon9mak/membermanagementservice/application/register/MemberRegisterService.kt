package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.domain.Member
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberRegisterService(
    private val memberRepository: MemberRepository,
) {
    fun register(request: MemberRegisterRequest): MemberRegisterResponse {
        val member = Member.withoutId(
            email = request.email,
            password = request.password,
            name = request.name,
            nickname = request.nickname,
            phoneNumber = request.phoneNumber,
        )
        validateDuplicateEmailNicknamePhoneNumber(member = member)
        val savedMember = memberRepository.save(member = member)
        return MemberRegisterResponse.from(member = savedMember)
    }

    private fun validateDuplicateEmailNicknamePhoneNumber(member: Member) {
        check(memberRepository.existsByEmail(email = member.email).not()) { "이미 존재하는 이메일입니다." }
        check(memberRepository.existsByNickname(nickname = member.nickname).not()) { "이미 존재하는 닉네임입니다." }
        check(memberRepository.existsByPhoneNumber(phoneNumber = member.phoneNumber).not()) { "이미 존재하는 전화번호입니다." }
    }
}
