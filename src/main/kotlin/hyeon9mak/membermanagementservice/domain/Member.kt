package hyeon9mak.membermanagementservice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Table(
    name = "member",
    indexes = [
        Index(name = "member_email_index", columnList = "email", unique = true),
        Index(name = "member_nickname_index", columnList = "nickname", unique = true),
        Index(name = "member_phone_number_index", columnList = "phone_number", unique = true)
    ]
)
@Entity
class Member private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: MemberEmail,

    @Column(nullable = false, length = 20)
    val password: MemberPassword,

    @Column(nullable = false, length = 100)
    val name: MemberName,

    @Column(nullable = false, unique = true, length = 10)
    val nickname: MemberNickname,

    @Column(nullable = false, unique = true, length = 11)
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
