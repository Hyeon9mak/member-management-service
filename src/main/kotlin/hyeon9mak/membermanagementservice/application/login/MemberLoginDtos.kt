package hyeon9mak.membermanagementservice.application.login

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class MemberEmailLoginRequest(
    @field:Schema(description = "이메일 주소" , example = "jinha3507@gmail.com", required = true)
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:Schema(description = "비밀번호 (영어 + 숫자 + 특수문자)" , example = "abc123!@#", required = true)
    val password: String,
)

data class MemberNicknameLoginRequest(
    @field:Schema(description = "닉네임" , example = "현구막", required = true)
    @field:Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    val nickname: String,

    @field:Schema(description = "비밀번호 (영어 + 숫자 + 특수문자)" , example = "abc123!@#", required = true)
    val password: String,
)

data class MemberPhoneNumberLoginRequest(
    @field:Schema(description = "전화번호" , example = "01012345678", required = true)
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,

    @field:Schema(description = "비밀번호 (영어 + 숫자 + 특수문자)" , example = "abc123!@#", required = true)
    val password: String,
)

data class MemberLoginResponse(
    @field:Schema(description = "로그인 인증 토큰", required = true)
    val token: String,
)
