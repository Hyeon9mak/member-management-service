package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode

interface MemberAuthenticationCodeQueryDslRepository {

    fun findLastByPhoneNumber(phoneNumber: String): MemberAuthenticationCode
}
