package hyeon9mak.membermanagementservice.domain

import org.springframework.stereotype.Repository

@Repository
interface MemberAuthenticationCodeRepository {

    fun save(memberAuthenticationCode: MemberAuthenticationCode): MemberAuthenticationCode
}
