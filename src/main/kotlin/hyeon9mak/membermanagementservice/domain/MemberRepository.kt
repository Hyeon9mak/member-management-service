package hyeon9mak.membermanagementservice.domain

interface MemberRepository {

    fun save(member: Member): Member

    fun existsByEmail(email: MemberEmail): Boolean

    fun existsByNickname(nickname: MemberNickname): Boolean

    fun existsByPhoneNumber(phoneNumber: MemberPhoneNumber): Boolean
}
