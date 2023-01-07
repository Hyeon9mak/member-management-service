package hyeon9mak.membermanagementservice.domain

class Member private constructor(
    val id: Long = 0,
    val email: MemberEmail,
    val password: MemberPassword,
    val name: MemberName,
    val nickname: MemberNickname,
    val phoneNumber: MemberPhoneNumber,
) {
    fun getEmailValue(): String = email.value

    fun getNameValue(): String = name.value

    fun getNicknameValue(): String = nickname.value

    fun getPhoneNumberValue(): String = phoneNumber.value

    companion object {
        fun withoutId(
            email: String,
            password: String,
            name: String,
            nickname: String,
            phoneNumber: String
        ) = Member(
            email = MemberEmail(email),
            password = MemberPassword(password),
            name = MemberName.of(name),
            nickname = MemberNickname(nickname),
            phoneNumber = MemberPhoneNumber(phoneNumber),
        )

        fun withId(
            id: Long,
            email: String,
            password: String,
            name: String,
            nickname: String,
            phoneNumber: String
        ) = Member(
            id = id,
            email = MemberEmail(email),
            password = MemberPassword(password),
            name = MemberName.of(name),
            nickname = MemberNickname(nickname),
            phoneNumber = MemberPhoneNumber(phoneNumber),
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
