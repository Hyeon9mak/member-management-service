package hyeon9mak.membermanagementservice.domain

interface MemberRepository {

    fun save(member: Member): Member
}
