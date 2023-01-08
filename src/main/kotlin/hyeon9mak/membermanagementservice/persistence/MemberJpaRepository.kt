package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.Member
import hyeon9mak.membermanagementservice.domain.MemberRepository
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : MemberRepository, JpaRepository<Member, Long>
