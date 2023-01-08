package hyeon9mak.membermanagementservice.web

data class ExceptionResponse(
    val message: String
) {
    companion object {
        fun from(message: String?): ExceptionResponse = ExceptionResponse(message = message ?: "알 수 없는 오류가 발생했습니다.")
    }
}
