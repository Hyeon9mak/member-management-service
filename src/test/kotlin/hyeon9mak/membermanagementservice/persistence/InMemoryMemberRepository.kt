package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.Member
import hyeon9mak.membermanagementservice.domain.MemberEmail
import hyeon9mak.membermanagementservice.domain.MemberNickname
import hyeon9mak.membermanagementservice.domain.MemberPhoneNumber
import hyeon9mak.membermanagementservice.domain.MemberRepository
import java.util.concurrent.atomic.AtomicLong

class InMemoryMemberRepository : MemberRepository {

    private val members = mutableMapOf<Long, Member>()
    private val atomicLong = AtomicLong(0)

    override fun save(member: Member): Member {
        val id = atomicLong.incrementAndGet()
        val idField = member::class.java.superclass.getDeclaredField("id")
        idField.isAccessible = true
        idField.set(member, id)
        members[id] = member
        return member
    }

    override fun existsByEmail(email: MemberEmail): Boolean =
        members.values.any { it.email == email }

    override fun existsByNickname(nickname: MemberNickname): Boolean =
        members.values.any { it.nickname == nickname }

    override fun existsByPhoneNumber(phoneNumber: MemberPhoneNumber): Boolean =
        members.values.any { it.phoneNumber == phoneNumber }

    fun init() {
        members.clear()
        atomicLong.set(0)
    }
}
