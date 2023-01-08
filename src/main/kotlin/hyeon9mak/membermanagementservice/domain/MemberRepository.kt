package hyeon9mak.membermanagementservice.domain

interface MemberRepository {

    fun save(member: Member): Member

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean
}
