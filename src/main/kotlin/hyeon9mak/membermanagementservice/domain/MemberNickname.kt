package hyeon9mak.membermanagementservice.domain

@JvmInline
value class MemberNickname(
    val value: String
) {
    init {
        require(value.matches(PATTERN)) { "회원 닉네임 형식이 올바르지 않습니다." }
    }

    companion object {
        private val PATTERN = Regex("^[a-zA-Z0-9가-힣]{2,10}$")
    }
}
