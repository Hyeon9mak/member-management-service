package hyeon9mak.membermanagementservice.application.login

import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class MemberEmailLoginRequest(
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,
    val password: String,
)

data class MemberNicknameLoginRequest(
    @field:Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    val nickname: String,
    val password: String,
)

data class MemberPhoneNumberLoginRequest(
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,
    val password: String,
)

data class MemberLoginResponse(
    val token: String,
)
