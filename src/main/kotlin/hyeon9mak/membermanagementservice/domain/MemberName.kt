package hyeon9mak.membermanagementservice.domain

@JvmInline
value class MemberName private constructor(
    val value: String
) {
    init {
        require(value.matches(PATTERN_KOREAN) || value.matches(PATTERN_ALPHABET)) { "회원 이름 형식이 올바르지 않습니다." }
    }

    companion object {
        private val PATTERN_KOREAN = Regex("^[가-힣]{2,100}$")
        private val PATTERN_ALPHABET = Regex("^[a-zA-Z\\s]{2,100}$")

        fun of(value: String): MemberName = MemberName(value.trim())
    }
}
