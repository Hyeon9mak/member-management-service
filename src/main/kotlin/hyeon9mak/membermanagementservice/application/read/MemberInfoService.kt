package hyeon9mak.membermanagementservice.application.read

import hyeon9mak.membermanagementservice.domain.MemberEmail
import hyeon9mak.membermanagementservice.domain.MemberRepository
import hyeon9mak.membermanagementservice.domain.findByEmailOrThrow
import hyeon9mak.membermanagementservice.domain.isNotExistsEmail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberInfoService(
    private val memberRepository: MemberRepository,
) {
    fun notExistsMemberByEmail(email: MemberEmail) = memberRepository.isNotExistsEmail(email = email)

    fun getInfoByEmail(email: String): MemberInfoResponse {
        val member = memberRepository.findByEmailOrThrow(email = MemberEmail(value = email))
        return MemberInfoResponse.from(member = member)
    }
}
