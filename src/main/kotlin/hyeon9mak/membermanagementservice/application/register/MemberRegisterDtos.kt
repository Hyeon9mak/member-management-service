package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.domain.Member
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class MemberRegisterRequest(

    @field:Schema(description = "이메일 주소" , example = "jinha3507@gmail.com", required = true)
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:Schema(description = "비밀번호 (영어 + 숫자 + 특수문자 8~20자리)" , example = "abc123!@#", required = true)
    @field:Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    val password: String,

    @field:Schema(description = "이름" , example = "최현구", required = true)
    @field:Size(min = 2, max = 100, message = "이름은 2자 이상 100자 이하로 입력해주세요.")
    val name: String,

    @field:Schema(description = "닉네임" , example = "현구막", required = true)
    @field:Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    val nickname: String,

    @field:Schema(description = "전화번호" , example = "01012345678", required = true)
    @field:Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
    val phoneNumber: String,

    @field:Schema(description = "인증번호 (8자리)" , example = "jH1Vcqdf", required = true)
    @field:Size(min = 8, max = 8, message = "인증번호는 8자로 입력해주세요.")
    val authenticationCode: String,
)

data class MemberRegisterResponse(
    val id: Long,

    @field:Schema(description = "이메일 주소" , example = "jinha3507@gmail.com", required = true)
    val email: String,

    @field:Schema(description = "이름" , example = "최현구", required = true)
    val name: String,

    @field:Schema(description = "닉네임" , example = "현구막", required = true)
    val nickname: String,

    @field:Schema(description = "전화번호" , example = "01012345678", required = true)
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
