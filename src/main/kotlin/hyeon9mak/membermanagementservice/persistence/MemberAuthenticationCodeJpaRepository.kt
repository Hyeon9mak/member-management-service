package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import org.springframework.data.jpa.repository.JpaRepository

interface MemberAuthenticationCodeJpaRepository : MemberAuthenticationCodeRepository,
    JpaRepository<MemberAuthenticationCode, Long>,
    MemberAuthenticationCodeQueryDslRepository
