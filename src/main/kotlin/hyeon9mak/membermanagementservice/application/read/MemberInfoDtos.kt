package hyeon9mak.membermanagementservice.application.read

import hyeon9mak.membermanagementservice.domain.Member
import io.swagger.v3.oas.annotations.media.Schema

data class MemberInfoResponse(
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
        fun from(member: Member) = MemberInfoResponse(
            email = member.getEmailValue(),
            name = member.getNameValue(),
            nickname = member.getNicknameValue(),
            phoneNumber = member.getPhoneNumberValue(),
        )
    }
}
