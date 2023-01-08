package hyeon9mak.membermanagementservice.domain

import javax.persistence.Column
import javax.persistence.Entity
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
class Member(
    @Column(nullable = false, unique = true)
    val email: MemberEmail,

    @Column(nullable = false)
    val password: MemberPassword,

    @Column(nullable = false, length = 100)
    val name: MemberName,

    @Column(nullable = false, unique = true, length = 10)
    val nickname: MemberNickname,

    @Column(name = "phone_number", nullable = false, unique = true, length = 11)
    val phoneNumber: MemberPhoneNumber,
) : BaseEntity() {

    fun authenticate(password: MemberPassword) {
        this.password.authenticate(password)
    }

    fun getEmailValue(): String = email.value
    fun getNameValue(): String = name.value
    fun getNicknameValue(): String = nickname.value
    fun getPhoneNumberValue(): String = phoneNumber.value

    companion object {
        fun from(
            email: String,
            password: String,
            name: String,
            nickname: String,
            phoneNumber: String
        ) = Member(
            email = MemberEmail(email),
            password = MemberPassword.createWithEncrypt(value = password),
            name = MemberName.of(name),
            nickname = MemberNickname(nickname),
            phoneNumber = MemberPhoneNumber(phoneNumber),
        )
    }
}
