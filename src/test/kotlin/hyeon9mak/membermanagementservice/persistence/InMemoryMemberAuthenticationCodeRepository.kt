package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCode
import hyeon9mak.membermanagementservice.domain.MemberAuthenticationCodeRepository
import java.util.concurrent.atomic.AtomicLong

class InMemoryMemberAuthenticationCodeRepository : MemberAuthenticationCodeRepository {

    private val codes = mutableMapOf<Long, MemberAuthenticationCode>()
    private val atomicLong = AtomicLong(0)

    override fun save(memberAuthenticationCode: MemberAuthenticationCode): MemberAuthenticationCode {
        val id = atomicLong.incrementAndGet()
        val idField = memberAuthenticationCode::class.java.getDeclaredField("id")
        idField.isAccessible = true
        idField.set(memberAuthenticationCode, id)
        codes[id] = memberAuthenticationCode
        return memberAuthenticationCode
    }

    fun init() {
        codes.clear()
        atomicLong.set(0)
    }
}
