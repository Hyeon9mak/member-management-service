package hyeon9mak.membermanagementservice.application.read

import hyeon9mak.membermanagementservice.domain.Member

data class MemberInfoResponse(
    val email: String,
    val name: String,
    val nickname: String,
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
