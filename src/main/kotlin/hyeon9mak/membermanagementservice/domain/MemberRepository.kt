package hyeon9mak.membermanagementservice.domain

fun MemberRepository.isNotExistsEmail(email: MemberEmail): Boolean =
    existsByEmail(email = email.value).not()

fun MemberRepository.isNotExistsNickname(nickname: MemberNickname): Boolean =
    existsByNickname(nickname = nickname.value).not()

fun MemberRepository.isNotExistsPhoneNumber(phoneNumber: MemberPhoneNumber): Boolean =
    existsByPhoneNumber(phoneNumber = phoneNumber.value).not()

fun MemberRepository.findByEmailOrThrow(email: MemberEmail): Member =
    findByEmail(email = email.value) ?: throw IllegalArgumentException("이메일에 해당하는 회원정보가 없습니다.")

fun MemberRepository.findByNicknameOrThrow(nickname: MemberNickname): Member =
    findByNickname(nickname = nickname.value) ?: throw IllegalArgumentException("닉네임에 해당하는 회원정보가 없습니다.")

fun MemberRepository.findByPhoneNumberOrThrow(phoneNumber: MemberPhoneNumber): Member =
    findByPhoneNumber(phoneNumber = phoneNumber.value) ?: throw IllegalArgumentException("전화번호에 해당하는 회원정보가 없습니다.")

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
