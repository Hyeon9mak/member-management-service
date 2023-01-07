package hyeon9mak.membermanagementservice.domain

@JvmInline
value class MemberPhoneNumber(
    val value: String
) {
    init {
        require(value.matches(PATTERN)) { "회원 전화번호 형식이 올바르지 않습니다." }
    }

    companion object {
        private val PATTERN = Regex("^[0-9]{10,11}$")
    }
}
