package hyeon9mak.membermanagementservice.application.register

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
        val savedMember = Member.withId(
            id = id,
            email = member.getEmailValue(),
            password = member.password.value,
            name = member.name.value,
            nickname = member.getNicknameValue(),
            phoneNumber = member.getPhoneNumberValue(),
        )
        members[id] = savedMember
        return savedMember
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
