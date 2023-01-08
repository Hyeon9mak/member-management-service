package hyeon9mak.membermanagementservice.domain

interface MemberRepository {

    fun save(member: Member): Member

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun findByEmail(email: String): Member?

    fun findByNickname(nickname: String): Member?

    fun findByPhoneNumber(phoneNumber: String): Member?

    fun findByEmailAndNameAndPhoneNumber(email: String, name: String, phoneNumber: String): Member?
}
