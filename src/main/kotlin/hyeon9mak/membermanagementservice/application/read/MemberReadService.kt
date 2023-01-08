package hyeon9mak.membermanagementservice.application.read

import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberReadService(
    private val memberRepository: MemberRepository,
) {
    fun existsMemberByEmail(email: String) = memberRepository.existsByEmail(email = email)
}
