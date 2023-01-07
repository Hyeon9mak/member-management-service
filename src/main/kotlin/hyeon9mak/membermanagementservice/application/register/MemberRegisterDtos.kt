package hyeon9mak.membermanagementservice.application.register

import hyeon9mak.membermanagementservice.domain.Member

data class MemberRegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val nickname: String,
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
