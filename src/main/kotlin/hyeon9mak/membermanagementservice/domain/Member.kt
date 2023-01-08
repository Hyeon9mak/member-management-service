package hyeon9mak.membermanagementservice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Table(
    name = "member",
    indexes = [
        Index(name = "ux_member_email", columnList = "email", unique = true),
        Index(name = "ux_member_nickname", columnList = "nickname", unique = true),
        Index(name = "ux_member_phone_number", columnList = "phone_number", unique = true),
        Index(name = "ux_member_email_name_phone_number", columnList = "email, name, phone_number", unique = true),
    ]
)
@Entity
class Member(
    @Column(nullable = false, unique = true)
    val email: MemberEmail,

    @Column(nullable = false)
    private var password: MemberPassword,

    @Column(nullable = false, length = 100)
    val name: MemberName,

    @Column(nullable = false, unique = true, length = 10)
    val nickname: MemberNickname,

    @Column(name = "phone_number", nullable = false, unique = true, length = 11)
    val phoneNumber: MemberPhoneNumber,
) : BaseEntity() {

    fun authenticate(plainPassword: String) {
        this.password.authenticate(plainPassword = plainPassword)
    }

    fun resetPassword(password: MemberPassword) {
        this.password = password
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
