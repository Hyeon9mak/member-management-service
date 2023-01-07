package hyeon9mak.membermanagementservice.domain

data class MemberPassword(
    val value: String
) {
    init {
        require(value.matches(PATTERN)) { "회원 비밀번호 형식이 올바르지 않습니다." }
    }

    companion object {
        private val PATTERN = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]){8,20}$")
    }
}
