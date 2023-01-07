package hyeon9mak.membermanagementservice.application.authentication

import javax.validation.constraints.Size

data class MemberAuthenticationCodeRequest(
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,
)

data class MemberAuthenticationCodeResponse(
    val authenticationCode: String,
)

data class MemberAuthenticateRequest(
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,

    @field:Size(min = 6, max = 6, message = "인증번호는 6자리로 입력해주세요.")
    val code: String,
)
