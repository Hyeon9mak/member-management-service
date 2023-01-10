package hyeon9mak.membermanagementservice.application.authentication

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class MemberRegisterAuthenticationCodeRequest(
    @field:Schema(description = "전화번호" , example = "01012345678", required = true)
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,
)

data class MemberRegisterAuthenticateRequest(
    @field:Schema(description = "전화번호" , example = "01012345678", required = true)
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,

    @field:Schema(description = "인증번호 (8자리)" , example = "jH1Vcqdf", required = true)
    @field:Size(min = 8, max = 8, message = "인증번호는 8자리로 입력해주세요.")
    val authenticationCode: String,
)

data class MemberPasswordResetAuthenticationCodeRequest(
    @field:Schema(description = "이메일 주소" , example = "jinha3507@gmail.com", required = true)
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:Schema(description = "이름" , example = "최현구", required = true)
    @field:Size(min = 2, max = 100, message = "이름은 2자 이상 100자 이하로 입력해주세요.")
    val name: String,

    @field:Schema(description = "전화번호" , example = "01012345678", required = true)
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,
)
