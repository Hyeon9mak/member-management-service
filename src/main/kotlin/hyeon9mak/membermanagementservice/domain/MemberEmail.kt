package hyeon9mak.membermanagementservice.domain

data class MemberEmail(
    val value: String
) {
    init {
        require(value.matches(PATTERN)) { "회원 이메일 형식이 올바르지 않습니다." }
    }

    companion object {
        private val PATTERN = Regex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    }
}
