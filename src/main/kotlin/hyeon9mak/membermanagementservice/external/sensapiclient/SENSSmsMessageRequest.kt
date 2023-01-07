package hyeon9mak.membermanagementservice.external.sensapiclient

data class SENSSmsMessageRequest(
    val type: String = "SMS",
    val from: String, // 사전에 등록된 발신번호만 가능
    val content: String,
    val messages: List<SENSMessage>,
) {
    companion object {
        fun from(
            from: String,
            to: String,
            content: String,
        ): SENSSmsMessageRequest = SENSSmsMessageRequest(
            from = from,
            messages = listOf(SENSMessage(to = to)),
            content = content,
        )
    }
}
