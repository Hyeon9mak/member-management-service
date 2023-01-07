package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.domain.Member
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class MemberRegisterRequest(
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    val password: String,

    @field:Size(min = 2, max = 100, message = "이름은 2자 이상 100자 이하로 입력해주세요.")
    val name: String,

    @field:Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    val nickname: String,

    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,
)

data class MemberRegisterResponse(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val phoneNumber: String,
) {
    companion object {
        fun from(member: Member): MemberRegisterResponse = MemberRegisterResponse(
            id = member.id,
            email = member.getEmailValue(),
            name = member.getNameValue(),
            nickname = member.getNicknameValue(),
            phoneNumber = member.getPhoneNumberValue(),
        )
    }
}
