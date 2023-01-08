package hyeon9mak.membermanagementservice.application.read

import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberInfoService(
    private val memberRepository: MemberRepository,
) {
    fun existsMemberByEmail(email: String) = memberRepository.existsByEmail(email = email)

    fun getInfoByEmail(email: String): MemberInfoResponse {
        val member = findMemberByEmail(email = email)
        return MemberInfoResponse.from(member = member)
    }

    private fun findMemberByEmail(email: String) = memberRepository.findByEmail(email = email)
        ?: throw IllegalArgumentException("이메일에 해당하는 회원정보가 없습니다.")
}
