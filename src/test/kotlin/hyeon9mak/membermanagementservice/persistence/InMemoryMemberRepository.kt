package hyeon9mak.membermanagementservice.persistence

import hyeon9mak.membermanagementservice.domain.Member
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

    override fun existsByEmail(email: String): Boolean =
        members.values.any { it.email.value == email }

    override fun existsByNickname(nickname: String): Boolean =
        members.values.any { it.nickname.value == nickname }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean =
        members.values.any { it.phoneNumber.value == phoneNumber }

    fun init() {
        members.clear()
        atomicLong.set(0)
    }
}
