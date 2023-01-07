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
        val savedMember = memberRepository.save(member)
        return MemberRegisterResponse.from(member = savedMember)
    }
}
