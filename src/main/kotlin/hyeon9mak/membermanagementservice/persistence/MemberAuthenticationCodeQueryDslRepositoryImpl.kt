package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode
import hyeon9mak.membermanagementservice.domain.QMemberAuthenticationCode.memberAuthenticationCode
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class MemberAuthenticationCodeQueryDslRepositoryImpl : MemberAuthenticationCodeQueryDslRepository,
    QuerydslRepositorySupport(MemberAuthenticationCode::class.java) {

    override fun findLastOneByPhoneNumber(phoneNumber: String): MemberAuthenticationCode? =
        from(memberAuthenticationCode)
            .where(memberAuthenticationCode.phoneNumber.eq(phoneNumber))
            .orderBy(memberAuthenticationCode.id.desc())
            .fetchFirst()
}
